require barebox.inc

PR = "r4"

SRC_URI = "git://git@192.168.100.52/development/barebox;protocol=ssh;tag=sfr/imx6-patches-v2012.11.0"

COMPATIBLE_MACHINE = "imx6q-dmo-realq7"

do_configure_prepend() {
	# cp ${WORKDIR}/git/arch/arm/configs/${BAREBOX_MACHINE} ${S}/.config
	oe_runmake -C ${WORKDIR}/git O=${S} ${BAREBOX_MACHINE}
}

