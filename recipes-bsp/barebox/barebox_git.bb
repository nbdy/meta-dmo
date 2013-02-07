require barebox.inc

PR = "${INC_PR}.1"
SRC_URI = " \
    git://git@emb.data-modul.com/development/barebox;protocol=ssh;tag=sfr/imx6-patches-v2012.11.0 \
"

COMPATIBLE_MACHINE = "imx6q-dmo-realq7"

SRC_URI_append_imx6q-dmo-realq7 = " \
    file://barebox-defaultenv.tar.gz \
"

do_configure_prepend_imx6q-dmo-realq7() {
        # Install barebox environment
        cp -a ${WORKDIR}/barebox-defaultenv/* ${WORKDIR}/git/arch/arm/boards/dmo-mx6-realq7/env/

	# cp ${WORKDIR}/git/arch/arm/configs/${BAREBOX_MACHINE} ${S}/.config
	oe_runmake -C ${WORKDIR}/git O=${S} ${BAREBOX_MACHINE}
}

