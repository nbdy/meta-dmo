require barebox.inc

SRC_URI = " \
    ${DMOFTP}/${PN}-${PV}.tar.gz \
"

SRC_URI[md5sum] = "391446d9c009175f1ecf4a2fd23ce87a"
SRC_URI[sha256sum] = "971ad644aa69e388d0e0358a57f36d160dae8c2cb6b6a12e2e002029a308b343"

COMPATIBLE_MACHINE = "imx6q-dmo-edm-qmx6"

do_configure_prepend_imx6q-dmo-edm-qmx6() {
    oe_runmake ${BAREBOX_MACHINE}
}
