include cpu-status-info.inc

PV = "0.1+git${SRCREV}"
PR = "${INC_PR}.1"

SRC_URI = " \
    git://git@emb.data-modul.com/userrepos/jgp/cpu-status-info;protocol=ssh \
"
SRCREV = "8fbc58d5c92422f9c928509b43a6b9b8ca49be66"

S = "${WORKDIR}/git"
