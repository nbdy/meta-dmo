include gstreamer1.0-plugins-base.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=c54ce9345727175ff66d17b67ff51f58 \
                    file://common/coverage/coverage-report.pl;beginline=2;endline=17;md5=a4e1830fce078028c8f0974161272607 \
                    file://COPYING.LIB;md5=6762ed442b3822387a51c92d928ead0d \
                   "
SRC_URI[md5sum] = "2e75fb03ed7e4d24d88601fcac4d57aa"
SRC_URI[sha256sum] = "de2444a5c150d4e4b680364d7c0414cd8b015d95b305ff65d65a17683379532f"
S = "${WORKDIR}/gst-plugins-base-${PV}"
