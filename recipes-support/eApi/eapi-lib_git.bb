# Copyright (C) 2016 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require eapi-lib.inc

SRC_URI = "git://git@emb.data-modul.com/development/tools/eApi;protocol=ssh;branch=master"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git/make"

