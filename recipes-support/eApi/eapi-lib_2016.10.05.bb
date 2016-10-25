# Copyright (C) 2016 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "dmo eApi lib"
HOMEPAGE = "www.data-modul.com"
LICENSE = "CLOSED"
SECTION = "kernel"
PARALLEL_MAKE = ""

SRC_URI = "git://github.com/data-modul/eAPI;protocol=git;tag=dmo-v${PV}"

S = "${WORKDIR}/git/make"

do_install() {
    install -d ${D}/${libdir}
    install -d ${D}/${includedir}
    install -m 0774 ${S}/bin/Linux/x86_64/rel/libEApi.so.1.0.nonstripped ${D}/${libdir}/libEApi.so.1.0
    ln -s libEApi.so.1.0 ${D}/${libdir}/libEApi.so.1
    ln -s libEApi.so.1.0 ${D}/${libdir}/libEApi.so
    install -m 0755 ${WORKDIR}/git/include/*.h ${D}/${includedir}
}

