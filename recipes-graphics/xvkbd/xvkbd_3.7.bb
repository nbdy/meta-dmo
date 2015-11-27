# Copyright (C) 2015 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "virtual keyboard for X window system"
HOMEPAGE = "http://homepage3.nifty.com/tsato/xvkbd/"
LICENSE = "GPL-2"
SECTION = "graphic"
DEPS = "libx11 libxaw7 libxaw3d libxmu libxt libxtst" 
RDEPENDS_${PN} = "${DEPS}"
DEPENDS = "${DEPS} xproto xextproto inputproto imake-native"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "http://homepage3.nifty.com/tsato/xvkbd/${P}.tar.gz"

SRC_URI[md5sum] = "2f0b5c91b701ea548883607d7b0ad8ec"
SRC_URI[sha256sum] = "895f768269820e7c0379b0daac3687bda0c670d192ebb7e61e5613a5939ec90b"

do_configure(){
    CC=gcc xmkmf -a
}

do_install(){
    make BINDIR="/usr/bin" XAPPLOADDIR="/usr/share/X11/app-defaults" DESTDIR="${D}" install
}

FILES_${PN} = "/usr/bin /usr/share"

