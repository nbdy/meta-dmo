#!/bin/sh

file=$1
if [[ -z $file ]]; then
	echo "Usage: $0 <video file>"
	exit 1
fi

coda=coda-decoder-3
vout=voutbg-1

if [[ ! -L /dev/video/by-name/$coda ]]; then
	coda=coda-decoder-2
fi

if [[ ! -L /dev/video/by-name/$vout ]]; then
	vout=voutbg-0
fi

/usr/bin/gst-launch-1.0  -v \
        filesrc location=$file \
        ! qtdemux name=d d.video_0 \
        ! h264parse \
        ! video/x-h264,stream-format=byte-stream \
        ! v4l2filter device=/dev/video/by-name/$coda min-queued=1 \
        ! video/x-raw,format=YV12,width=800,height=480 \
        ! identity sync=true \
        ! v4l2sink device=/dev/video/by-name/$vout crop-top=0 \
        crop-height=480 overlay-height=480 sync=false

