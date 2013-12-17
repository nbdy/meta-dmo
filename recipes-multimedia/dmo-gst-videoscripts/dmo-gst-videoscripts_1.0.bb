# Copyright (C) 2013 Data-Modul AG
# Released under the MIT license

DESCRIPTION = "Some scripts to start video over gst-launch"
LICENSE = "MIT"
DEPENDS = "gstreamer"
LIC_FILES_CHKSUM = "file://${WORKDIR}/MIT-license.txt;md5=84c2f27af54cb640d5fa0a101671f386"
PR = "r3"
PACKAGE_ARCH = "all"

SRC_URI = " \
    file://MIT-license.txt \
    file://dmo-video-1920x1080.sh;name=video1080 \
    file://dmo-video-800x480.sh;name=video480 \
"

do_install () {
    install -d ${D}/usr/bin
    install -m 755 ${WORKDIR}/dmo-video-1920x1080.sh \
                   ${WORKDIR}/dmo-video-800x480.sh \
                   ${D}/usr/bin
}

SRC_URI[video1080.md5sum] = "af52c60bdc145c956233f469f4bba703"
SRC_URI[video1080.sha256] = "8c2af40b6fd42c534a71ee3a13b6bf2abadf330dd912868b6446dc535bbabf97"
SRC_URI[video480.md5sum] = "5904fa77351ad1b844363f016962b686"
SRC_URI[video480.sha256] = "8c557565cf1fda9181e4bfa6e1c9b8e5506c0eb6f46dab35ba7072fb81cfd8e7"

