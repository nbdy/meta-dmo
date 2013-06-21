
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

do_install_append () {
    install ${WORKDIR}/inittab ${D}/${sysconfdir}/inittab
}

