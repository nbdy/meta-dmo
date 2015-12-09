FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append += " \
    file://10xinit.sh \
    file://0001-fix-systemd-service-installation.patch \
"

do_install_append() {
    install -m 755 -d ${D}${sysconfdir}/X11/Xsession.d/
    install -m 755 ${WORKDIR}/10xinit.sh ${D}${sysconfdir}/X11/Xsession.d/
}

FILES_${PN}_append += "${sysconfdir}/X11/Xsession.d/"

