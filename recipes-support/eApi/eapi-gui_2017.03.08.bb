# Copyright (C) 2017 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require eapi-gui.inc

SRC_URI = "git://github.com/data-modul/eAPIApp-Gui.git;protocol=https;tag=dmo-v${PV}"

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

