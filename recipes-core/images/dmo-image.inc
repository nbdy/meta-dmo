INC_PR = "r4"

# hardware independent packages
IMAGE_INSTALL = " \
    bluez-hcidump \
    bluez4 \
    coreutils \
    dmo-base-user \
    e2fsprogs-e2fsck \
    e2fsprogs-mke2fs \
    e2fsprogs-tune2fs \
    fb-test \
    iperf \
    iproute2 \
    iputils \
    iw \
    linux-firmware \
    nfs-utils \
    nfs-utils-client \
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

DEPENDS += "e2fsprogs-native"

inherit core-image
inherit dmo-images

SDCARD = "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.without-homefs.img"
SDCARD_HOME = "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.with-homefs.img"

ROOTFS_POSTINSTALL_COMMAND += " create_homefs_and_image; "
ROOTFS_POSTPROCESS_COMMAND += "remove_libav_and_x264_files"

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

    [ -e ${HOMEFS_IMAGE} ] && rm ${HOMEFS_IMAGE}
    dd if=/dev/zero of=${HOMEFS_IMAGE} count=0 bs=1k seek=${HOMEFS_SPACE}

    # Create a sparse image block
    mkfs.ext3 -F ${HOMEFS_IMAGE} -d ${HOMEFS}
}

removeFiles() {

    filePath="$1"
    if [ -f $filePath ]; then
        i=0
        while read line
        do
            xFiles[$i]=${IMAGE_ROOTFS}"/usr/"$line
            ((++i))
        done < $filePath

        for f in ${xFiles[*]}
        do
            if [ ! -d $f ] && [ -f $f ]; then
                rm "$f"
                bbnote "Removed" $f
            fi
        done
    else
            bberror "\""$filePath"\" does not exists."
            exit -1
    fi
}

remove_libav_and_x264_files() {

    readonly X264_FILES=${FILE_DIRNAME}"/files/x264_file_list"
    readonly LIBAV_FILES=${FILE_DIRNAME}"/files/libav_file_list"

    removeFiles $X264_FILES
    removeFiles $LIBAV_FILES
}

