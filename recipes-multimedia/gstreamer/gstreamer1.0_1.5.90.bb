include gstreamer1.0.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6762ed442b3822387a51c92d928ead0d \
                    file://gst/gst.h;beginline=1;endline=21;md5=e059138481205ee2c6fc1c079c016d0d"

SRC_URI = " \
    http://gstreamer.freedesktop.org/src/gstreamer/gstreamer-${PV}.tar.xz \
    file://0001-Fix-crash-with-gst-inspect.patch \
"

SRC_URI[md5sum] = "6940aa481751bab647c16a07f9e479c8"
SRC_URI[sha256sum] = "7cd8094880b5dc4b7f457a9288b611c15aaf1b9cf4a63da49c821ab687eb56ed"


S = "${WORKDIR}/gstreamer-${PV}"

