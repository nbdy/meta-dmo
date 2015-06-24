DEFAULT_PREFERENCE = "-1"

include gstreamer1.0-plugins-good.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://common/coverage/coverage-report.pl;beginline=2;endline=17;md5=a4e1830fce078028c8f0974161272607 \
                    file://gst/replaygain/rganalysis.c;beginline=1;endline=23;md5=b60ebefd5b2f5a8e0cab6bfee391a5fe"

S = "${WORKDIR}/git"

#SRC_URI = "git://github.com/Vodalys/gst-plugins-good.git;branch=veobox \
#	   file://0001-gst-plugins-good-v4l2sink-use-output-overlay.patch"

SRC_URI += "file://0001-gst-plugins-good-v4l2sink-use-output-overlay.patch "

# SRCREV = "bc2bd82c1b696711124fd55eaa6cd5adedd31a2f"
SRCREV = "37e3ca1447932633d521d29b722ce38f42c10618"
# ${AUTOREV}"

PR = "r14"

CFLAGS_append = " -Wno-error=unused-but-set-variable "

do_configure_prepend() {
	cd ${S}
	./autogen.sh --noconfigure
	cd ${B}
}

