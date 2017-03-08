# Copyright (C) 2017 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

inherit qmake5

DESCRIPTION = "dmo eApi gui"
HOMEPAGE = "www.data-modul.com"
LICENSE = "CLOSED"
SECTION = "gui"
DEPENDS = "qtbase qtbase-native eapi-lib"
RDEPENDS_${PN} = "qtbase eapi-lib"

PACKAGES = "${PN} ${PN}-dbg"

do_configure_prepend() {
    export PREFIX=${STAGING_LIBDIR}
}

do_install() {
    install -d ${D}/usr/bin
    install -m 755 ${SEPB}/eAPI-DM-Utility ${D}/usr/bin/
}
