# Copyright (C) 2015 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require qupzilla.inc

DEPS_append += "qtwebengine"

PV = "1.9.0_qtwebengine+git${SRCPV}"

SRC_URI = " \
    git://github.com/QupZilla/${PN}.git \
"
SRCREV = "94d89e4fa68cea8957a51fefb0ff57d3786feb34"

S="${WORKDIR}/git"

