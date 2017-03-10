include linux-dmo-yocto.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/kernelcfgs:${THISDIR}/${PN}:"

SRC_URI_append_dmo-edm-comb-bs6 += " \
    file://0001-i915-intel-graphic-driver-mapped-to-Braswell-DDC-4-8-9.patch \
"
