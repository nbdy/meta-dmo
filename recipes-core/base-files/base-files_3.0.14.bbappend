
do_install_append () {
    install -d ${D}/media/card

    echo "/dev/mmcblk0p3 /home       auto defaults,sync        0 0" >> ${D}/${sysconfdir}/fstab
    echo "/dev/mmcblk1p3 /media/card auto defaults,sync,noauto 0 0" >> ${D}/${sysconfdir}/fstab
}

