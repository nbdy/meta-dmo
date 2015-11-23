# Copyright (C) 2015 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

inherit qmake5

DESCRIPTION = "QT5 webkit browser"
HOMEPAGE = "http://www.qupzilla.com/"
LICENSE = "GPL-3"
SECTION = "browser"

DEPS = " \
    qtbase \
    qtscript \
    qtwebengine \
"
DEPS += "${@base_contains('DISTRO_FEATURES', 'x11', 'libx11', '',  d)}"

RDEPENDS_{PN} = "${DEPS}"
DEPENDS = "${DEPS} qtx11extras qttools-native"


LIC_FILES_CHKSUM = " \
    file://GPLv3;md5=8f0e2cd40e05189ec81232da84bd6e1a \
    file://COPYRIGHT;md5=1d4ea452ed7ae778545f2dda5963c9fd \
"
PV = "1.8.6_qtwebengine+git${SRCPV}"

SRC_URI = " \
    git://github.com/QupZilla/${PN}.git \
"
SRCREV = "94d89e4fa68cea8957a51fefb0ff57d3786feb34"

S="${WORKDIR}/git"

EXTRA_QMAKEVARS_PRE += "QMAKE_LRELEASE+=${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}/lrelease"

FILES_${PN} += "/usr/share/"

do_configure_prepend() {
    if [ ${@base_contains('DISTRO_FEATURES', 'x11', '', 'true',  d)} == 'true' ]; then
        export NO_X11=true
    fi
}

