# Copyright (C) 2016 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require eapi-app.inc

SRC_URI = "git://github.com/data-modul/eAPIApp;protocol=git;tag=dmo-v${PV}"
S = "${WORKDIR}/git/make"

