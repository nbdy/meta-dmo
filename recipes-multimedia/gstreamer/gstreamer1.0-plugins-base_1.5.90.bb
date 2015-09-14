include gstreamer1.0-plugins-base.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=c54ce9345727175ff66d17b67ff51f58 \
                    file://common/coverage/coverage-report.pl;beginline=2;endline=17;md5=a4e1830fce078028c8f0974161272607 \
                    file://COPYING.LIB;md5=6762ed442b3822387a51c92d928ead0d \
                   "

SRC_URI += "file://get-caps-from-src-pad-when-query-caps.patch \
            file://taglist-not-send-to-down-stream-if-all-the-frame-cor.patch \
"

SRC_URI[md5sum] = "6274aa94f3a5bab24ff949601735745c"
SRC_URI[sha256sum] = "9ba9d103079124c09f3d2b4062e072406d7a0019323a3f2119129db3bdff9c00"

S = "${WORKDIR}/gst-plugins-base-${PV}"
