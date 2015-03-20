# Copyright (C) 2015 Silvio Fricke <silvio.fricke@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "disable or enable tty blanking"
HOMEPAGE = "https://emb.data-modul.com"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
DEPENDS = "bash"

SRC_URI = "file://blanking.sh"

do_install () {
    install -d ${D}/${sysconfdir}/{init,rc5}.d
    install -m 755 ${WORKDIR}/blanking.sh ${D}/etc/init.d
    ln -sf ../init.d/blanking.sh ${D}/${sysconfdir}/rc5.d/S99blanking
}

