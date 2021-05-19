require recipes-bsp/barebox/barebox.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = " \
    git://github.com/data-modul/barebox-imx6.git;branch=dmo-release-2017.04;protocol=https;name=mx6 \
"

PV = "2017.04+git${SRCPV}"
SRCREV_mx6 = "${AUTOREV}"

COMPATIBLE_MACHINE = "(imx6dl-dmo-ppc|imx6q-dmo-ppc)"

BBENV_BUILD_DIR = "${WORKDIR}/build_env/"

S = "${WORKDIR}/git"

do_configure_prepend_mx6() {
    oe_runmake ${BAREBOX_MACHINE}
}
