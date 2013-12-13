DESCRIPTION = "kernel-modules - prebuild"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PV = "prebuild-${KERNELVERSION}-${TOOLCHAINVERSION}"
PR = "r1"

KERNELVERSION = "v3.9-rc7-958-g9971099-dirty"
TOOLCHAINVERSION = "oselas-tc-2011.11.1"

SRC_URI = "git://git@emb.data-modul.com/userrepos/sfr/linuxdmo-prebuild;protocol=ssh"
S = "${WORKDIR}/git"

SRCREV = "992a721ca91bea2490d640de5af3896e3f609eaa"

COMPATIBLE_MACHINE = "(mx6)"

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

