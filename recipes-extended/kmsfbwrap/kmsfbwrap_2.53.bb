DESCRIPTION = "KMS frame buffer wrapper"
HOMEPAGE="https://emb.data-modul.com"
LICENSE = "GPLv2"

DEPENDS = "fuse"
DEPENDS += " ${@base_contains('DISTRO_FEATURES', 'pam', 'libpam', '', d)}"

inherit autotools

SRC_URI = "git://git.pengutronix.de/git/tools/kmsfbwrap.git;protocol=git"
SRCREV = "cd10850ac140c4bbf5e423ef2f45d809606e19a1"

S = "${WORKDIR}/git"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=e66651809cac5da60c8b80e9e4e79e08"


