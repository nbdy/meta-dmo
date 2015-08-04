#CONFIGUREOPTS += "--disable-compositor"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-disable-compositor-default.patch"
