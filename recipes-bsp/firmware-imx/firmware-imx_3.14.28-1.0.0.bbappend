DEPENDS += "convert-vpu-firmware-native"

FIRMWARE_NAME_mx6q = "v4l-coda960-imx6q.bin"
FIRMWARE_NAME_mx6dl = "v4l-coda960-imx6dl.bin"
SOURCE_FW_NAME_mx6q = "vpu_fw_imx6q.bin"
SOURCE_FW_NAME_mx6dl = "vpu_fw_imx6d.bin"

convert_firmware () {
    ${STAGING_BINDIR_NATIVE}/convert-vpu-firmware ${S}/firmware/vpu/${SOURCE_FW_NAME} ${D}/lib/firmware/${FIRMWARE_NAME}
    ln -sf ../${FIRMWARE_NAME} ${D}/lib/firmware/vpu/${FIRMWARE_NAME}
}

do_install_append_mx6q() {
    convert_firmware
}

do_install_append_mx6dl() {
    convert_firmware
}

FILES_${PN}-vpu-imx6q += "lib/firmware/v4l-coda960-imx6q.bin"
FILES_${PN}-vpu-imx6q += "lib/firmware/vpu/v4l-coda960-imx6q.bin"

#Note: Here only a 'd' is used instead of 'dl' because the MACHINE_FIRMWARE_append_mx6dl appends only the 'd' and not 'dl'
FILES_${PN}-vpu-imx6d += "lib/firmware/v4l-coda960-imx6dl.bin"
FILES_${PN}-vpu-imx6d += "lib/firmware/vpu/v4l-coda960-imx6dl.bin"
