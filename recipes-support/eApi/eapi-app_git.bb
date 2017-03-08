# Copyright (C) 2016 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require eapi-app.inc

SRC_URI = "git://git@emb.data-modul.com/userrepos/rya/eAPI-App;protocol=ssh;"
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"
S = "${WORKDIR}/git/make"

