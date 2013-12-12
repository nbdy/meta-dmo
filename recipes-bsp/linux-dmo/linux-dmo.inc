# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Linux kernel for imx based data-modul platforms"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
DEPENDS += "lzop-native bc-native"

INC_PR = "r33"

inherit kernel

# Put a local version until we have a true SRCREV to point to
LOCALVERSION ?= "+yocto"
SCMVERSION ?= "y"

do_configure_prepend() {
    if [ ! -z "${DMO_KERNEL_DEFCONFIG}" ]; then
        make ${DMO_KERNEL_DEFCONFIG}
    fi
}

do_install_append() {
    if [ ! -z "${DMO_KERNEL_DEVICE_TREE_BLOBS_DEPLOY_DIR}" ]; then
        [ ! -e ${DEPLOY_DIR_IMAGE} ] && mkdir -p ${DEPLOY_DIR_IMAGE}
        for dtb in ${DMO_KERNEL_DEVICE_TREE_BLOBS_DEPLOY_DIR}
        do
            oe_runmake ${dtb}
            cp ${S}/arch/arm/boot/dts/${dtb} ${DEPLOY_DIR_IMAGE}/${MACHINE}-${PV}-${PR}-${dtb}
            ln -sf ${DEPLOY_DIR_IMAGE}/${MACHINE}-${PV}-${PR}-${dtb} ${DEPLOY_DIR_IMAGE}/${dtb}
        done
    fi
}

