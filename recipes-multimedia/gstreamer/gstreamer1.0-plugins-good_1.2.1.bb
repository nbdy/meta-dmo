include gstreamer1.0-plugins-good.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://common/coverage/coverage-report.pl;beginline=2;endline=17;md5=a4e1830fce078028c8f0974161272607 \
                    file://gst/replaygain/rganalysis.c;beginline=1;endline=23;md5=b60ebefd5b2f5a8e0cab6bfee391a5fe"

SRC_URI[md5sum] = "082156e9fb737ee6132b6b663f2901a1"
SRC_URI[sha256sum] = "660fa02dbe01086fcf702d87acc0ba5dde2559d6a11ecf438874afe504c50517"
S = "${WORKDIR}/gst-plugins-good-${PV}"

