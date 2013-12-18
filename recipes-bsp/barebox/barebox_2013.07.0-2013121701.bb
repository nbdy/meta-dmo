require barebox.inc

PR = "${INC_PR}.22"
SRC_URI = " \
    ${DMOFTP}/${PN}-${PV}.tar.gz \
"

SRC_URI[md5sum] = "7671f49da3850b1c7a64e62cf78fce76"
SRC_URI[sha256sum] = "94762712e1534ae11089e5f2372842915503fb374a42c1e7a2f651d7eaf1596a"

COMPATIBLE_MACHINE = "imx6q-dmo-edm-qmx6"

do_configure_prepend_imx6q-dmo-edm-qmx6() {
    oe_runmake ${BAREBOX_MACHINE}
}
