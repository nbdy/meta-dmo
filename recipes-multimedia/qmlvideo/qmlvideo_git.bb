# Copyright (C) 2015 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

inherit dmo-launcher-app

DMO_LAUNCHER_EXEC = "/usr/bin/qt5/qmlscene /usr/share/qmlvideotest/qmlvideotest.qml --fullscreen"
DMO_LAUNCHER_NAME = "QT-Videoplayer-demo"
DMO_LAUNCHER_DESC = "Qml video test application"
DMO_LAUNCHER_ICONPATH = "/usr/share/cinematicexperience-1.0/content/images/qt_logo.png"

DESCRIPTION = "Data Modul qml vidoe test application"
HOMEPAGE = "http://www.data-modul.com"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SECTION = "graphics"
DEPENDS = "qtbase qtbase-native qtdeclarative"
RDEPENDS_${PN} = "qtbase qtdeclarative qtdeclarative-tools qtquickcontrols-qmlplugins bash"

SRC_URI = " \
    git://git@emb.data-modul.com/userrepos/pst/qmlvideotest;protocol=ssh \
    file://qmlvideo.sh \
"

SRCREV = "${AUTOREV}"
PV = "0.1.2+git${SRCPV}"

S="${WORKDIR}/git"

do_install() {
    install -d ${D}/usr/share/qmlvideotest
    install -m 755 ./qmlvideotest.qml ${D}/usr/share/qmlvideotest
    install -d ${D}/usr/bin
    install -m 755 ${WORKDIR}/qmlvideo.sh ${D}/usr/bin
}

PACKAGES = "${PN}"

FILES_${PN} += "/usr/share/qmlvideotest /usr/bin"

