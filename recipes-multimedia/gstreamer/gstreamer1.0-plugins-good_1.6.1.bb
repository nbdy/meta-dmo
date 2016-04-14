DEFAULT_PREFERENCE = "-1"

include gstreamer1.0-plugins-good.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://gst/replaygain/rganalysis.c;beginline=1;endline=23;md5=b60ebefd5b2f5a8e0cab6bfee391a5fe"

GSTREAMER_1_0_GIT_BRANCH = "1.6"
GSTREAMER_TAR = "true"
S = "${WORKDIR}/gst-plugins-good-${PV}"
SRC_URI[md5sum] = "a930f5ff133f17baf39d2876628b7890"
SRC_URI[sha256sum] = "86d4b814099f7b7b16be19d4b94fa41002ac01fdf1525b07c5764d54c0605935"

SRC_URI += "file://0001-gst-plugins-good-v4l2sink-use-output-overlay.patch "

CFLAGS_append = " -Wno-error=unused-but-set-variable "

do_configure_prepend() {
	cd ${S}
	./autogen.sh --noconfigure
	cd ${B}
}

