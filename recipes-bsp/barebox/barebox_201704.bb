require recipes-bsp/barebox/barebox.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = " \
    git://git@emb.data-modul.com/development/barebox;branch=rya/201704/next-release-vertigoBased;protocol=ssh;name=mx6 \
    file://0002-enable-initrd-for-sdcard-boot.patch \
"

PV = "2017.04+git${SRCPV}"
SRCREV_mx6 = "${AUTOREV}"

COMPATIBLE_MACHINE = "imx6s-dmo-vertigo"

PACKAGES_append += "${PN}-bbenv"
FILES_${PN}-bbenv = "/bareboxenv"
BBENV_BUILD_DIR = "${WORKDIR}/build_env/"

S = "${WORKDIR}/git"

do_configure_prepend_imx6s-dmo-vertigo() {
    oe_runmake ${BAREBOX_MACHINE}
}

do_compile_append() {
    bbnote "Build the second environment"

    install -d ${BBENV_BUILD_DIR}/env/nv
    echo "bootB" > ${BBENV_BUILD_DIR}/env/nv/dual.boot

    ${S}/scripts/bareboxenv -s -p 0x10000 ${BBENV_BUILD_DIR}/env ${BBENV_BUILD_DIR}/envB
}

do_install_append() {

    bbnote "Install second environment"
    install -d ${D}/bareboxenv

    install -m 0644 ${BBENV_BUILD_DIR}/envB ${D}/bareboxenv/
}

