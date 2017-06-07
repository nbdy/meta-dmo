# Copyright (C) 2013 Data-Modul AG
# Released under the MIT license

DESCRIPTION = "Some scripts to start video over gst-launch"
HOMEPAGE="https://emb.data-modul.com"
LICENSE = "MIT"
DEPENDS = "gstreamer1.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/MIT-license.txt;md5=84c2f27af54cb640d5fa0a101671f386"
PACKAGE_ARCH = "all"

SRC_URI = " \
    file://MIT-license.txt \
    file://arm-video-play.sh;name=arm \
    file://intel-video-play.sh;name=intel \
"

do_install () {
    install -d ${D}/usr/bin
}

do_install_append_dmo-edm-comb() {
    install -m 755 ${WORKDIR}/intel-video-play.sh \
                   ${D}/usr/bin/dmo-video-play.sh
}

do_install_append_mx6() {
    install -m 755 ${WORKDIR}/arm-video-play.sh \
                   ${D}/usr/bin/dmo-video-play.sh
}

SRC_URI[arm.md5sum] = "33d1640db1a1b15c10eadc0545250eb9"
SRC_URI[arm.sha256] = "439bb66aedec101f2041177b68277cd58e621ea0a07edb42c2a30a8cc9903c6c"
SRC_URI[intel.md5sum] = "8572f060be6793211185aea53bce7879"
SRC_URI[intel.sha256] = "7d53fe3688a393c2ea59d73f1722e8aef5e9d3507ad7899c752bf1beb83a5fda"

