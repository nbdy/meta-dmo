INC_PR = "r4"

# hardware independent packages
IMAGE_INSTALL = " \
    bluez-hcidump \
    bluez4 \
    dmo-base-user \
    e2fsprogs-e2fsck \
    e2fsprogs-mke2fs \
    e2fsprogs-tune2fs \
    fb-test \
    iperf \
    iproute2 \
    iputils \
    iw \
    kernel-modules \
    linux-firmware \
    nfs-utils \
    nfs-utils-client \
    opkg \
    opkg-config-base \
    powertop \
    packagegroup-core-boot \
    root-user-homedir \
    start-stop-daemon \
    tslib \
    udev-extraconf \
    wireless-tools \
    wpa-supplicant \
    ${ROOTFS_PKGMANAGE_BOOTSTRAP} \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"
# Hardware depend packages
IMAGE_INSTALL_append_mx6 = " \
    firmware-imx-vpu-imx6q \
"

IMAGE_LINGUAS = " "
LICENSE = "GPLv2"

inherit core-image
inherit dmo-images

DEPENDS += "genext2fs-native"

SDCARD = "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.without-homefs.img"
SDCARD_HOME = "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.with-homefs.img"

ROOTFS_POSTINSTALL_COMMAND += " create_homefs_and_image; "

# set size and factor of ROOTFS
IMAGE_ROOTFS_SIZE = "819200"
IMAGE_OVERHEAD_FACTOR = "1"

HOMEFS_SPACE = "786431"
HOMEFS_IMAGE = "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.homefs.ext3"
create_homefs_and_image () {
    HOMEFS="${WORKDIR}/homefs"

    [ -e ${HOMEFS} ] && rm -rf ${HOMEFS}
    mkdir ${HOMEFS}

    mv ${WORKDIR}/rootfs/home/* ${HOMEFS}

    # generate ext2
    genext2fs -b ${HOMEFS_SPACE} -d ${HOMEFS} -i 8192 ${HOMEFS_IMAGE}
    # convert to ext3
    tune2fs -j ${HOMEFS_IMAGE}
}
