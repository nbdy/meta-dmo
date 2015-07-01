FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "${@bb.utils.contains('IMAGE_FEATURES', 'read-only-rootfs', 'file://0001-systemd-sshd-read-only', ' ', d)}"

python do_unpack_append() {
    
    if bb.utils.contains('IMAGE_FEATURES', 'read-only-rootfs', 'true', 'false', d) == 'true':
        os.system("patch -p1 < 0001-systemd-sshd-read-only")
}

