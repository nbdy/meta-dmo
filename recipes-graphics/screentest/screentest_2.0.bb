# Copyright (C) 2017 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)
inherit autotools pkgconfig

DESCRIPTION = "CRT and LCD screen testing utility"
HOMEPAGE = "http://screentest.sourceforge.net/"
LICENSE = "GPL-2"
SECTION = "graphics"
DEPS = "glib-2.0 libglade gtk+"
DEPENDS = "${DEPS}"
RDEPENDS_${PN} = "${DEPS}"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = " \ 
    https://github.com/TobiX/${PN}/archive/${PV}.tar.gz \
"
SRC_URI[md5sum] = "8525c1b07c4a45c0e49df88e9916fe04"
SRC_URI[sha256sum] = "95418fa0936a8c9b6ffd8708d339d65a8ba9f66b937e3bd6b55dd0bdde85a561"

