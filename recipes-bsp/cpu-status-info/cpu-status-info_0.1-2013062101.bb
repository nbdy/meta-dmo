DESCRIPTION = "cpu status information"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

PR = "r1"

SRC_URI = " \
    ${DMOFTP}/${PN}-${PV}.tar.gz \
"
SRC_URI[md5sum] = "b956b9a87040f6159d891941e70870f4"
SRC_URI[sha256sum] = "53538485e615ef6fe3f2da9983226900c36037b10f43b1c301365d245ec1920b"

CROSS_COMPILER = "${HOST_PREFIX}"

do_install () {
    install -d ${D}/usr/bin

    install ${S}/cpu-status-info ${D}/usr/bin
}

