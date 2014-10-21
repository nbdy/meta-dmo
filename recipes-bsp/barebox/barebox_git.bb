require barebox.inc

PV = "2014.10.0+git${SRCREV}"
SRC_URI = " \
    git://git@emb.data-modul.com/development/barebox;protocol=ssh;branch=sfr/release-2014.10;nocheckout=1;name=mx6 \
"
SRCREV_mx6="${AUTOREV}"

COMPATIBLE_MACHINE = "imx6q-dmo-edm-qmx6"

S = "${WORKDIR}/git"

do_configure_prepend_imx6q-dmo-edm-qmx6() {
    oe_runmake ${BAREBOX_MACHINE}
}
