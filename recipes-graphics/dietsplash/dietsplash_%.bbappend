RCONFLICTS_${PN} = "dietsplash-animation"

DEPENDS_append += "imagemagick-native"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append += " \
    file://0001-reset-tty-after-dietsplash.patch \
    ${SPLASH_SCREEN_IMAGE} \
"

EXTRA_OECONF_append = "\
    --with-bg=${WORKDIR}/logo.ppm \
"

do_configure_prepend(){
    INDEX=`expr index "${SPLASH_SCREEN_IMAGE}" / + 1`
    LOGO=${SPLASH_SCREEN_IMAGE}
    SPLASHLOGO=${LOGO:$INDEX}

    convert.im6 ${WORKDIR}/$SPLASHLOGO -compress none -resize ${SPLASH_SCREEN_DIMENSION} ${WORKDIR}/logo.ppm
}
