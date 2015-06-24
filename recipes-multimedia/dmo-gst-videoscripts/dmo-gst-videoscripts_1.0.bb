# Copyright (C) 2013 Data-Modul AG
# Released under the MIT license

DESCRIPTION = "Some scripts to start video over gst-launch"
HOMEPAGE="https://emb.data-modul.com"
LICENSE = "MIT"
DEPENDS = "gstreamer"
LIC_FILES_CHKSUM = "file://${WORKDIR}/MIT-license.txt;md5=84c2f27af54cb640d5fa0a101671f386"
PACKAGE_ARCH = "all"

SRC_URI = " \
    file://MIT-license.txt \
    file://dmo-play2.sh;name=playback \
"

do_install () {
    install -d ${D}/usr/bin
    install -m 755 ${WORKDIR}/dmo-play2.sh \
                   ${D}/usr/bin
}

SRC_URI[playback.md5sum] = "8448648b7e428861b5b9928db5368550"
SRC_URI[playback.sha256] = "42206ab68b71be838eed180cac221844d1082396101fb489d917751d4789bb46"

