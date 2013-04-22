require barebox.inc

PR = "${INC_PR}.2"
SRC_URI = " \
    git://git@emb.data-modul.com/development/barebox;protocol=ssh \
"
SRCREV = "29677d889bf8f96a4076c0d3c93ea94265b3becc"
LOCALVERSION = "-1.1.0+yocto"

COMPATIBLE_MACHINE = "imx6q-dmo-edm-qmx6"

SRC_URI_append_imx6q-dmo-edm-qmx6 = " \
    file://barebox-defaultenv.tar.gz \
"

do_configure_prepend_imx6q-dmo-edm-qmx6() {
    # Install barebox environment
    cp -a ${WORKDIR}/barebox-defaultenv/* ${WORKDIR}/git/arch/arm/boards/dmo-mx6-realq7/env/

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

