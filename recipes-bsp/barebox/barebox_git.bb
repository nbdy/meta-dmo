require barebox.inc

PR = "${INC_PR}.3"
SRC_URI = " \
    git://git@emb.data-modul.com/development/barebox;protocol=ssh \
"
SRCREV = "2ea3fabd36c32c8df57377f91a7736306f7cdb67"
LOCALVERSION = "-1.1.0+yocto"

COMPATIBLE_MACHINE = "imx6q-dmo-edm-qmx6"

do_configure_prepend_imx6q-dmo-edm-qmx6() {
    # Install barebox environment
    for line in ${BAREBOX_ADD_FILES_TO_ENVIRONMENT}
    do

# It is a little bit ugly, but the next three lines should start at column 0
IFS=":" read src dst <<EOL
${line}
EOL

        cp $src ${WORKDIR}/git/arch/arm/boards/dmo-mx6-realq7/${dst}
    done

    # cp ${WORKDIR}/git/arch/arm/configs/${BAREBOX_MACHINE} ${S}/.config
    oe_runmake ${BAREBOX_MACHINE}
}
# before we can configure we need the generated dtb
do_configure[depends] += "virtual/kernel:do_install"

