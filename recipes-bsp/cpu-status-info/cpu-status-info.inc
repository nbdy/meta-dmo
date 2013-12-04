DESCRIPTION = "cpu status information"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

CROSS_COMPILER = "${HOST_PREFIX}"

COMPATIBLE_MACHINE = "(mx6)"

do_install () {
    install -d ${D}/usr/bin

    install ${S}/cpu-status-info ${D}/usr/bin
}
