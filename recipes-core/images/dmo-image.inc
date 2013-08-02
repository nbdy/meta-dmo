INC_PR = "r3"

IMAGE_INSTALL = " \
    bluez4 \
    bluez-hcidump \
    opkg \
    opkg-config-base \
    packagegroup-core-boot \
    nfs-utils \
    nfs-utils-client \
    kernel-modules \
    udev-extraconf \
    firmware-imx-vpu-imx6q \
    e2fsprogs-e2fsck \
    e2fsprogs-mke2fs \
    e2fsprogs-tune2fs \
    start-stop-daemon \
    tslib \
    iputils \
    ${ROOTFS_PKGMANAGE_BOOTSTRAP} \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"
IMAGE_LINGUAS = " "
LICENSE = "GPLv2"

inherit core-image

ROOTFS_POSTINSTALL_COMMAND += " create_homefs_and_image; "
IMAGE_POSTPROCESS_COMMAND += " dmo_create_developer_directory; "

IMAGE_ROOTFS_SIZE = "8192"

dmo_create_developer_directory () {
    if [ -n "${DMO_CREATE_NFS_DIR}" ]; then
        mkdir ${DEPLOY_DIR_IMAGE}/nfs-${IMAGE_NAME}
        sudo tar -C ${DEPLOY_DIR_IMAGE}/nfs-${IMAGE_NAME} -xvf ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.tar.bz2
        [ -e ${DEPLOY_DIR_IMAGE}/nfs ] && rm ${DEPLOY_DIR_IMAGE}/nfs
        ln -sf "nfs-${IMAGE_NAME}" ${DEPLOY_DIR_IMAGE}/nfs
    fi
}

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

