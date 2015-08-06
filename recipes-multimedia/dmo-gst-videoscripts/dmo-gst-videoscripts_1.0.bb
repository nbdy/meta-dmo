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
    file://dmo-video-play.sh;name=playback \
"

do_install () {
    install -d ${D}/usr/bin
    install -m 755 ${WORKDIR}/dmo-play2.sh \
                   ${D}/usr/bin
}

SRC_URI[playback.md5sum] = "33d1640db1a1b15c10eadc0545250eb9"
SRC_URI[playback.sha256] = "439bb66aedec101f2041177b68277cd58e621ea0a07edb42c2a30a8cc9903c6c"

