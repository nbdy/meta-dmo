# Copyright (C) 2011-2012 Freescale Semiconductor
# Released under the MIT license (see COPYING.MIT for the terms)

inherit kernel

DESCRIPTION = "Linux kernel for imx based data-modul platforms - prebuild"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

KERNELVERSION = "v3.9-rc7-958-g9971099-dirty"
TOOLCHAINVERSION = "oselas-tc-2011.11.1"

PV = "prebuild-${KERNELVERSION}-${TOOLCHAINVERSION}+git${SRCREV}"
PR = "r5"

SRC_URI = "git://git@emb.data-modul.com/userrepos/sfr/linuxdmo-prebuild;protocol=ssh"
S = "${WORKDIR}/git"

KERNEL_PRIORITY = "10000"

COMPATIBLE_MACHINE = "(mx6)"

SRCREV = "992a721ca91bea2490d640de5af3896e3f609eaa"
DEPENDS_${PN}_remove = "kernel-modules"
RDEPENDS_${PN}_remove = "kernel-modules"
PROVIDES += " \
    linux-dmo \
    virtual/kernel \
    kernel-base \
    kernel-image \
"

do_install () {
    [ ! -e ${DEPLOY_DIR_IMAGE} ] && mkdir -p ${DEPLOY_DIR_IMAGE}
    # kernel
    cp -a ${S}/${KERNELVERSION}/${TOOLCHAINVERSION}/arch/arm/boot/zImage ${DEPLOY_DIR_IMAGE}/zImage--${PV}.bin
    ln -sf zImage--${PV}.bin ${DEPLOY_DIR_IMAGE}/zImage
    ln -sf zImage--${PV}.bin ${DEPLOY_DIR_IMAGE}/zImage-${MACHINE}.bin

    # devicetree
    for dtb in ${S}/${KERNELVERSION}/${TOOLCHAINVERSION}/arch/arm/boot/dts/*
    do
        name=$(basename ${dtb})
        cp -a ${S}/${KERNELVERSION}/${TOOLCHAINVERSION}/arch/arm/boot/dts/${name} ${DEPLOY_DIR_IMAGE}/${MACHINE}-${PV}-${PR}-${name}
        ln -sf ${MACHINE}-${PV}-${PR}-${name} ${DEPLOY_DIR_IMAGE}/${name}
    done
}

# this functions not needed
do_bundle_initramfs () {
}

do_configure () {
}

do_compile () {
}

do_deploy () {
}

do_package () {
}

do_package_write_ipk () {
}
