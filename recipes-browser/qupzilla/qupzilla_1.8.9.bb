# Copyright (C) 2015 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require qupzilla.inc

DEPS_append += "qtwebkit"

SRC_URI = " \
    https://github.com/${MY_PN}/${PN}/releases/download/v${PV}/${MY_P}.tar.xz \
    file://0001-Fix-qtwebkit-check-macro-missing.patch \
"
SRC_URI[md5sum] = "9a0f8179fbf3d100942c4d1688ee701a"
SRC_URI[sha256sum] = "620ffb10a3f6b3f596c2c38a84d9c320cb66dd2d5e6701e8e31945e26308fcac"

S="${WORKDIR}/${MY_P}"

