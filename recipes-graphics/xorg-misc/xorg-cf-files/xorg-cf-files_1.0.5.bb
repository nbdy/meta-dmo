# Copyright (C) 2015 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Old Imake-related build files"
HOMEPAGE = ""
LICENSE = "GPLv2"
SECTION = ""
DEPENDS = "util-macros-native font-util-native"

require recipes-graphics/xorg-util/xorg-util-common.inc

BBCLASSEXTEND = "native"

LIC_FILES_CHKSUM = "file://COPYING;md5=0f334a06f2de517e37e86d6757167d88"

SRC_URI[md5sum] = "ccb5f341ed5932d5ae870d9128e37c32"
SRC_URI[sha256sum] = "8c9ce7952094d49a1d3f849e45caf848d83ad879d91f3169cc373f52502d0c20"

