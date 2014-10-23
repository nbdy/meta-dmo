include gstreamer1.0.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6762ed442b3822387a51c92d928ead0d \
                    file://gst/gst.h;beginline=1;endline=21;md5=e059138481205ee2c6fc1c079c016d0d"

SRC_URI = " \
    http://gstreamer.freedesktop.org/src/gstreamer/gstreamer-${PV}.tar.xz \
    file://0001-Fix-crash-with-gst-inspect.patch \
    "
SRC_URI[md5sum] = "55d50f48a496799dbb0b195c9c6c6153"
SRC_URI[sha256sum] = "a4523d2471bca6cd0059a32e3b042f50faa4dadc6439852af8b43ca3f17d1fc9"
S = "${WORKDIR}/gstreamer-${PV}"

