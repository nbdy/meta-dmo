# Copyright (C) 2015 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require qupzilla.inc

DEPS_append += "qtwebengine"

PV = "2.0.2_qtwebengine+git${SRCPV}"

SRC_URI = " \
    git://github.com/QupZilla/${PN}.git;protocol=git;tag=v2.0.2 \
"
#SRCREV = "6e445dcc050d3569e69774444751f9c420344483"

S="${WORKDIR}/git"

LIC_FILES_CHKSUM = "\
    file://COPYRIGHT;md5=1d4ea452ed7ae778545f2dda5963c9fd \
"
