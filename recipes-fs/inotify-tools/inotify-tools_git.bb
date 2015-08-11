# Copyright (C) 2015 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

inherit autotools

DESCRIPTION = "a set of command-line programs providing a simple interface to inotify"
HOMEPAGE = "https://github.com/rvoicilas/inotify-tools/wiki"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=ac6c26e52aea428ee7f56dc2c56424c6"
SECTION = "fs tools"

PV = "3.14+git${SRCPV}"

SRC_URI = " \ 
            git://github.com/rvoicilas/${PN}.git;protocol=https;branch=master \
          "

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

