
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://rcstartxfce4.sh \
            file://defaultxfce4"

do_install_append () {
    install -d ${D}/${sysconfdir}/{default,{rc1,init}.d}

    install ${WORKDIR}/rcstartxfce4.sh ${D}/${sysconfdir}/init.d/startxfce.sh
    ln -sf ../init.d/startxfce.sh ${D}/${sysconfdir}/rc1.d/S99startxfce4.sh

    install ${WORKDIR}/defaultxfce4 ${D}/${sysconfdir}/default/xfce4
}


