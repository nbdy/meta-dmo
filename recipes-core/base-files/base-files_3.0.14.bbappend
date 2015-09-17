dirs1777_remove_mx6 = "${localstatedir}/volatile/tmp"
dirs1777_append_mx6 += "${localstatedir}/tmp"
volatiles_remove_mx6 = "tmp"

do_install_append_mx6 () {
    echo "tmpfs          /media tmpfs defaults 0 2" >> ${D}/${sysconfdir}/fstab
    echo "/dev/mmcblk1p4 /home  auto  defaults,nofail 0 2" >> ${D}/${sysconfdir}/fstab
}

