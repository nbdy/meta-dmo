DESCRIPTION = "A small image just capable of allowing a device to boot."
HOMEPAGE="https://emb.data-modul.com"

inherit core-image
inherit dmo-images
inherit dmo-checksum
inherit image-buildinfo

IMAGE_LINGUAS = " "
LICENSE = "GPLv2"

DEPENDS += "e2fsprogs-native"

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

LOCAL_MACHINE_mx6 = "imx6"
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

ssh_allow_empty_password_append() {
    if [ -e ${IMAGE_ROOTFS}${sysconfdir}/ssh/sshd_config_readonly ]; then
        sed -i 's/^[#[:space:]]*PermitRootLogin.*/PermitRootLogin yes/' ${IMAGE_ROOTFS}${sysconfdir}/ssh/sshd_config_readonly
        sed -i 's/^[#[:space:]]*PermitEmptyPasswords.*/PermitEmptyPasswords yes/' ${IMAGE_ROOTFS}${sysconfdir}/ssh/sshd_config_readonly
    fi
}

