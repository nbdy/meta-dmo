# linux-yocto-custom.bb:
#
#   An example kernel recipe that uses the linux-yocto and oe-core
#   kernel classes to apply a subset of yocto kernel management to git
#   managed kernel repositories.
#
#   To use linux-yocto-custom in your layer, create a
#   linux-yocto-custom.bbappend file containing at least the following
#   lines:
#
#     FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
#     COMPATIBLE_MACHINE_yourmachine = "yourmachine"
#
#   You must also provide a Linux kernel configuration. The most direct
#   method is to copy your .config to files/defconfig in your layer,
#   in the same directory as the bbappend and add file://defconfig to
#   your SRC_URI.
#
#   To use the yocto kernel tooling to generate a BSP configuration
#   using modular configuration fragments, see the yocto-bsp and
#   yocto-kernel tools documentation.
#
# Warning:
#
#   Building this example without providing a defconfig or BSP
#   configuration will result in build or boot errors. This is not a
#   bug.
#
#
# Notes:
#
#   patches: patches can be merged into to the source git tree itself,
#            added via the SRC_URI, or controlled via a BSP
#            configuration.
#   
#   example configuration addition:
#            SRC_URI += "file://smp.cfg"
#   example patch addition (for kernel v3.4 only):
#            SRC_URI += "file://0001-linux-version-tweak.patch
#   example feature addition (for kernel v3.4 only):
#            SRC_URI += "file://feature.scc"
#

inherit kernel
inherit dmo-checksum
inherit fsl-vivante-kernel-driver-handler
require recipes-kernel/linux/linux-yocto.inc
inherit dmo-splashlogo

KCONFIG_MODE="--alldefconfig"
KBUILD_DEFCONFIG_mx6 ?= "imx_v6_v7_defconfig"

do_deploy_append() {
    dmo_do_checksum ${DEPLOYDIR} ${KERNEL_IMAGE_BASE_NAME}".bin"
    dmo_do_checksum ${DEPLOYDIR} ${MODULE_TARBALL_BASE_NAME}

    if test -n "${KERNEL_DEVICETREE}"; then
        for DTB in ${KERNEL_DEVICETREE}; do
            DTB_BASE_NAME=`basename ${DTB} .dtb`
            DTB_NAME=`echo ${KERNEL_IMAGE_BASE_NAME} | sed "s/${MACHINE}/${DTB_BASE_NAME}/g"`
            dmo_do_checksum ${DEPLOYDIR} ${DTB_NAME}".dtb"
        done
    fi
}

