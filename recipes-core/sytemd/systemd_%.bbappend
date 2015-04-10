pkg_postinst_${PN}() {
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        if [ -n "$D" ]; then
            OPTS="--root=$D"
        fi
        systemctl $OPTS mask getty@tty1.service
    fi
}
