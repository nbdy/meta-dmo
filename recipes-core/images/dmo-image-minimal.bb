DESCRIPTION = "A small image just capable of allowing a device to boot."

require dmo-image.inc

# remove not needed ipkg informations
ROOTFS_POSTPROCESS_COMMAND += "remove_packaging_data_files ; "

PR = "${INC_PR}.0"

