require recipes-graphics/dietsplash/dietsplash_git.bb

RCONFLICTS_${PN} = "dietsplash"

FILESEXTRAPATHS_prepend := "${THISDIR}/dietsplash:"

SRC_URI = " \
    git://github.com/OSSystems/dietsplash.git;protocol=http;branch=animation \
    file://logo_dm.ppm \
    file://desc.txt \
    file://part0 \
    file://0001-reset-tty-after-dietsplash.patch \
"

SRCREV = "7974771dc123c3a975f6a79a7f5a6c15a69f7b74"

EXTRA_OECONF_append += " \
    --with-bg=${WORKDIR}/logo_dm.ppm \
    --enable-animation \
    --with-animation=/usr/share/dietsplash/data/dm_animation/ \
"

do_install_append(){
    install -d ${D}/usr/share/dietsplash/data/dm_animation/part0
    install ${WORKDIR}/desc.txt ${D}/usr/share/dietsplash/data/dm_animation/
    install ${WORKDIR}/part0/logo_dm0.ppm ${D}/usr/share/dietsplash/data/dm_animation/part0
    install ${WORKDIR}/part0/logo_dm1.ppm ${D}/usr/share/dietsplash/data/dm_animation/part0

    rm -rf ${D}/usr/share/dietsplash/data/default_animation
}

FILES_${PN}_append += "/usr/share/dietsplash/"
