require barebox.inc

PR = "${INC_PR}.5"
SRC_URI = " \
    git://git@emb.data-modul.com/development/barebox;protocol=ssh \
"
SRCREV = "f95c097d3abac4978ede2fc9115b4213587c5bd3"
LOCALVERSION = "-1.1.0+yocto"

COMPATIBLE_MACHINE = "imx6q-dmo-edm-qmx6"

do_configure_prepend_imx6q-dmo-edm-qmx6() {
    # Install barebox environment
    cp ${DEPLOY_DIR_IMAGE}/imx6q-dmo-realq7-1920x1080.dtb \
        ${WORKDIR}/git/arch/arm/boards/dmo-mx6-realq7/env/imx6q-dmo-realq7-1920x1080.dtb
    cp ${DEPLOY_DIR_IMAGE}/imx6q-dmo-realq7-800x480.dtb \
        ${WORKDIR}/git/arch/arm/boards/dmo-mx6-realq7/env/imx6q-dmo-realq7-800x480.dtb

    ln -sf /env/imx6q-dmo-realq7-800x480.dtb \
        ${WORKDIR}/git/arch/arm/boards/dmo-mx6-realq7/env/deviceTreeFile
    oe_runmake ${BAREBOX_MACHINE}
}
# before we can configure we need the generated dtb
do_configure[depends] += "virtual/kernel:do_install"

