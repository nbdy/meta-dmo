require linux-dmo.inc

# Override SRC_URI in a bbappend file to point at a different source
# tree if you do not want to build from Linus' tree.
SRC_URI = "git://git@emb.data-modul.com/userrepos/rya/linux;branch=rya/temp-gpu-viv-p8;protocol=ssh;name=kernel \
    file://debug.cfg \
    file://hardware.cfg \
    file://zconfig.cfg \
"

LINUX_VERSION ?= "4.1.18"
LINUX_VERSION_EXTENSION = "+git${SRCPV}"

# Override SRCREV to point to a different commit in a bbappend file to
# build a different release of the Linux kernel.
SRCREV_kernel="${AUTOREV}"

PV = "${LINUX_VERSION}-${PR}+git${SRCPV}"

# Override COMPATIBLE_MACHINE to include your machine in a bbappend
# file. Leaving it empty here ensures an early explicit build failure.
COMPATIBLE_MACHINE = "(imx6q-dmo-edm-qmx6|imx6dl-dmo-edm-qmx6)"

