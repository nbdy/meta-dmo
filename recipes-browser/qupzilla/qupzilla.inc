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
"
DEPS += "${@base_contains('DISTRO_FEATURES', 'x11', 'libx11 qtx11extras', '',  d)}"

RDEPENDS_{PN} = "${DEPS}"
DEPENDS = "${DEPS} qttools-native"

LIC_FILES_CHKSUM = " \
    file://GPLv3;md5=8f0e2cd40e05189ec81232da84bd6e1a \
    file://COPYRIGHT;md5=1d4ea452ed7ae778545f2dda5963c9fd \
"
MY_PN="QupZilla"
MY_P="${MY_PN}-${PV}"

EXTRA_QMAKEVARS_PRE += "QMAKE_LRELEASE+=${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}/lrelease"

FILES_${PN} += "/usr/share/"

do_configure_prepend() {
    if [ ${@base_contains('DISTRO_FEATURES', 'x11', '', 'true',  d)} == true ]; then
        export NO_X11=true
    fi
}

do_install_append_x86-64() {
    if [ -d ${D}/usr/lib/x86_64-linux-gnu/ ]; then
        mv ${D}/usr/lib/x86_64-linux-gnu/* ${D}/usr/lib 
        rmdir ${D}/usr/lib/x86_64-linux-gnu/
    fi
}

