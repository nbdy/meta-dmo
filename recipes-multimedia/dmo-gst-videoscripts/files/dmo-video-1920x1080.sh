#!/bin/sh

video="$1"

if [ ! -e "${video}" ]; then
    echo "Videofile \"${video}\" does not exists"
    exit
fi

/usr/bin/gst-launch-0.11  -v \
    filesrc location="${video}" \
    ! qtdemux name=d d.video_0 \
    ! h264parse \
    ! video/x-h264,stream-format=byte-stream \
    ! v4l2filter device=/dev/video4 min-queued=1 \
    ! video/x-raw,format=YV12,width=1920,height=1088 \
    ! v4l2sink device=/dev/video3 crop-top=0  crop-height=1080 overlay-height=1080

