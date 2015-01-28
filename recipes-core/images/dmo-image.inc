HOMEPAGE="https://emb.data-modul.com"

# hardware independent packages
IMAGE_INSTALL = " \
    bash \
    bluez-hcidump \
    bluez4 \
    canutils \
    coreutils \
    dmo-gst-videoscripts \
    e2fsprogs-e2fsck \
    e2fsprogs-mke2fs \
    e2fsprogs-tune2fs \
    fb-test \
    gpu-viv-bin-mx6q \
    gstreamer1.0 \
    gstreamer1.0-plugins-bad-meta \
    gstreamer1.0-plugins-base-meta \
    gstreamer1.0-plugins-good-meta \
    i2c-tools \
    icu \
    iperf \
    iproute2 \
    iputils \
    iw \
    kernel-modules \
    kmod \
    kmsfbwrap \
    linux-firmware \
    mtd-utils \
    mtools \
    nfs-utils \
    nfs-utils-client \
    obexd \
    openobex \
    openssh \
    packagegroup-core-boot \
    pciutils \
    powertop \
    procps \
    psplash \
    root-user-homedir \
    start-stop-daemon \
    strace \
    sudo \
    tslib \
    udev-extraconf \
    util-linux \
    wget \
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
inherit dmo-checksum

SDCARD = "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.without-homefs.img"
SDCARD_HOME = "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.with-homefs.img"

ROOTFS_POSTINSTALL_COMMAND += " create_homefs_and_image; "
ROOTFS_POSTPROCESS_COMMAND += " dmo_imageRemoveLibavX264Files; "
ROOTFS_POSTPROCESS_COMMAND_remove = "rootfs_update_timestamp" 

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

dmo_image_removeFiles () {

    filePath="$1"
    if [ -f $filePath ]; then
        while read line
        do
            file=${IMAGE_ROOTFS}"/usr/"$line
            if [ ! -d $file ] && [ -f $file ]; then
                rm "$file"
                bbnote "Removed" $file
            fi 
        done < $filePath
    else
        bberror "\""$filePath"\" does not exists."
        exit -1
    fi
}

dmo_imageRemoveLibavX264Files () {

    if [ "${DMO_REMOVE_FILES_FROM_COMMERCIAL_RECIPES}" = "1" ]; then
        bbnote "Removing files from commercial recipies"
        dmo_image_removeFiles ${FILE_DIRNAME}"/files/x264_file_list"
        dmo_image_removeFiles ${FILE_DIRNAME}"/files/libav_file_list"
    fi
}

do_deploy () {
    dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".homefs.ext3"
    dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".rootfs.ext3"
    dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".rootfs.manifest"
    dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".rootfs.tar.bz2"
    dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".with-homefs.sdcard2"
    dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".without-homefs.sdcard2"
}

do_deploy[dirs] = "${S}"
addtask deploy before do_build after do_rootfs
