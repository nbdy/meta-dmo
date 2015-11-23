DESCRIPTION = "some changes for the root users homedirectory"
HOMEPAGE="https://emb.data-modul.com"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "file://xinitrc"

FILES_${PN} += "/home"

# Prevents do_package failures with:
# debugsources.list: No such file or directory:
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_install () {
    install -m 700 -d ${D}/home/root

    if [ ${@base_contains('DISTRO_FEATURES', 'x11', 'true', '',  d)} == true ]; then
        install -m 700 ${WORKDIR}/xinitrc ${D}/home/root/.xinitrc
    fi

    chown -R root:root ${D}/home/root
}
