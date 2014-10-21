
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

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit kernel-arch
inherit cml1

EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX}"

BAREBOX_BINARY ?= "barebox.bin"
BAREBOX_IMAGE ?= "barebox-${MACHINE}-${PV}-${PR}.bin"
BAREBOXENV_BIN ?= "bareboxenv-${MACHINE}-${PV}-${PR}.bin"
BAREBOX_SYMLINK ?= "barebox-${MACHINE}.bin"
BAREBOXENV_SYMLINK ?= "bareboxenv-${MACHINE}.bin"

INSANE_SKIP_${PN} = "True"


do_compile () {
	if [ "${@base_contains('DISTRO_FEATURES', 'ld-is-gold', 'ld-is-gold', '', d)}" = "ld-is-gold" ] ; then
		sed -i 's/$(CROSS_COMPILE)ld/$(CROSS_COMPILE)ld.bfd/g' Makefile
	fi
	unset LDFLAGS
	unset CFLAGS
	unset CPPFLAGS
	oe_runmake all
}

do_configure_prepend_imx6q-dmo-edm-qmx6() {
    oe_runmake ${BAREBOX_MACHINE}
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
}
# before we can configure we need the generated dtb
do_deploy[depends] += "virtual/kernel:do_install"

do_deploy[dirs] = "${S}"
addtask deploy before do_build after do_compile
