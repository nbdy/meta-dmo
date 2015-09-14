include gstreamer1.0-plugins-bad.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=73a5855a8119deb017f5f13cf327095d \
                    file://gst/tta/filters.h;beginline=12;endline=29;md5=8a08270656f2f8ad7bb3655b83138e5a \
                    file://COPYING.LIB;md5=21682e4e8fea52413fd26c60acb907e5 \
                    file://gst/tta/crc32.h;beginline=12;endline=29;md5=27db269c575d1e5317fffca2d33b3b50"

SRC_URI[md5sum] = "f15aab45962dd0cac34cf281a9c66fac"
SRC_URI[sha256sum] = "25b6c499716fc52a0aa1999628da2a4c3bba4c70f09dbe3ff38a2453abe0a8c0"

S = "${WORKDIR}/gst-plugins-bad-${PV}"

