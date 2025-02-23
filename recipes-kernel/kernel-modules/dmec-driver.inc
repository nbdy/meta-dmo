# Copyright (C) 2016 Sebastian Wezel <swezel@data-modul.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Kernel driver for dmo ec"
HOMEPAGE = "www.data-modul.com"
LICENSE = "CLOSED"
SECTION = "kernel"
DEPENDS = "virtual/kernel"

inherit module
KERNEL_MODULE_AUTOLOAD = "dmec i2c-dmec rtm-dmec gpio-dmec wdt-dmec acpi-dmec"
DMEC_TAG ?= ""

SRC_URI = "git://github.com/data-modul/dmec.git;protocol=https;branch=master"

S = "${WORKDIR}/git"

