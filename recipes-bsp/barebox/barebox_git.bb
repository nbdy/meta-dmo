require barebox.inc

PV = "2015.08.0+git${SRCPV}"
#SRC_URI = "git://github.com/data-modul/barebox.git;protocol=https;branch=dmo-release-2015.02;name=mx6"
SRC_URI = "git://git@emb.data-modul.com/development/barebox;branch=swe/201508/initramfs;protocol=ssh;name=mx6"

SRCREV_mx6="${AUTOREV}"

COMPATIBLE_MACHINE = "(imx6q-dmo-edm-qmx6|imx6dl-dmo-edm-qmx6)"

S = "${WORKDIR}/git"

do_configure_prepend() {
    oe_runmake ${BAREBOX_MACHINE}
}

