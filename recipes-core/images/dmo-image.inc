INC_PR = "r3"

IMAGE_INSTALL = " \
    bluez-hcidump \
    bluez4 \
    dmo-base-user \
    e2fsprogs-e2fsck \
    e2fsprogs-mke2fs \
    e2fsprogs-tune2fs \
    fb-test \
    firmware-imx-vpu-imx6q \
    iperf \
    iputils \
    iw \
    kernel-modules \
    linux-firmware \
    nfs-utils \
    nfs-utils-client \
    opkg \
    opkg-config-base \
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
IMAGE_LINGUAS = " "
LICENSE = "GPLv2"

inherit core-image

ROOTFS_POSTINSTALL_COMMAND += " create_homefs_and_image; "

IMAGE_ROOTFS_SIZE = "8192"

HOMEFS_SPACE ?= "1048576"
HOMEFS_IMAGE ?= "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.homefs.ext3"
create_homefs_and_image () {
    HOMEFS="${WORKDIR}/homefs"

    [ -e ${HOMEFS} ] && rm -rf ${HOMEFS}
    mkdir ${HOMEFS}

    mv ${WORKDIR}/rootfs/home/* ${HOMEFS}

    printf "genext2fs -b ${HOMEFS_SIZE} -d ${HOMEFS} -i 8192 ${HOMEFS_IMAGE}\n"
    genext2fs -b ${HOMEFS_SPACE} -d ${HOMEFS} -i 8192 ${HOMEFS_IMAGE}
    tune2fs -j ${HOMEFS_IMAGE}
}

