# Copyright (C) 2011-2012 Freescale Semiconductor
# Released under the MIT license (see COPYING.MIT for the terms)

PV = "3.9rc7+git${SRCREV}"
PR = "${INC_PR}.6"

require linux-dmo.inc

SRC_URI = "git://git@emb.data-modul.com/development/linux;protocol=ssh \
           file://defconfig \
           file://fsl_otp-compile-problem.patch \
"
KERNEL_PRIORITY = "10000"

COMPATIBLE_MACHINE = "(mx6)"

# Revision of 1.1.0 tag
SRCREV = "340ccdb3590980574aa1a6ad1091972d8bf2435f"

