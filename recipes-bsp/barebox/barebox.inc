
#
# This file is a fork of meta-fsl-arm/recipes-bsp/barebox/barebox.inc
# with some adaptions for our barebox in git repository
#

DESCRIPTION = "Barebox - a bootloader that inherits the best of U-Boot and the Linux kernel"
HOMEPAGE = "http://www.barebox.org/"
SECTION = "bootloader"
PROVIDES = "virtual/bootloader"
PRIORITY = "optional"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=057bf9e50e1ca857d0eb97bfe4ba8e5d"

DEPENDS = "lzop-native coreutils-native"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit kernel-arch
inherit cml1
inherit dmo-checksum

EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX}"

BAREBOX_BINARY ?= "barebox.bin"
BAREBOX_IMAGE ?= "barebox-${MACHINE}-${PV}-${PR}.bin"
BAREBOX_SYMLINK ?= "barebox-${MACHINE}.bin"

INSANE_SKIP_${PN} = "True"
OVERLAYFS_PARTITION ?= "/dev/mmcblk0p3"


do_compile () {
        if [ ${@bb.utils.contains('IMAGE_FEATURES', 'read-only-rootfs', 'true', 'false', d)} == 'false' ]; then
            echo "global linux.bootargs.overlayrw=\"overlayrw=${OVERLAYFS_PARTITION}\"" >> ${S}/defaultenv/defaultenv-2-base/config
        fi

	if [ "${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', 'ld-is-gold', '', d)}" = "ld-is-gold" ] ; then
		sed -i 's/$(CROSS_COMPILE)ld/$(CROSS_COMPILE)ld.bfd/g' Makefile
	fi
	unset LDFLAGS
	unset CFLAGS
	unset CPPFLAGS
	oe_runmake all
}

do_deploy () {
        # manipulate environment
	install -d ${DEPLOY_DIR_IMAGE}
	install ${S}/${BAREBOX_BINARY} ${DEPLOY_DIR_IMAGE}/${BAREBOX_IMAGE}

        # deploy ...
	cd ${DEPLOY_DIR_IMAGE}
	rm -f ${BAREBOX_SYMLINK}
	ln -sf ${BAREBOX_IMAGE} ${BAREBOX_SYMLINK}

	install -d ${STAGING_BINDIR_NATIVE}
	cd ${S}
	install -m 755 scripts/mkimage ${STAGING_BINDIR_NATIVE}/
	install -m 755 scripts/bareboxenv ${STAGING_BINDIR_NATIVE}/

        dmo_do_checksum ${DEPLOY_DIR_IMAGE} ${BAREBOX_IMAGE}
}
do_deploy[dirs] = "${S}"
addtask deploy before do_install after do_compile
