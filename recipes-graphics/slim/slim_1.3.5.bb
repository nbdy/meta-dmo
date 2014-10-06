DESCRIPTION = "Simple Login Manager"
HOMEPAGE = "http://slim.berlios.de"
LICENSE = "GPLv2"

DEPENDS = "virtual/libx11 libxmu libpng jpeg freetype sessreg ttf-bitstream-vera"
DEPENDS += " ${@base_contains('DISTRO_FEATURES', 'pam', 'libpam', '', d)}"

inherit cmake

PR = "r6"

SRC_URI = "git://github.com/data-modul/slim;protocol=git;tag=v${PV} \
           file://slim.sh \
           file://0001-Fix-systemd-service-install-path.patch \
           file://0003-Fix-install-for-slim.service-3418.patch"

S = "${WORKDIR}/git"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

CONFFILES_${PN} += "${sysconfdir}/slim.conf"

RDEPENDS_${PN} = "xauth"

PACKAGES += "${PN}-systemd"
FILES_${PN}-systemd = "/usr/systemd"

do_install_append() {
    install -d ${D}/${sysconfdir}/{init,rc5}.d
    install -m 755 ${WORKDIR}/slim.sh ${D}/${sysconfdir}/init.d
    ln -sf ../init.d/slim.sh ${D}/${sysconfdir}/rc5.d/S99slim
}
