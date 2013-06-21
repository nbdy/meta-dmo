# Copyright (C) 2013 Data-Modul AG
# Released under the MIT license

DESCRIPTION = "Some scripts to start video over gst-launch"
LICENSE = "MIT"
DEPENDS = "gstreamer"
LIC_FILES_CHKSUM = "file://${WORKDIR}/MIT-license.txt;md5=84c2f27af54cb640d5fa0a101671f386"
PR = "r0"
PACKAGE_ARCH = "all"

SRC_URI = " \
    file://MIT-license.txt \
    file://dmo-video-1920x1080.sh \
    file://dmo-video-800x480.sh \
"

do_install () {
    install -d ${D}/usr/bin
    install -m 755 ${WORKDIR}/dmo-video-1920x1080.sh \
                   ${WORKDIR}/dmo-video-800x480.sh \
                   ${D}/usr/bin
}
