DESCRIPTION = "A small image just capable of allowing a device to boot."
HOMEPAGE="https://emb.data-modul.com"

inherit core-image
inherit dmo-images
inherit dmo-checksum
inherit image-buildinfo

IMAGE_LINGUAS = " "
LICENSE = "GPLv2"

DEPENDS += "e2fsprogs-native"

# hardware independent packages
IMAGE_INSTALL = " \
    bash \
    blanking \
    bluez-hcidump \
    bluez5 \
    canutils \
    coreutils \
    dmo-gst-videoscripts \
    e2fsprogs-e2fsck \
    e2fsprogs-mke2fs \
    e2fsprogs-tune2fs \
    fb-test \
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
    root-user-homedir \
    systemd-vconsole-setup \
    strace \
    sudo \
    tslib-conf \
    tslib-calibrate \
    tslib-tests \
    udev-extraconf \
    util-linux \
    wget \
    wireless-tools \
    wpa-supplicant \
    ${ROOTFS_PKGMANAGE_BOOTSTRAP} \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"
IMAGE_INSTALL_append_dmo-x11 = " \
    chromium \
    mesa-demos \
    packagegroup-xfce-base \
    slim \
    slim-systemd \
    unclutter \
    xdg-utils \
    xf86-input-evdev \
    xf86-video-fbdev \
    xhost \
    xinput-calibrator \
    xmodmap \
    xrdb \
    xserver-xorg \
    xserver-xorg-extension-dbe \
    xserver-xorg-extension-extmod \
    xset \
"
IMAGE_INSTALL_append_dmo-qt = " \
    cinematicexperience \
    dmlauncher \
    ew15demo \
    qmlvideo \
    qt3d \
    qtbase \
    qtbase-examples \
    qtbase-fonts \
    qtbase-fonts-pfa \
    qtbase-fonts-pfb \
    qtbase-fonts-qpf \
    qtbase-fonts-ttf-dejavu \
    qtbase-fonts-ttf-vera \
    qtbase-mkspecs \
    qtbase-plugins \
    qtbase-tools \
    qtdeclarative \
    qtdeclarative-examples \
    qtdeclarative-mkspecs \
    qtdeclarative-plugins \
    qtdeclarative-qmlplugins \
    qtdeclarative-tools \
    qtmultimedia \
    qtmultimedia-examples \
    qtmultimedia-mkspecs \
    qtmultimedia-plugins \
    qtmultimedia-qmlplugins \
"
# Hardware depend packages
IMAGE_INSTALL_append_mx6q = " \
    firmware-imx-vpu-imx6q \
"
IMAGE_INSTALL_append_mx6dl = " \
    firmware-imx-vpu-imx6d \
"
IMAGE_INSTALL_append_mx6 = " \
    imx-gpu-viv \
    imx-gpu-viv-demos \
"
IMAGE_INSTALL_append_mx6_dmo-x11 = " \
    xf86-video-imxfb-vivante \
"
IMAGE_INSTALL_append_dmo-edm-comb-bw6 = " \
    alsa-utils \
    gstreamer-vaapi-1.0 \
    libva-intel-driver \
    mesa-megadriver \
    xf86-video-intel \
    xserver-xorg-extension-glx \
"

SDCARD = "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.without-homefs.img"
SDCARD_HOME = "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.with-homefs.img"

ROOTFS_POSTINSTALL_COMMAND += " create_homefs_and_image; "
ROOTFS_POSTPROCESS_COMMAND += " dmo_imageRemoveLibavX264Files; "
ROOTFS_POSTPROCESS_COMMAND_remove = " rootfs_update_timestamp ;" 

# The write_image_manifest needs to be removed, with the space,
# and afterwards reenterd witout the space.
# This needs to be done, because the above remove,
# removes also the ';' after 'write_image_manifest'
ROOTFS_POSTPROCESS_COMMAND_remove = " write_image_manifest ;"
ROOTFS_POSTPROCESS_COMMAND += " write_image_manifest;"

# set size and factor of ROOTFS
IMAGE_ROOTFS_SIZE = "2883584"
IMAGE_OVERHEAD_FACTOR = "1"
IMAGE_ROOTFS_EXTRA_SPACE = "0"

HOMEFS_SPACE = "204799"
HOMEFS_IMAGE = "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.homefs.ext4"

create_homefs_and_image () {
    HOMEFS="${WORKDIR}/homefs"

    [ -e ${HOMEFS} ] && rm -rf ${HOMEFS}
    mkdir ${HOMEFS}

    mv ${WORKDIR}/rootfs/home/* ${HOMEFS}

    [ -e ${HOMEFS_IMAGE} ] && rm ${HOMEFS_IMAGE}
    dd if=/dev/zero of=${HOMEFS_IMAGE} count=0 bs=1k seek=${HOMEFS_SPACE}

    # Create a sparse image block
    mkfs.ext4 -F ${HOMEFS_IMAGE} -d ${HOMEFS}
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

LOCAL_MACHINE_imx6-dmo-edm-qmx = "imx6"
LOCAL_MACHINE_dmo-edm-comb-bw6 = "x86"

do_deploy () {
    dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".homefs.ext4"
    dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".rootfs.ext4"
    dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".rootfs.manifest"
    dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".rootfs.tar.bz2"
    if [ "imx6" == "${LOCAL_MACHINE}" ]; then
        dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".with-homefs.sdcard2"
        dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".without-homefs.sdcard2"
        dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".without-overlayfs.sdcard2"
    elif [ "x86" == "${LOCAL_MACHINE}" ]; then
        dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".hddimg"
        dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${IMAGE_NAME}".iso"
    fi
}

do_deploy[dirs] = "${S}"
addtask deploy before do_build after do_bootimg

ssh_allow_empty_password_append() {
    if [ -e ${IMAGE_ROOTFS}${sysconfdir}/ssh/sshd_config_readonly ]; then
        sed -i 's/^[#[:space:]]*PermitRootLogin.*/PermitRootLogin yes/' ${IMAGE_ROOTFS}${sysconfdir}/ssh/sshd_config_readonly
        sed -i 's/^[#[:space:]]*PermitEmptyPasswords.*/PermitEmptyPasswords yes/' ${IMAGE_ROOTFS}${sysconfdir}/ssh/sshd_config_readonly
    fi
}

