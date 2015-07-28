# Copyright (C) 2015 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

inherit native
inherit autotools

DESCRIPTION = "convert vpu firmware"
HOMEPAGE = "http://www.data-modul.com"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SECTION = "converter"
DEPENDS = ""
SRCREV = "${AUTOREV}"
PV = "${SRCPV}"

SRC_URI = "git://git@emb.data-modul.com/development/tools/convert-vpu-firmware;protocol=ssh"

S="${WORKDIR}/git"

