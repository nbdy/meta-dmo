require recipes-kernel/linux/linux-dmo.inc

# Override SRC_URI in a bbappend file to point at a different source
# tree if you do not want to build from Linus' tree.
SRC_URI = "git://git@emb.data-modul.com/development/linux;protocol=ssh;branch=rya/ppc/release-4.4.57;name=kernel"
FILESEXTRAPATHS_prepend := "${THISDIR}/linux-dmo:"

#SRC_URI_append += "file://hardware.cfg \
#           file://zconfig.cfg \
#           file://develop.cfg \
#           file://debug.cfg \
#"


LINUX_VERSION ?= "4.4.57"
LINUX_VERSION_EXTENSION = "+git${@legitimize_package_name('${SRCPV}')}"

# Override SRCREV to point to a different commit in a bbappend file to
# build a different release of the Linux kernel.
SRCREV_kernel = "${AUTOREV}"

PV = "${LINUX_VERSION}-${PR}+git${SRCPV}"

# Override COMPATIBLE_MACHINE to include your machine in a bbappend
# file. Leaving it empty here ensures an early explicit build failure.
COMPATIBLE_MACHINE = "(mx6)"

RDEPENDS_kernel-base = ""

do_configure_prepend() {
	cat ${S}/arch/${ARCH}/configs/dmo-imx6-ppc_defconfig >>  ${WORKDIR}/defconfig
}
