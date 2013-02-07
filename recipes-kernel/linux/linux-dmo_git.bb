# Copyright (C) 2011-2012 Freescale Semiconductor
# Released under the MIT license (see COPYING.MIT for the terms)

require linux-dmo.inc

SRC_URI = "git://git@emb.data-modul.com/development/linux;protocol=ssh \
           file://defconfig \
"
KERNEL_PRIORITY = "10000"

PR = "${INC_PR}.1"

COMPATIBLE_MACHINE = "(mx6)"

# Revision of 1.1.0 tag
SRCREV = "ca17f1fc8b9901394ae70bc229ff026db917c324"
LOCALVERSION = "-1.1.0+yocto"

