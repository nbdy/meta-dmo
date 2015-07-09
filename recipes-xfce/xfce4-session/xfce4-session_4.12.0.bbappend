# protect from frightening message that xfce might not work correctly
pkg_postinst_${PN} () {
    echo 127.0.0.1    ${MACHINE} >> /etc/hosts
}

