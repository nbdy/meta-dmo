include linux-dmo-yocto.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/kernelcfgs:${THISDIR}/${PN}:"

SRC_URI_append_dmo-edm-comb += "file://0001-i2c-i801-Allow-ACPI-SystemIO-OpRegion-to-conflict-wi.patch"

