# Copyright (C) 2015 Silvio Fricke <silvio.fricke@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "disable or enable tty blanking"
HOMEPAGE = "https://emb.data-modul.com"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
RDEPENDS = "bash"

inherit systemd

SYSTEMD_SERVICE_${PN} = "blanking.service"

SRC_URI = "file://blanking.sh \
           file://blanking.service "

do_install () {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
        install -d ${D}/${sysconfdir}/{init,rc5}.d
        install -m 755 ${WORKDIR}/blanking.sh ${D}/etc/init.d
        ln -sf ../init.d/blanking.sh ${D}/${sysconfdir}/rc5.d/S99blanking
    elif ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}/usr/bin
        install -m 755 ${WORKDIR}/blanking.sh ${D}/usr/bin
        install -d ${D}/usr/lib/systemd/system/
        install -m 644 ${WORKDIR}/blanking.service ${D}/usr/lib/systemd/system/
    fi
}

