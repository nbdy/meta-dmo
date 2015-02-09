
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://v4l-coda960-imx6q.bin \
            file://v4l-coda960-imx6dl.bin"

FIRMWARE_NAME_mx6q = "v4l-coda960-imx6q.bin"
FIRMWARE_NAME_mx6dl = "v4l-coda960-imx6dl.bin"

do_install_append () {
    install ${WORKDIR}/${FIRMWARE_NAME} ${D}/lib/firmware/
    ln -sf ../${FIRMWARE_NAME} ${D}/lib/firmware/vpu/${FIRMWARE_NAME}
}

FILES_${PN}-vpu-imx6q += "lib/firmware/v4l-coda960-imx6q.bin"
FILES_${PN}-vpu-imx6q += "lib/firmware/vpu/v4l-coda960-imx6q.bin"

#Note: Here only a 'd' is used instead of 'dl' because the MACHINE_FIRMWARE_append_mx6dl appends only the 'd' and not 'dl'
FILES_${PN}-vpu-imx6d += "lib/firmware/v4l-coda960-imx6dl.bin"
FILES_${PN}-vpu-imx6d += "lib/firmware/vpu/v4l-coda960-imx6dl.bin"
