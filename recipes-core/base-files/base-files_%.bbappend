do_install_append_imx6-dmo-edm-qmx () {
    echo "tmpfs          /media tmpfs defaults 0 2" >> ${D}/${sysconfdir}/fstab
    echo "/dev/mmcblk0p4 /home  auto  defaults,nofail 0 2" >> ${D}/${sysconfdir}/fstab
}

do_install_append_mx6 (){
    if ${@bb.utils.contains('IMAGE_FEATURES', 'read-only-rootfs', 'true', 'false', d)}; then
        echo "tmpfs          /srv  tmpfs defaults 0 2" >> ${D}/${sysconfdir}/fstab
        ln -sf /usr/share/zoneinfo/UT ${D}${sysconfdir}/localtime
    fi
}

