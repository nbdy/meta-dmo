require recipes-bsp/barebox/barebox.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = " \
    git://git@emb.data-modul.com/development/barebox;branch=rya/imx6-ppc/next-release-2017.04;protocol=ssh;name=mx6 \
"

PV = "2017.04+git${SRCPV}"
SRCREV_mx6 = "${AUTOREV}"

COMPATIBLE_MACHINE = "(imx6dl-dmo-ppc|imx6q-dmo-ppc)"

PACKAGES_append += "${PN}-bbenv"
FILES_${PN}-bbenv = "/bareboxenv"
BBENV_BUILD_DIR = "${WORKDIR}/build_env/"

S = "${WORKDIR}/git"

do_configure_prepend_mx6() {
    oe_runmake ${BAREBOX_MACHINE}
}

do_compile_append() {
    bbnote "Build the display environment variable"

    install -d ${BBENV_BUILD_DIR}/env/nv
    echo "HDMI" > ${BBENV_BUILD_DIR}/env/nv/display.type

    ${S}/scripts/bareboxenv -s -p 0x10000 ${BBENV_BUILD_DIR}/env ${BBENV_BUILD_DIR}/hdmi_display

    echo "LVDS" > ${BBENV_BUILD_DIR}/env/nv/display.type

    ${S}/scripts/bareboxenv -s -p 0x10000 ${BBENV_BUILD_DIR}/env ${BBENV_BUILD_DIR}/lvds_display

}

do_install_append() {

    bbnote "Install second environment"
    install -d ${D}/bareboxenv

    install -m 0644 ${BBENV_BUILD_DIR}/hdmi_display ${D}/bareboxenv/
    install -m 0644 ${BBENV_BUILD_DIR}/lvds_display ${D}/bareboxenv/

}

