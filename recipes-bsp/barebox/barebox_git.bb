require barebox.inc

PV = "2013.03.0+git${SRCREV}"
PR = "${INC_PR}.20"
SRC_URI = " \
    git://git@emb.data-modul.com/development/barebox;protocol=ssh \
"
SRCREV = "ef266bc588756bb10f439a1965e1e2a48f19f441"

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

