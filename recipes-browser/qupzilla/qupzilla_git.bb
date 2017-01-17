# Copyright (C) 2015 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require qupzilla.inc

DEPS_append += "qtwebengine"

PV = "2.0.1_qtwebengine+git${SRCPV}"

SRC_URI = " \
    git://github.com/QupZilla/${PN}.git \
"
SRCREV = "3274634539fbe18df32de247eefa69122f650f0c"

S="${WORKDIR}/git"

