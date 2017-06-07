#!/bin/sh

# Device definitions
audio=""
func_name=""

# just in case
export DISPLAY=:0

function check_for_sound
{
soundcard=`cat /proc/asound/cards`
if [[ $soundcard == "--- no soundcards ---" ]]; then
	echo "sound: no"
	audio="no"
else
	echo "sound: yes"
	audio="yes"
fi
echo "==============================="
}

# play in hardware fullscreen overlay
function play_fs
{
cmd=$1
fn=$2
aud=$3

aud_append=" "
if [[ $aud != "no" ]]; then
aud_append="demux.audio_0 \
	    ! queue \
	    ! aacparse ! faad ! alsasink "
fi

$cmd -v \
        filesrc location=$fn \
        ! qtdemux name=demux $aud_append \
	demux.video_0 \
	! queue  \
	! h264parse \
        ! video/x-h264,stream-format=byte-stream \
	! vaapidecode \
	! vaapisink sync=true \
	fullscreen=true
}

# scale and output in ximagesink i.e. software overlay
function play_scale_xv
{
cmd=$1
fn=$2
aud=$3
win=$4

aud_append=" "
if [[ $aud != "no" ]]; then
aud_append="demux.audio_0 \
	    ! queue \
	    ! aacparse ! faad ! alsasink "
fi

$cmd -v filesrc location=$fn \
	        ! qtdemux name=demux $aud_append \
		demux.video_0 \
		! queue  \
		! h264parse \
		! video/x-h264,stream-format=byte-stream \
		! vaapidecode \
		! videoscale \
		! video/x-raw,width=${win[0]},height=${win[1]} \
		! xvimagesink sync=true name=videosink0
}

# scale and output in hardware overlay
function play_scale_ovl
{
cmd=$1
fn=$2
aud=$3
win=$4

aud_append=" "
if [[ $aud != "no" ]]; then
aud_append="demux.audio_0 \
	    ! queue \
	    ! aacparse ! faad ! alsasink "
fi

$cmd -v filesrc location=$fn \
		! qtdemux name=demux $aud_append \
		demux.video_0 \
		! queue  \
		! h264parse \
		! video/x-h264,stream-format=byte-stream \
		! vaapidecode \
		! vaapipostproc width=${win[0]} height=${win[1]} \
		! vaapisink sync=true
}

function usage
{
 	printf "\n--------------------------------------------------------------------------\n"
	printf "Usage: $0  -t test [-a]  -l [-w w,h] FILE\n"
	printf "Test video playback\n\n"
	printf "   -t test=fs|ovl|xv	fs=fullscreen, ovl=hardware window mode\n"
	printf "   -a			enable audio\n"
	printf "   -l			loop over and play the files in the direcory specified by FILE\n"
	printf "   -w w,h		rect position (width,height) only needed for ovl\n\n\n"
	printf "Examples:\n"
	printf "    Start full screen playback.\n"
	printf "        dmo-video-play.sh -t fs  test.mp4\n"
	printf "    Start playback in hardware overlayed window.\n"
	printf "        dmo-video-play.sh -t ovl -w 540,480 test.mp4\n"
	printf "    Start full screen playback loop.\n"
	printf "        dmo-video-play.sh -t fs  -l /videos\n\n\n"
}



sound=0
loop=0

while getopts "t:alw:" Option
do
	case $Option in
		t ) tst=${OPTARG};;
		a ) sound=1;;
		l ) loop=1;;
		w ) rect=${OPTARG};;
	esac
done
shift $(($OPTIND-1))
fn=$1

if [[ -z $fn || -z $tst || ($tst != "fs" && -z $rect) ]]; then
	usage
	exit 1
fi

if [[ $loop -eq 1 && ! -d $fn ]]; then
	echo "ERROR: File is not directory."
	exit 1
fi

# only for window modes
if [[ "$tst" != "fs" ]]; then

OIFS=$IFS

IFS=', '
win=($rect)

if [[ -d $fn ]]; then
fsrc=`ls -1 $fn | head -1`
filesrc=${fn}/${fsrc}
else
filesrc=$fn
fi

video_fmt=`gst-discoverer-1.0  -v ${filesrc}  |grep video:`
for i in $video_fmt; do
	if [[ $i =~ .*width=\(int\).* ]]; then
		width=${i#width=(int)}
	fi
	if [[ $i =~ .*height=\(int\).* ]]; then
		height=${i#height=(int)}
	fi
done

IFS=$OIFS


max_scale_x=$(((width*4)-20))
max_scale_y=$(((height*4)-20))
min_scale_x=$(((width/4)+20))
min_scale_y=$(((height/4)+20))
printf "+---+\n"
printf "video resolution: ${width}x${height} scale limits:[${min_scale_x}x${min_scale_y}..${max_scale_x}x${max_scale_y}]\n"
printf "scale win: ${win[0]}x${win[1]}\n"


if [[ ${#win[@]} -ne 2 ]]; then
	printf "ERROR: Invalid window coordinates.\n"
	exit 1
fi

if [[ "${win[0]}" -lt "$min_scale_x" || "${win[0]}" -gt "$max_scale_x" ||
      "${win[1]}" -lt "$min_scale_y" || "${win[1]}" -gt "$max_scale_y" ]]; then
	printf "ERROR: Scaling limits exceeded.\n"
	exit 1
fi

fi # tst != fs

cmd="/usr/bin/gst-launch-1.0"

case $tst in
	fs)
		func_name="play_fs"
		;;
	xv)
		func_name="play_scale_xv"
		;;
	ovl)
		func_name="play_scale_ovl"
		;;
esac

if [[ $sound -eq 1 ]]; then
	check_for_sound
else
	audio="no"
fi

if [[ $loop -eq 0 ]]; then
	$func_name $cmd $fn $audio $win
fi

if [[ $loop -eq 1 ]]; then
cd $fn
while [[ true ]]; do
	for f in `ls -1`; do
		echo $f
		$func_name $cmd $f $audio $win
	done
done	
fi

