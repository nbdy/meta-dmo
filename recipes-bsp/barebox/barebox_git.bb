require barebox.inc

PV = "2013.03.0+git${SRCREV}"
PR = "${INC_PR}.21"
SRC_URI = " \
    git://git@emb.data-modul.com/development/barebox;protocol=ssh \
"
SRCREV = "fbbad8cee25a431d17d9a43f91a6c77062bf175d"

COMPATIBLE_MACHINE = "imx6q-dmo-edm-qmx6"

S = "${WORKDIR}/git"

do_configure_prepend_imx6q-dmo-edm-qmx6() {
    oe_runmake ${BAREBOX_MACHINE}
}

do_deploy_prepend () {
    [ -e ${S}/manipulate_bb_env ] && rm -rf ${S}/manipulate_bb_env

    ${S}/scripts/bareboxenv -l ${S}/manipulate_bb_env ${S}/common/barebox_default_env

    install ${DEPLOY_DIR_IMAGE}/imx6q-dmo-realq7-1920x1080.dtb \
            ${DEPLOY_DIR_IMAGE}/imx6q-dmo-realq7-800x480.dtb \
            ${S}/manipulate_bb_env/
    ln -sf imx6q-dmo-edmqmx6-auo-g104sn03.dtb ${S}/manipulate_bb_env/deviceTreeFile

    ${S}/scripts/bareboxenv -s ${S}/manipulate_bb_env ${S}/common/barebox_default_env
}
# before we can configure we need the generated dtb
do_deploy[depends] += "virtual/kernel:do_install"

