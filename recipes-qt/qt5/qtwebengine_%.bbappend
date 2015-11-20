FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = "file://0100-add-libjpeg-turbo-to-config.patch"
