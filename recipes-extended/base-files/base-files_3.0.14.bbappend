
do_install_append () {
    install -d ${D}/media/card

    echo "tmpfs          /media tmpfs defaults 0 2" >> ${D}/${sysconfdir}/fstab
    echo "/dev/mmcblk1p4 /home  auto  defaults,nofail 0 2" >> ${D}/${sysconfdir}/fstab
}

