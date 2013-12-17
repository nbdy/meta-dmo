DESCRIPTION = "kernel-modules - prebuild"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

DWLNAME = "linuxdmo-prebuild"
PR = "r0"

KERNELVERSION = "v3.9-rc7-959-gd58ac5a7-dirty"
TOOLCHAINVERSION = "oselas-tc-2011.11.1"

SRC_URI = "${DMOFTP}/prebuild/${DWLNAME}-${PV}.tar.gz"

COMPATIBLE_MACHINE = "(mx6)"

SRC_URI[md5sum] = "bc394ba6ed1ffd4c4e7c21013ba8056f"
SRC_URI[sha256sum] = "79065dde4ecfdef0eaf6764bc52620e51c5cee66fa935aacae0b3470883f7e78"

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

