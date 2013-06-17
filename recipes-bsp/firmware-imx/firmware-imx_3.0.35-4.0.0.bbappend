
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://v4l-coda960-imx6q.bin"

do_install_append () {
    install ${WORKDIR}/v4l-coda960-imx6q.bin ${D}/lib/firmware/
    ln -sf ../v4l-coda960-imx6q.bin ${D}/lib/firmware/vpu/v4l-coda960-imx6q.bin
}

FILES_${PN}-vpu-imx6q += "lib/firmware/v4l-coda960-imx6q.bin"
FILES_${PN}-vpu-imx6q += "lib/firmware/vpu/v4l-coda960-imx6q.bin"

