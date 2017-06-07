#!/bin/sh

# Device definitions
dec_dev=""
dec_plugin=""
enc_dev=""
ovl1_dev=""
ovl2_dev=""
scale1_dev=""
scale1_plugin=""
scale2_dev=""
scale2_plugin=""
vout1_dev=""
vout2_dev=""
audio=""
func_name=""
output_device=""

# just in case
export DISPLAY=:0

# play in hardware fullscreen overlay
function play_fs
{
odev=$1
cmd=$2
fn=$3
aud=$4

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
        ! $dec_plugin output-io-mode=2 capture-io-mode=4 \
	! v4l2sink device=$odev io-mode=5 sync=true \
	crop-top=0 crop-height=1080 overlay-height=1080
}

# scale and output in hardware overlay
function play_scale_ovl
{
ovldev=$1
scdev=$2
cmd=$3
fn=$4
aud=$5
win=$6

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
		! $dec_plugin capture-io-mode=dmabuf \
		! $scdev output-io-mode=dmabuf-import capture-io-mode=dmabuf \
		! video/x-raw,format=RGB,width=${win[2]},height=${win[3]} \
		! v4l2sink device=$ovldev io-mode=dmabuf-import sync=true \
		overlay-left=${win[0]} overlay-top=${win[1]} overlay-width=${win[2]} overlay-height=${win[3]}

}


# scale and output in ximagesink i.e. software overlay
function play_scale_xv
{
scdev=$1
cmd=$2
fn=$3
aud=$4
win=$5

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
		! $dec_plugin capture-io-mode=dmabuf \
		! $scdev  output-io-mode=dmabuf-import \
		! video/x-raw,width=${win[2]},height=${win[3]} \
		! ximagesink sync=true name=videosink0
}


function get_v4l2_devices
{
ovl=1
scale=1
vout=1
for dev in `ls -1 /dev/video/by-name/*`; do
	case "$dev" in
		*decoder*)
		dec_dev=$dev
		num=${dev##/dev/video/by-name/coda-decoder-}
		dec_plugin="v4l2video"$num"dec"
		;;
		*encoder*)
		enc_dev="$dev"
		;;
		*ovl*)
		if [[ $ovl == 1 ]]; then
			ovl1_dev="$dev"
			ovl=$((ovl+1))
		else
			ovl2_dev="$dev"
		fi
		;;
		*scale*)
		if [[ $scale == 1 ]]; then
			scale1_dev=$dev
			num=${dev##/dev/video/by-name/imx-ipuv3-scale-}
			scale1_plugin="v4l2video"$num"convert"
			scale=$((scale+1))
		else
			scale2_dev=$dev
			num=${dev##/dev/video/by-name/imx-ipuv3-scale-}
			scale2_plugin="v4l2video"$num"convert"
		fi
		;;
		*voutbg*)
		if [[ $vout == 1 ]]; then
			vout1_dev="$dev"
			vout=$((vout+1))
		else
			vout2_dev="$dev"
		fi
		;;
	esac
done

echo "==============================="
if [[ -z $vout2_dev ]]; then
	echo "CPU: imx6dl"
	dual_lite=yes
else
	echo "CPU: imx6q"
	dual_lite=no
fi



printf "vout1: $vout1_dev\nvout2: $vout2_dev\n"
printf "ovl1: $ovl1_dev\novl2: $ovl2_dev\n"
printf "scale1: $scale1_dev ($scale1_plugin)\nsclae2: $scale2_dev ($scale2_plugin)\n"
printf "decoder: $dec_dev ($dec_plugin)\nencoder: $enc_dev\n"

}

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

function usage
{
 	printf "\n--------------------------------------------------------------------------\n"
	printf "Usage: $0  -t test [-a]  -o device -l [-w l,t,w,h] FILE\n"
	printf "Test video playback\n\n"
	printf "   -t test=fs|ovl|xv	fs=fullscreen, ovl=hardware window mode, xv=xvimagesink window mode\n"
	printf "   -a			enable audio\n"
	printf "   -o device=0|1	devices to use in the pipeline i.e first or second voutbg,\n"
	printf "                        imx-ipuv3-scale and imx-ipuv3-ovl device\n"
	printf "   -l			loop over and play the files in the direcory specified by FILE\n"
	printf "   -w l,t,w,h		rect position (left,top,width,height) only needed for ovl and xv\n\n\n"
	printf "Examples:\n"
	printf "    Start full screen playback.\n"
	printf "        dmo-video-play.sh -t fs -o 1 test.mp4\n"
	printf "    Start playback in hardware overlayed window.\n"
	printf "        dmo-video-play.sh -t ovl -o 1 -w 50,50,540,480 test.mp4\n"
	printf "    Start full screen playback loop.\n"
	printf "        dmo-video-play.sh -t fs -o 1 -l /videos\n\n\n"
}

sound=0
loop=0

while getopts "t:ao:lw:" Option
do
	case $Option in
		t ) tst=${OPTARG};;
		a ) sound=1;;
		o ) output_device=${OPTARG};;
		l ) loop=1;;
		w ) rect=${OPTARG};;
	esac
done
shift $(($OPTIND-1))

fn=$1

if [[ -z $fn || -z $tst || -z $output_device || ($tst != "fs" && -z $rect) ]]; then
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
printf "scale win: ${win[2]}x${win[3]}\n"


if [[ ${#win[@]} -ne 4 ]]; then
	printf "ERROR: Invalid window coordinates.\n"
	exit 1
fi

if [[ "${win[2]}" -lt "$min_scale_x" || "${win[2]}" -gt "$max_scale_x" ||
      "${win[3]}" -lt "$min_scale_y" || "${win[3]}" -gt "$max_scale_y" ]]; then
	printf "ERROR: Scaling limits exceeded.\n"
	exit 1
fi

fi # tst != fs

cmd="/usr/bin/gst-launch-1.0"

get_v4l2_devices

if [[ $output_device == 1 ]]; then
	vout=$vout1_dev
	scale=$scale1_plugin
	ovl=$ovl1_dev
else
	vout=$vout2_dev
	scale=$scale2_plugin
	ovl=$ovl2_dev
fi

case $tst in
	fs)
		func_name="play_fs $vout"
		;;
	xv)
		func_name="play_scale_xv $scale"
		;;
	ovl)
		func_name="play_scale_ovl $ovl $scale"
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
		$func_name $cmd $f $audio $win
	done
done	
fi

