DESCRIPTION = "dmctouch: kernelmodules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/usb26/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
PR = "r0"

SRC_URI = "file://tsc_213_121126.tgz \
           file://0001-remove-KDIR.patch \
           file://0002-add-a-modules-install-target.patch \
"

inherit module

S = "${WORKDIR}/TSC10LD_V213"
export KDIR = "${STAGING_KERNEL_DIR}"

do_compile () {
    cd usb26
    oe_runmake clean

    oe_runmake usbdmc.ko
    oe_runmake tpdmc.ko
}

do_install_prepend() {
    cd ${S}/usb26
}
