# Copyright (C) 2017 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

inherit qmake5

DESCRIPTION = "dmo eApi gui"
HOMEPAGE = "www.data-modul.com"
LICENSE = "CLOSED"
SECTION = "gui"
DEPENDS = "qtbase qtbase-native eapi-lib"
RDEPENDS_${PN} = "qtbase eapi-lib"

SRC_URI = "git://git@emb.data-modul.com/userrepos/swe/eAPI-Gui;protocol=ssh;branch=swe/master"

SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

PACKAGES = "${PN} ${PN}-dbg"

do_configure_prepend() {
    export PREFIX=${STAGING_LIBDIR}
}


