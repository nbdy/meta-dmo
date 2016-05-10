FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
DEPENDS_append += "libgpg-error"

SYSTEMD_NETWORK_FILE ?= "default.network"
SRC_URI_append = " \
    file://0001-fix-udevd-automount.patch \
    file://0020-Revert-root-home-for-rescue-shells.patch \
    file://${SYSTEMD_NETWORK_FILE} \
"
SRC_URI_append += " \
${@bb.utils.contains \
    ( \
        'IMAGE_FEATURES' \
        , 'read-only-rootfs' \
        , 'file://0030-remove-mtab-symlink-creation.patch file://0040-fix-bachlight-servic-after-list.patch' \
        ,'' \
        , d \
    ) \
}"

FILES_${PN}_append = "${sysconfdir}/resolv.conf"

do_install_append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'networkd', 'true', 'false', d)}; then
        install -m 644 ${WORKDIR}/${SYSTEMD_NETWORK_FILE} ${D}/${sysconfdir}/systemd/network/
    fi
    if ${@bb.utils.contains('PACKAGECONFIG', 'resolved', 'true', 'false', d)}; then
        ln -s /var/run/systemd/resolve/resolv.conf ${D}/${sysconfdir}/resolv.conf  
    fi
    install -m 0755 -d ${D}/root
    install -m 0755 -d ${D}/home/root
}

pkg_postinst_${PN}() {
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        if [ -n "$D" ]; then
            OPTS="--root=$D"
        fi
        systemctl $OPTS mask getty@tty1.service
        if ${@bb.utils.contains('IMAGE_FEATURES', 'read-only-rootfs', 'true', 'false', d)}; then
            systemctl $OPTS mask opkg-configure.service
        fi
    fi
}

FILES_${PN}_append += "/root /home /home/root"

