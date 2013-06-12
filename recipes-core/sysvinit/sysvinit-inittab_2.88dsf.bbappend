
do_install_append () {
    echo "x:5:respawn:/usr/bin/startxfce4 >& /dev/null" >> ${D}${sysconfdir}/inittab
}

