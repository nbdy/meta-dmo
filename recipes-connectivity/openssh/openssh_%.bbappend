FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "${@bb.utils.contains('IMAGE_FEATURES', 'read-only-rootfs', 'file://0001-systemd-sshd-read-only.patch;patchdir=..', ' ', d)}"
