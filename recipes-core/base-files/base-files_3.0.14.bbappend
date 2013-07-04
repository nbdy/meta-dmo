
do_install_append () {
    install -d ${D}/media/card

    echo "/dev/mmcblk0p3 /home       auto defaults       0 2" >> ${D}/${sysconfdir}/fstab
    echo "/dev/mmcblk1p3 /media/card auto defaultsnoauto 0 2" >> ${D}/${sysconfdir}/fstab
}

