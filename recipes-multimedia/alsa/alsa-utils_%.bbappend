FILESEXTRAPATHS_prepend := "${THISDIR}/alsa-utils:"

SUMMARY = "Alsa software mixer config file"
DESCRIPTION = "This file makes possible to use software mixing with alsa"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r0"

SRC_URI_append_dmo-edm-comb-bw6 += " \
	  file://alsa.conf \
	  "

do_install_append_dmo-edm-comb-bw6() {
	
	install -d ${D}${sysconfdir}/modprobe.d
	install -m 0644 ${WORKDIR}/alsa.conf ${D}${sysconfdir}/modprobe.d

}

FILES_${PN}_append_dmo-edm-comb-bw6 = " ${sysconfdir}/modprobe.d/alsa.conf "
CONFFILES_${PN}_append_dmo-edm-comb-bw6 = " ${sysconfdir}/modprobe.d/alsa.conf "
