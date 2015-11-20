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
    qtwebkit \
"
DEPS += "${@base_contains('DISTRO_FEATURES', 'x11', 'libx11', '',  d)}"

RDEPENDS_{PN} = "${DEPS}"
DEPENDS = "${DEPS} qtx11extras qttools-native"


LIC_FILES_CHKSUM = " \
    file://GPLv3;md5=8f0e2cd40e05189ec81232da84bd6e1a \
    file://COPYRIGHT;md5=1d4ea452ed7ae778545f2dda5963c9fd \
"
MY_PN="QupZilla"
MY_P="${MY_PN}-${PV}"

SRC_URI = " \
    https://github.com/${MY_PN}/${PN}/releases/download/v${PV}/${MY_P}.tar.xz \
    file://0001-Fix-qtwebkit-check-macro-missing.patch \
"

SRC_URI[md5sum] = "a73b1b574b99988018a5ada62018fab9"
SRC_URI[sha256sum] = "9437fdf563a04951b8e515b12becf352d847f8ab938f5db8f443d1be90e8ef59"

S="${WORKDIR}/${MY_P}"

EXTRA_QMAKEVARS_PRE += "QMAKE_LRELEASE+=${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}/lrelease"

FILES_${PN} += "/usr/share/"

do_configure_prepend() {
    if [ ${@base_contains('DISTRO_FEATURES', 'x11', '', 'true',  d)} == true ]; then
        export NO_X11=true
    fi
}

