DEFAULT_PREFERENCE = "-1"

include gstreamer1.0-plugins-good.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://gst/replaygain/rganalysis.c;beginline=1;endline=23;md5=b60ebefd5b2f5a8e0cab6bfee391a5fe"

GSTREAMER_1_0_GIT_BRANCH = "1.6"
S = "${WORKDIR}/git"

SRC_URI += "file://0001-gst-plugins-good-v4l2sink-use-output-overlay.patch "

CFLAGS_append = " -Wno-error=unused-but-set-variable "

do_configure_prepend() {
	cd ${S}
	./autogen.sh --noconfigure
	cd ${B}
}

