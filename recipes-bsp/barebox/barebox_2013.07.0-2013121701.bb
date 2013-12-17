require barebox.inc

PR = "${INC_PR}.21"
SRC_URI = " \
    ${DMOFTP}/${PN}-${PV}.tar.gz \
"

SRC_URI[md5sum] = "cebc3a1419c1c99e93a8bfa1f3991e65"
SRC_URI[sha256sum] = "7a4a3d7fc6d1b526a7aed2d25ed0eacbdb11098897ecf63f4d946b25e3a7c884"

COMPATIBLE_MACHINE = "imx6q-dmo-edm-qmx6"

do_configure_prepend_imx6q-dmo-edm-qmx6() {
    oe_runmake ${BAREBOX_MACHINE}
}
