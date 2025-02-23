include gstreamer1.0-plugins-good.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://common/coverage/coverage-report.pl;beginline=2;endline=17;md5=a4e1830fce078028c8f0974161272607 \
                    file://gst/replaygain/rganalysis.c;beginline=1;endline=23;md5=b60ebefd5b2f5a8e0cab6bfee391a5fe"

SRC_URI = " \
    http://gstreamer.freedesktop.org/src/gst-plugins-good/gst-plugins-good-${PV}.tar.xz \
    file://0001-gstrtpmp4gpay-set-dafault-value-for-MPEG4-without-co.patch \
    file://avoid-including-sys-poll.h-directly.patch \
    file://ensure-valid-sentinel-for-gst_structure_get.patch \
"
SRC_URI_append_mx6 += " \
    file://0001-Revert-v4l2src-check-for-valid-size-on-raw-video-buf.patch \
    file://gstDeviceMonitor_assert_being_off.patch \
    file://gstDiscover_empty_caps_SDVideo.patch \
"

SRC_URI[md5sum] = "473ebb1f15c67de99ddb6e4d027c0876"
SRC_URI[sha256sum] = "a1d6579ba203a7734927c24b90bf6590d846c5a5fcec01a48201018c8ad2827a"

S = "${WORKDIR}/gst-plugins-good-${PV}"

EXTRA_OECONF_mx6 += " --enable-v4l2-probe "
