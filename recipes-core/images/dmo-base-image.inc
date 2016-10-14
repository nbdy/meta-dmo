DESCRIPTION = "A small image just capable of allowing a device to boot."
HOMEPAGE="https://emb.data-modul.com"

inherit core-image
inherit dmo-checksum
inherit image-buildinfo

IMAGE_LINGUAS = " "
LICENSE = "GPLv2"

DEPENDS += "e2fsprogs-native"

ROOTFS_POSTPROCESS_COMMAND += " dmo_imageRemoveLibavX264Files; "
ROOTFS_POSTPROCESS_COMMAND_remove = " rootfs_update_timestamp ;" 

# The write_image_manifest needs to be removed, with the space,
# and afterwards reenterd witout the space.
# This needs to be done, because the above remove,
# removes also the ';' after 'write_image_manifest'
ROOTFS_POSTPROCESS_COMMAND_remove = " write_image_manifest ;"
ROOTFS_POSTPROCESS_COMMAND += " write_image_manifest;"

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
LOCAL_MACHINE_dmo-edm-comb = "x86"

ssh_allow_empty_password_append() {
    if [ -e ${IMAGE_ROOTFS}${sysconfdir}/ssh/sshd_config_readonly ]; then
        sed -i 's/^[#[:space:]]*PermitRootLogin.*/PermitRootLogin yes/' ${IMAGE_ROOTFS}${sysconfdir}/ssh/sshd_config_readonly
        sed -i 's/^[#[:space:]]*PermitEmptyPasswords.*/PermitEmptyPasswords yes/' ${IMAGE_ROOTFS}${sysconfdir}/ssh/sshd_config_readonly
    fi
}

