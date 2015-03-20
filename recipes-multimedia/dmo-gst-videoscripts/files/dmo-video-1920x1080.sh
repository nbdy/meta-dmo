#!/bin/sh

file=$1
if [[ -z $file ]]; then
	echo "Usage: $0 <video file>"
	exit 1
fi

coda=coda-decoder-3

if [[ ! -L /dev/video/by-name/$coda ]]; then
	coda=coda-decoder-2
fi

/usr/bin/gst-launch-1.0  -v \
        filesrc location=$file \
        ! qtdemux name=d d.video_0 \
        ! h264parse \
        ! video/x-h264,stream-format=byte-stream \
        ! v4l2filter device=/dev/video/by-name/$coda min-queued=1 \
        ! video/x-raw,format=YV12,width=1920,height=1088 \
        ! identity sync=true \
        ! v4l2sink device=/dev/video/by-name/voutbg-0 crop-top=0 \
        crop-height=1080 overlay-height=1080 sync=false

