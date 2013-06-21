DESCRIPTION = "cpu status information"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

PV = "0.1+git${SRCREV}"
PR = "r1"

SRC_URI = " \
    git://git@emb.data-modul.com/userrepos/jgp/cpu-status-info;protocol=ssh \
"
SRCREV = "8fbc58d5c92422f9c928509b43a6b9b8ca49be66"

CROSS_COMPILER = "${HOST_PREFIX}"

S = "${WORKDIR}/git"

do_install () {
    install -d ${D}/usr/bin

    install ${S}/cpu-status-info ${D}/usr/bin
}

