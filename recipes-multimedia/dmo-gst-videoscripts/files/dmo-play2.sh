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
echo "$cmd $fn $odev $aud"

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

echo "$cmd $fn $ovldev $scdev $aud"

aud_append=" "
if [[ $aud != "no" ]]; then
aud_append="demux.audio_0 \
	    ! queue \
	    ! aacparse ! faad ! alsasink "
fi

DISPLAY=:0

$cmd -v filesrc location=$fn \
		! qtdemux name=demux $aud_append \
		demux.video_0 \
		! queue  \
		! h264parse \
		! video/x-h264,stream-format=byte-stream \
		! $dec_plugin capture-io-mode=dmabuf \
		! $scdev output-io-mode=dmabuf-import capture-io-mode=dmabuf \
		! video/x-raw,format=RGB,width=540,height=300 \
		! v4l2sink device=$ovldev io-mode=dmabuf-import sync=true \
		overlay-left=450 overlay-top=300 overlay-width=540 overlay-height=300

}


# scale and output in ximagesink i.e. software overlay
function play_scale_xv
{
scdev=$1
cmd=$2
fn=$3
aud=$4

echo "$cmd $fn $scdev $aud"

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
		! video/x-raw,width=480,height=320 \
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



printf "vout1: \033[1;33m$vout1_dev\033[0m\nvout2: \033[1;33m$vout2_dev\033[0m\n"
printf "ovl1: \033[1;33m$ovl1_dev\033[0m\novl2: \033[1;33m$ovl2_dev\033[0m\n"
printf "scale1: \033[1;33m$scale1_dev ($scale1_plugin)\033[0m\nsclae2: \033[1;33m$scale2_dev ($scale2_plugin)\033[0m\n"
printf "decoder: \033[1;33m$dec_dev ($dec_plugin)\033[0m\nencoder: \033[1;33m$enc_dev\033[0m\n"

}

function check_for_sound
{
soundcard=`cat /proc/asound/cards`
if [[ $soundcard == "--- no soundcards ---" ]]; then
	echo "sound: no"
	audio="no"
else
	echo "sound: yes"
fi
echo "==============================="
}

function usage
{
	echo "Usage: $0 <video file> <test=fs|ovl|xv> <audio=on|off> <output_device=1|2> <loop>"
}

fn=$1
tst=$2
sound=$3
output_device=$4
loop=$5

if [[ -z $fn || -z $tst || -z $sound ||-z $output_device ]]; then
	usage
	exit 1
fi

# export GST_DEBUG=v4l2videodec:5,v4l2*:8
#,v4l2allocator:5
# export GST_DEBUG=qtdemux*:5,h264parse*:5,v4l*:5
# export GST_DEBUG=h264parse*:5
# export GST_DEBUG=v4l2*:5
# . /gst-env.sh /gst-git
# trace-cmd record -e v4l2:*
# cmd="trace-cmd record -e coda:* /usr/bin/gst-launch-1.0"
cmd="$DBG_GST_PBASE/usr/bin/gst-launch-1.0"


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
	*)
		func_name="play_fs"
esac

echo ""
echo "$func_name"

if [[ $sound == "on" ]]; then
	check_for_sound
else
	audio="no"
fi

if [[ -z $loop ]]; then
	$func_name $cmd $fn $audio
else
cd $fn
while [[ true ]]; do
	for f in `ls -1`; do
		$func_name $cmd $f $audio
	done
done	
fi

