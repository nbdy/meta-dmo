
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://90-video.rules"

do_install_append () {
    install -m 0644 ${WORKDIR}/90-video.rules ${D}${sysconfdir}/udev/rules.d/90-video.rules
}

do_install_append_mx6() {
    echo "/dev/mmcblk1p1" >> ${D}/${sysconfdir}/udev/mount.blacklist
    echo "/dev/mmcblk1p4" >> ${D}/${sysconfdir}/udev/mount.blacklist
}

