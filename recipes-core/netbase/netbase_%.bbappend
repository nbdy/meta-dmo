do_install_append(){

    echo 127.0.0.1    ${MACHINE} >> ${D}${sysconfdir}/hosts
}
