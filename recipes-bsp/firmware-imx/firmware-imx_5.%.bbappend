FIRMWARE_NAME_mx6q = "v4l-coda960-imx6q.bin"
FIRMWARE_NAME_mx6dl = "v4l-coda960-imx6dl.bin"
SOURCE_FW_NAME_mx6q = "vpu_fw_imx6q.bin"
SOURCE_FW_NAME_mx6dl = "vpu_fw_imx6d.bin"

do_install_append() {
    ln -sf vpu/${SOURCE_FW_NAME} ${D}/lib/firmware/${FIRMWARE_NAME}
}

FILES_${PN}-vpu-imx6q += "lib/firmware/v4l-coda960-imx6q.bin"
FILES_${PN}-vpu-imx6d += "lib/firmware/v4l-coda960-imx6dl.bin"

