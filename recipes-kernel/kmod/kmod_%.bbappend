FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://blacklist.conf"

do_install_append() {
    install -m 0644 ${WORKDIR}/blacklist.conf ${D}${sysconfdir}/modprobe.d/
}
