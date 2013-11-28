require barebox.inc

PV = "2013.07.0+git${SRCREV}"
PR = "${INC_PR}.22"
SRC_URI = " \
    git://git@emb.data-modul.com/development/barebox;protocol=ssh \
"
SRCREV = "7e9a16e4b1dbf7bdf51abdab07d0dffebbe0befe"

COMPATIBLE_MACHINE = "imx6q-dmo-edm-qmx6"

S = "${WORKDIR}/git"

do_configure_prepend_imx6q-dmo-edm-qmx6() {
    oe_runmake ${BAREBOX_MACHINE}
}
