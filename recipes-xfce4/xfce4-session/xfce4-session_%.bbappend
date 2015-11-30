do_install_append(){
    if [ -e ${D}/etc/xdg/autostart/xscreensaver.desktop ]; then
        rm ${D}/etc/xdg/autostart/xscreensaver.desktop
    fi
}
