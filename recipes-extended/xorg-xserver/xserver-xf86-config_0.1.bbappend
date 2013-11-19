
do_install_append () {
    mv ${D}/${sysconfdir}/X11/xorg.conf ${D}/${sysconfdir}/X11/xorg.conf.vivante.disabled
}

