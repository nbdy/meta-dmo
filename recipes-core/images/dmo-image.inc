INC_PR = "r2"

IMAGE_INSTALL = " \
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
    ${ROOTFS_PKGMANAGE_BOOTSTRAP} \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"
IMAGE_LINGUAS = " "
LICENSE = "GPLv2"

inherit core-image

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

