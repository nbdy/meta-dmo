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
PV = "${SRCPV}"

OE_QMAKE_QMAKE_ORIG = "${STAGING_BINDIR_NATIVE}/${QT_DIR_NAME}//qmake"
OE_QMAKE_QMAKE = "bin/qmake"

S="${WORKDIR}/git"

do_configure() {
    if [ ! -e ${B}/bin/qmake ]; then
        mkdir ${B}/bin
        ln -sf ${OE_QMAKE_QMAKE_ORIG} ${B}/bin/qmake
    fi
    
    ${B}/bin/qmake ${S}/dmlauncher.pro
}

do_install() {
    install -d ${D}/usr/bin
    install -d ${D}/usr/share/dmlauncher/apps
    install -m 755 ./dmlauncher ${D}/usr/bin

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'flase', d)}; then
        install -d ${D}/usr/lib/systemd/system/
        install -m 644 ${WORKDIR}/dmlauncher.service ${D}/usr/lib/systemd/system
    fi
}

PACKAGES = "${PN} ${PN}-dbg"

FILES_${PN} += "/usr/bin/dmlauncher /usr/share/dmlauncher/apps"
FILES_${PN}-dbg += "${datadir}/${P}/.dbug"

SYSTEMD_SERVICE_${PN} = "dmlauncher.service"

