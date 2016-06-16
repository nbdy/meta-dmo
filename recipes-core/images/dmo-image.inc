require dmo-base-image.inc
inherit dmo-images

ROOTFS_POSTINSTALL_COMMAND += " create_homefs_and_image; "

# set size and factor of ROOTFS
IMAGE_ROOTFS_SIZE = "2883584"
IMAGE_OVERHEAD_FACTOR = "1"
IMAGE_ROOTFS_EXTRA_SPACE = "0"

HOMEFS_SPACE = "204799"
HOMEFS_IMAGE = "${IMAGE_NAME}.homefs.ext4"
HOMEFS_IMAGE_FULL = "${DEPLOY_DIR_IMAGE}/${HOMEFS_IMAGE}"
HOMEFS_LINK_IMAGE = "${DEPLOY_DIR_IMAGE}/${IMAGE_LINK_NAME}.homefs.ext4"

create_homefs_and_image () {
    HOMEFS="${WORKDIR}/homefs"

    [ -e ${HOMEFS} ] && rm -rf ${HOMEFS}
    mkdir ${HOMEFS}

    mv ${WORKDIR}/rootfs/home/* ${HOMEFS}

    [ -e ${HOMEFS_IMAGE_FULL} ] && rm ${HOMEFS_IMAGE_FULL}
    dd if=/dev/zero of=${HOMEFS_IMAGE_FULL} count=0 bs=1k seek=${HOMEFS_SPACE}

    # Create a sparse image block
    mkfs.ext4 -F ${HOMEFS_IMAGE_FULL} -d ${HOMEFS}
}

do_deploy () {
    dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".homefs.ext4"
    dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".rootfs.ext4"
    dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".rootfs.manifest"
    dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".rootfs.tar.bz2"
    if [ "imx6" == "${LOCAL_MACHINE}" ]; then
        dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".with-homefs.sdcard2"
        dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".without-homefs.sdcard2"
        dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".without-overlayfs.sdcard2"
    fi
}

do_deployiso() {
    if [ "x86" == "${LOCAL_MACHINE}" ]; then
        dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".hddimg"
        dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".iso"
    fi
}

do_deploy[dirs] = "${S}"
do_deployiso[dirs] = "${S}"
addtask deploy before do_build after do_rootfs
addtask deployiso before do_build after do_bootimg

