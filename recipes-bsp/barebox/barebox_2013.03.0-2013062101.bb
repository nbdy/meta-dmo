require barebox.inc

PR = "${INC_PR}.21"
SRC_URI = " \
    ${DMOFTP}/${PN}-${PV}.tar.gz \
"

SRC_URI[md5sum] = "340535231361ed36877e1d93d4145f05"
SRC_URI[sha256sum] = "183d665e0cfd337f50fba483712750031dbcfec25b5029f80c5835a6c2986914"

COMPATIBLE_MACHINE = "imx6q-dmo-edm-qmx6"

do_configure_prepend_imx6q-dmo-edm-qmx6() {
    oe_runmake ${BAREBOX_MACHINE}
}

do_deploy_prepend () {
    [ -e ${S}/manipulate_bb_env ] && rm -rf ${S}/manipulate_bb_env

    ${S}/scripts/bareboxenv -l ${S}/manipulate_bb_env ${S}/common/barebox_default_env

    install ${DEPLOY_DIR_IMAGE}/imx6q-dmo-realq7-1920x1080.dtb ${S}/manipulate_bb_env/imx6q-dmo-realq7-1920x1080.dtb
    install ${DEPLOY_DIR_IMAGE}/imx6q-dmo-realq7-800x480.dtb ${S}/manipulate_bb_env/imx6q-dmo-realq7-800x480.dtb
    ln -sf imx6q-dmo-realq7-800x480.dtb ${S}/manipulate_bb_env/deviceTreeFile

    ${S}/scripts/bareboxenv -s ${S}/manipulate_bb_env ${S}/common/barebox_default_env
}
# before we can configure we need the generated dtb
do_deploy[depends] += "virtual/kernel:do_install"

