inherit dmo-splashlogo

RCONFLICTS_${PN} = "dietsplash-animation"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append += " \
    file://0001-reset-tty-after-dietsplash.patch \
"

EXTRA_OECONF_append += "${@base_contains('SPLASH_SCREEN_TYPE', 'dietsplash', '--with-bg=${WORKDIR}/logo.ppm', '', d)}"

