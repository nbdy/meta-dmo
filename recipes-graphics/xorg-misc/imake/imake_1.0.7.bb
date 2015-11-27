# Copyright (C) 2015 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

DESCRIPTION = "C preprocessor interface to the make utility"
HOMEPAGE = ""
LICENSE = "GPLv2"
SECTION = ""
DEPENDS = "xproto xorg-cf-files-native"
RDEPENDS_${PN} = "perl"

require recipes-graphics/xorg-util/xorg-util-common.inc

BBCLASSEXTEND = "native"

LIC_FILES_CHKSUM = "file://COPYING;md5=b9c6cfb044c6d0ff899eaafe4c729367"

SRC_URI[md5sum] = "186ca7b8ff0de8752f2a2d0426542363"
SRC_URI[sha256sum] = "6bda266a07eb33445d513f1e3c82a61e4822ccb94d420643d58e1be5f881e5cb"

