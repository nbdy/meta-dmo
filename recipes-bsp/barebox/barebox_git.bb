require barebox.inc

PV = "2014.10.0+git${SRCREV}"
SRC_URI = " \
    git://github.com/data-modul/barebox.git;protocol=https;branch=dmo-release-2014.10;name=mx6 \
"
SRCREV_mx6="${AUTOREV}"

COMPATIBLE_MACHINE = "imx6q-dmo-edm-qmx6"

S = "${WORKDIR}/git"
