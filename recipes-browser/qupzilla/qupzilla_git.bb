# Copyright (C) 2015 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require qupzilla.inc

DEPS_append += "qtwebengine"

PV = "2.0.0_qtwebengine+git${SRCPV}"

SRC_URI = " \
    git://github.com/QupZilla/${PN}.git \
"
SRCREV = "e5460538c8cd6e6c321a69804e06a65631205234"

S="${WORKDIR}/git"

