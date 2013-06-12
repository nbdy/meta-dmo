
do_compile_append () {
    cat > ${S}/${sysconfdir}/opkg/opkg.conf << EOF

dest root /

src/gz dmo_all http://emb.data-modul.com/dl/packagemanagement/all
src/gz dmo_arm http://emb.data-modul.com/dl/packagemanagement/armv7a-vfp-neon
src/gz dmo_imx http://emb.data-modul.com/dl/packagemanagement/imx6q_dmo_edm_qmx6

EOF
}

CONFFILES_${PN} += "${sysconfdir}/opkg/opkg.conf"

