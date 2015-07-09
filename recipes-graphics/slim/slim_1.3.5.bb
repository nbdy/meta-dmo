DESCRIPTION = "Simple Login Manager"
HOMEPAGE = "http://slim.berlios.de"
LICENSE = "GPLv2"

DEPENDS = "virtual/libx11 libxft libxmu libpng jpeg freetype sessreg ttf-bitstream-vera"
DEPENDS += " ${@base_contains('DISTRO_FEATURES', 'pam', 'libpam', '', d)}"
RDEPENDS_${PN} += "libxft"

inherit cmake
inherit systemd

SRC_URI = "git://github.com/data-modul/slim;protocol=git;tag=v${PV} \
           file://slim.sh \
           file://0001-Fix-sytemd-install-path.patch \
           file://0002-Fix-slim.service-install-part.patch "

S = "${WORKDIR}/git"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

CONFFILES_${PN} += "${sysconfdir}/slim.conf"

RDEPENDS_${PN} = "xauth"

PACKAGES += "${PN}-systemd"
FILES_${PN}-systemd = "/usr/lib/systemd"

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE_${PN}-systemd = "slim.service"

do_install_append() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
        install -d ${D}/${sysconfdir}/{init,rc5}.d
        install -m 755 ${WORKDIR}/slim.sh ${D}/${sysconfdir}/init.d
        ln -sf ../init.d/slim.sh ${D}/${sysconfdir}/rc5.d/S99slim
    fi

    # Enable auto login for root user
    sed -e "s/#\s*default_user\s*simone/default_user root/" -i ${D}/${sysconfdir}/slim.conf
    sed -e "s/#\s*auto_login\s*no/auto_login yes/" -i ${D}/${sysconfdir}/slim.conf
}
