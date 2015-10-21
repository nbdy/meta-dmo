# Copyright (C) 2015 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

inherit qmake5
inherit systemd

DESCRIPTION = "Data Modul welcome lanucher"
HOMEPAGE = "http://www.data-modul.com"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SECTION = "graphics"
DEPENDS = "qtbase qtbase-native qtdeclarative"
RDEPENDS_${PN} = "qtbase qtdeclarative"

SRC_URI = " \
            git://git@emb.data-modul.com/userrepos/pst/dmlauncher;protocol=ssh \
            file://dmlauncher.service \
            "
SRCREV = "${AUTOREV}"
PV = "0.3.0+git${SRCPV}"

S = "${WORKDIR}/git"

SRC_URI_append_dmo-x11 += "file://0001-x11-qtplatform-xcb.patch;patchdir=.."

do_install() {
    install -d ${D}/usr/bin
    install -d ${D}/usr/share/dmlauncher/apps
    install -m 755 ./dmlauncher ${D}/usr/bin

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'flase', d)}; then
        install -d ${D}${systemd_unitdir}/system/
        install -m 644 ${WORKDIR}/dmlauncher.service ${D}${systemd_unitdir}/system
    fi
}

PACKAGES = "${PN} ${PN}-dbg"

FILES_${PN} += "/usr/bin/dmlauncher /usr/share/dmlauncher/apps"
FILES_${PN}-dbg += "${datadir}/${P}/.dbug"

SYSTEMD_SERVICE_${PN} = "dmlauncher.service"

