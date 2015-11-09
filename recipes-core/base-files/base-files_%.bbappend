dirs1777_remove_imx6-dmo-edm-qmx = "${localstatedir}/volatile/tmp"
dirs1777_append_imx6-dmo-edm-qmx += "${localstatedir}/tmp"
volatiles_remove_imx6-dmo-edm-qmx = "tmp"

do_install_append_imx6-dmo-edm-qmx () {
    echo "tmpfs          /media tmpfs defaults 0 2" >> ${D}/${sysconfdir}/fstab
    echo "/dev/mmcblk1p4 /home  auto  defaults,nofail 0 2" >> ${D}/${sysconfdir}/fstab
}

