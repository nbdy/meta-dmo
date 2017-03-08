# Copyright (C) 2016 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "dmo eApi test app"
HOMEPAGE = "www.data-modul.com"
LICENSE = "CLOSED"
SECTION = "kernel"
DEPENDS = "eapi-lib"
RDEPENDS_${PN} = "eapi-lib"
PARALLEL_MAKE = ""

do_compile_prepend() {
    export PREFIX=${STAGING_LIBDIR}
}

do_install() {
    install -d ${D}/usr/bin
    install -m 755 ${S}/bin/Linux/${TARGET_ARCH}/rel/EApiValidateAPI.nonstripped ${D}/usr/bin/EApiValidateAPI
}
