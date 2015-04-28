# Copyright (C) 2015 Sebastian Wezel <swezel@data-modul.com>

DESCRIPTION = "Initramfs with systemd"
HOMEPAGE = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

RDEPENDS_${PN} = "udev"

SRC_URI = "git://git@emb.data-modul.com/development/initscripts;branch=master;protocol=ssh;name=initrd"
SRCREV_initrd = "${AUTOREV}"

S = "${WORKDIR}/git"
PV = "${SRCPV}"

do_install(){
    install -m 0755 ${S}/init-overlay.sh ${D}/init
}

FILES_${PN} += "/init"

