require barebox.inc

PV = "2016.02.0+git${SRCPV}"
SRC_URI = "git://git@emb.data-modul.com/development/barebox;branch=zdo/201602/next-release;protocol=ssh;name=mx6"

SRCREV_mx6="${AUTOREV}"

COMPATIBLE_MACHINE = "(imx6q-dmo-edm-qmx6|imx6dl-dmo-edm-qmx6)"

S = "${WORKDIR}/git"

do_configure_prepend() {
    oe_runmake ${BAREBOX_MACHINE}
}

