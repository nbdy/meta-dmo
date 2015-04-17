FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = "file://eth0.network"

FILES_${PN}_append = "${sysconfdir}/resolv.conf"

do_install_append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'networkd', 'true', 'false', d)}; then
        install -m 644 ${WORKDIR}/eth0.network ${D}/${sysconfdir}/systemd/network/ 
    fi

    if ${@bb.utils.contains('PACKAGECONFIG', 'resolved', 'true', 'false', d)}; then
        ln -s /var/run/systemd/resolve/resolv.conf ${D}/${sysconfdir}/resolv.conf  
    fi
}

pkg_postinst_${PN}() {
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        if [ -n "$D" ]; then
            OPTS="--root=$D"
        fi
        systemctl $OPTS mask getty@tty1.service
    fi
}

