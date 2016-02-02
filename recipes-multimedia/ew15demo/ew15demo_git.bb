# Copyright (C) 2015 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

inherit qmake5

inherit dmo-launcher-app

DMO_LAUNCHER_EXEC = "/usr/bin/ew15demo"
DMO_LAUNCHER_NAME = "Multitouch-Demo"
DMO_LAUNCHER_DESC = "Embedde World Multitouch Demo"
DMO_LAUNCHER_ICONPATH = "/usr/share/dmlauncher/icons/EW15demo.jpg"
DMO_LAUNCHER_ICONFILE = "EW15demo.jpg"

DESCRIPTION = "Data Modul qt5 multitouch demo"
HOMEPAGE = "http://www.data-modul.com"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SECTION = "graphics"
DEPENDS = "qtbase qtbase-native qtdeclarative"
RDEPENDS_${PN} = "qtbase qtdeclarative"

SRC_URI = " \
            git://git@emb.data-modul.com/userrepos/pst/ew15demo;protocol=ssh \
            file://EW15demo.jpg \
            "
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}/usr/bin
    install -d ${D}/usr/share/ew15demo/pictures
    install -m 755 ./ew15demo ${D}/usr/bin
    install ${S}/qml/photo/surfaceimages/* ${D}/usr/share/ew15demo/pictures
}

PACKAGES = "${PN} ${PN}-dbg"

FILES_${PN} += "/usr/bin/ew15demo"
FILES_${PN}-dbg += "${datadir}/${P}/.dbug"


