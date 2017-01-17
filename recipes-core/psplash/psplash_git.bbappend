inherit systemd

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " file://psplash-start.service \
                   file://psplash-quit.service \
           "
SYSTEMD_PACKAGES = "${@bb.utils.contains('DISTRO_FEATURES','systemd','${PN}','',d)}"
SYSTEMD_SERVICE_${PN} = "${@bb.utils.contains('DISTRO_FEATURES','systemd','psplash-start.service psplash-quit.service','',d)}"

do_install_append() {
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -m 644 ${WORKDIR}/*.service ${D}/${systemd_unitdir}/system
    fi

}

