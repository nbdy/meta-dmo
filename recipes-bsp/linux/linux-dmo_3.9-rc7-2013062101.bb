# Copyright (C) 2011-2012 Freescale Semiconductor
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "${INC_PR}.8"

require linux-dmo.inc

SRC_URI = "${DMOFTP}/${PN}-${PV}.tar.gz;name=archive \
           file://defconfig;name=config \
           file://fsl_otp-compile-problem.patch;name=patch1 \
"
SRC_URI[archive.md5sum] = "997c01fc07ec0c2af466a26e1408445c"
SRC_URI[archive.sha256sum] = "3ceb7d1eca1721ffe16de7b3a6f1ffd04acbeec88d88b0ae37ac1ceb64b9a8fa"

KERNEL_PRIORITY = "10000"

COMPATIBLE_MACHINE = "(mx6)"
