FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append += " \
    file://0001-reset-tty-after-dietsplash.patch \
"

