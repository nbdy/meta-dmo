require recipes-bsp/barebox/barebox.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = " \
    git://git@emb.data-modul.com/development/barebox;branch=rya/imx6-ppc/next-release-2017.04;protocol=ssh;name=mx6 \
"

PV = "2017.04+git${SRCPV}"
SRCREV_mx6 = "${AUTOREV}"

COMPATIBLE_MACHINE = "(imx6dl-dmo-ppc|imx6q-dmo-ppc)"

BBENV_BUILD_DIR = "${WORKDIR}/build_env/"

S = "${WORKDIR}/git"

do_configure_prepend_mx6() {
    oe_runmake ${BAREBOX_MACHINE}
}
