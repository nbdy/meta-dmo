# Copyright (C) 2015 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require qupzilla.inc

DEPS_append += "qtwebengine"

PV = "2.2.6_qtwebengine+git${SRCPV}"

SRC_URI = " \
    https://github.com/QupZilla/${PN}.git;tag=v2.2.6 \
"
#SRCREV = "6e445dcc050d3569e69774444751f9c420344483"

S="${WORKDIR}/git"

LIC_FILES_CHKSUM = "\
    file://COPYRIGHT;md5=74154cacbe9a1b3764db7c8158acb09a \
"
