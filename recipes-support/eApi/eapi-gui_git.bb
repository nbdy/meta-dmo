# Copyright (C) 2017 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require eapi-gui.inc

SRC_URI = "git://git@emb.data-modul.com/userrepos/rya/eAPI-Gui;protocol=ssh;"

SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

