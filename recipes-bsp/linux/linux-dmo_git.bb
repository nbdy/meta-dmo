# Copyright (C) 2011-2012 Freescale Semiconductor
# Released under the MIT license (see COPYING.MIT for the terms)

PV = "3.9rc7+git${SRCREV}"
PR = "${INC_PR}.8"

require linux-dmo.inc

SRC_URI = "git://git@emb.data-modul.com/development/linux;protocol=ssh \
           file://defconfig \
           file://fsl_otp-compile-problem.patch \
"
KERNEL_PRIORITY = "10000"

COMPATIBLE_MACHINE = "(mx6)"

SRCREV = "d0128be0e4e8435df49209ad8a505edbaf32c1fb"

