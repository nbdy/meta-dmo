DESCRIPTION = "dmctouch -- meta-package for dmc touches driver and tools"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

PR="r2"

inherit packagegroup

RDEPENDS_${PN} = " \
    dmctouch-kernelmodules \
    dmctouch-tools \
"

