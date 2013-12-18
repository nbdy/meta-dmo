DESCRIPTION = "kernel-modules - prebuild"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

DWLNAME = "linuxdmo-prebuild"
PR = "r0"

KERNELVERSION = "v3.9-rc7-959-gda5ab8a-dirty"
TOOLCHAINVERSION = "oselas-tc-2011.11.1"

SRC_URI = "${DMOFTP}/prebuild/${DWLNAME}-${PV}.tar.gz"

COMPATIBLE_MACHINE = "(mx6)"

SRC_URI[md5sum] = "f725df1b5bbb84331d38fc3a7df8dffd"
SRC_URI[sha256sum] = "3851a8fbfcd01c37dbbeb1074fa5a1c2e002f0115f1c549ca7a3fe6b75f50e4a"

S = "${WORKDIR}/${DWLNAME}-${PV}"

FILES_${PN} += "/lib"

do_install () {
    # modules
    cp -av ${S}/${KERNELVERSION}/${TOOLCHAINVERSION}/DIST/* ${D}
    find ${D} -iname "build" -exec rm {} \;
    find ${D} -iname "source" -exec rm {} \;
}

do_deploy () {
    tar -C ${S}/${KERNELVERSION}/${TOOLCHAINVERSION}/DIST -cvzf ${DEPLOY_DIR_IMAGE}/modules--${PV}.tgz lib
    ln -sf modules--${PV}.tgz ${DEPLOY_DIR_IMAGE}/modules-${MACHINE}.tgz
}

