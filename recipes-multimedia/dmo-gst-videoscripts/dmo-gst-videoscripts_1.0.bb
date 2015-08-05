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

SRC_URI[playback.md5sum] = "723fcdaa332ec944a9f157a359e9ee11"
SRC_URI[playback.sha256] = "df363768933748163f31ee4cf853b6e893c4374c8e29a555e80948b61267cc47"

