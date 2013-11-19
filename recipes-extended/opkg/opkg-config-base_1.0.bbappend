
do_compile_append () {
    cat > ${S}/${sysconfdir}/opkg/opkg.conf << EOF

dest root /

src/gz dmo_all http://10.0.0.1/all
src/gz dmo_arm http://10.0.0.1/cortexa9hf-vfp-neon
src/gz dmo_mx6 http://10.0.0.1/cortexa9hf-vfp-neon-mx6
src/gz dmo_imx http://10.0.0.1/imx6q_dmo_edm_qmx6

EOF
}

CONFFILES_${PN} += "${sysconfdir}/opkg/opkg.conf"

