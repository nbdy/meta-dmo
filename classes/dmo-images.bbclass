# Class to generate the sdcard image.

HOMEPAGE = "https://emb.data-modul.com"

inherit image_types

# we need generating the rootfs to every time
do_rootfs[nostamp] = "1"

SDCARD_SIZE = "3858432"
SDCARD_WITH_HOMEFS    = "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.with-homefs.sdcard2"
SDCARD_WITHOUT_HOMEFS = "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.without-homefs.sdcard2"
SDCARD_WITHOUT_OVERLAY = "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.without-overlay.sdcard2"

IMAGE_DEPENDS_dmosdcard = "parted-native dosfstools-native mtools-native \
                        virtual/kernel ${IMAGE_BOOTLOADER}"

do_rootfs[depends] += "${IMAGE_BOOTLOADER}:do_deploy"
do_rootfs[depends] += "virtual/kernel:do_deploy"

IMAGE_CMD_dmosdcard () {
    # create SDCARD
    bbnote "creating sdcard"
    dd if=/dev/zero of=${SDCARD_WITH_HOMEFS} bs=1 count=0 seek=$(expr ${SDCARD_SIZE} \* 1024)

    # ... and partition this sdcard
    # 0000MB - 0001MB - Bootspace (unpartitioned)
    # 0001MB - 0010MB - Kernelspace (fat32)
    # 0010MB - 1000MB - RootFS (ext3)
    # 1000MB - 2000M  - HomeFS (ext3)
    parted -s ${SDCARD_WITH_HOMEFS} mklabel msdos
    parted -s ${SDCARD_WITH_HOMEFS} unit KiB mkpart primary fat32    1024   52224
    parted -s ${SDCARD_WITH_HOMEFS} unit KiB mkpart primary         52224 2936832
    parted -s ${SDCARD_WITH_HOMEFS} unit KiB mkpart primary       2936832 3653632
    parted -s ${SDCARD_WITH_HOMEFS} unit KiB mkpart primary       3653632 3858431
    parted -s ${SDCARD_WITH_HOMEFS} print

    # put barebox to image
    dd if=${DEPLOY_DIR_IMAGE}/barebox-${MACHINE}.bin of=${SDCARD_WITH_HOMEFS} conv=notrunc seek=1 skip=1 bs=512

    # put kernel to image (shameless copied from meta-fsl-arm)
    [ -e ${WORKDIR}/boot.img ] && rm ${WORKDIR}/boot.img
    BOOT_BLOCKS=$(LC_ALL=C parted -s ${SDCARD_WITH_HOMEFS} unit b print | awk '/ 1 / { print substr($4, 1, length($4 -1)) / 1024 }')
    mkfs.vfat -n "${BOOTDD_VOLUME_ID}" -S 512 -C ${WORKDIR}/boot.img $BOOT_BLOCKS
    mcopy -i ${WORKDIR}/boot.img -s ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE}-${MACHINE}.bin ::/${KERNEL_IMAGETYPE}
    # copy the devicetrees in kernel image
    for dtb in ${KERNEL_DEVICETREE}
    do
        mcopy -i ${WORKDIR}/boot.img -s ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE}-${dtb} ::/
    done
    mcopy -i ${WORKDIR}/boot.img -s ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE}-${DEFAULT_KERNEL_DEVICETREE} ::/oftree
    mcopy -i ${WORKDIR}/boot.img -s ${DEPLOY_DIR_IMAGE}/${INITRAMFS_IMAGE}-${MACHINE}.cpio.gz ::/initramfs

    # Creat the overlay partition
    [ -e ${WORKDIR}/overlay.img ] && rm ${WORKDIR}/overlay.img
    dd if=/dev/zero of=${WORKDIR}/overlay.img seek=716799 count=0 bs=1k
    mkfs.ext3 -F ${WORKDIR}/overlay.img
    
    # Burn Partition
    dd if=${WORKDIR}/boot.img    of=${SDCARD_WITH_HOMEFS} conv=notrunc seek=1 bs=$(expr 1024    \* 1024) && sync && sync
    dd if=${SDCARD_ROOTFS}       of=${SDCARD_WITH_HOMEFS} conv=notrunc seek=1 bs=$(expr 52224   \* 1024) && sync && sync
    dd if=${WORKDIR}/overlay.img of=${SDCARD_WITH_HOMEFS} conv=notrunc seek=1 bs=$(expr 2936832 \* 1024) && sync && sync
    dd if=${HOMEFS_IMAGE}        of=${SDCARD_WITH_HOMEFS} conv=notrunc seek=1 bs=$(expr 3653632 \* 1024) && sync && sync

    # crop image to get one without homefs, but with same partition table
    dd if=${SDCARD_WITH_HOMEFS} of=${SDCARD_WITHOUT_HOMEFS} bs=$(expr 1024 \* 3653632) count=1 && sync && sync

    # crop image to get one without overlay and homefs, but with same partition table
    dd if=${SDCARD_WITH_HOMEFS} of=${SDCARD_WITHOUT_OVERLAY} bs=$(expr 1024 \* 2936832) count=1 && sync && sync
}

