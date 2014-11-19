require barebox.inc

PV = "2014.10.0+git${SRCREV}"
SRC_URI = " \
    git://git@emb.data-modul.com/development/barebox;protocol=ssh;branch=zdo/swu;name=mx6 \
"
SRCREV_mx6="${AUTOREV}"

COMPATIBLE_MACHINE = "imx6q-dmo-edm-qmx6"

S = "${WORKDIR}/git"
