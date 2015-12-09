DEFAULT_PREFERENCE = "-1"

include gstreamer1.0-plugins-bad.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=73a5855a8119deb017f5f13cf327095d \
                    file://gst/tta/filters.h;beginline=12;endline=29;md5=8a08270656f2f8ad7bb3655b83138e5a \
                    file://COPYING.LIB;md5=21682e4e8fea52413fd26c60acb907e5 \
                    file://gst/tta/crc32.h;beginline=12;endline=29;md5=27db269c575d1e5317fffca2d33b3b50"

GSTREAMER_1_0_GIT_BRANCH = "1.6"
S = "${WORKDIR}/git"

SRC_URI_remove_mx6 = " file://0001-PATCH-install-gstaggregator-and-gstvideoaggregator-h.patch"

PACKAGECONFIG[sbc] = "--enable-sbc,--disable-sbc,sbc"
PACKAGECONFIG[hls] = "--enable-hls,--disable-hls,gnutls"

EXTRA_OECONF += " \
    -disable-openjpeg \
    "

do_configure_prepend() {
	cd ${S}
	./autogen.sh --noconfigure
	cd ${B}
}

