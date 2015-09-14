include gstreamer1.0-plugins-good.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://common/coverage/coverage-report.pl;beginline=2;endline=17;md5=a4e1830fce078028c8f0974161272607 \
                    file://gst/replaygain/rganalysis.c;beginline=1;endline=23;md5=b60ebefd5b2f5a8e0cab6bfee391a5fe"

SRC_URI += "file://0001-gst-plugins-good-v4l2sink-use-output-overlay.patch "

SRC_URI[md5sum] = "f2ab995f5b68239a987f6a3a4e76bbbd"
SRC_URI[sha256sum] = "cbaaa238f7a51f50090d86b6b31136bff133d1f8ffc02532dcbd5724dfd43fee"

S = "${WORKDIR}/gst-plugins-good-${PV}"
