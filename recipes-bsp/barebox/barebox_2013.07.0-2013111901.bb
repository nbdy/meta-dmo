require barebox.inc

PR = "${INC_PR}.21"
SRC_URI = " \
    ${DMOFTP}/${PN}-${PV}.tar.gz \
"

SRC_URI[md5sum] = "340535231361ed36877e1d93d4145f05"
SRC_URI[sha256sum] = "183d665e0cfd337f50fba483712750031dbcfec25b5029f80c5835a6c2986914"

COMPATIBLE_MACHINE = "imx6q-dmo-edm-qmx6"

do_configure_prepend_imx6q-dmo-edm-qmx6() {
    oe_runmake ${BAREBOX_MACHINE}
}
