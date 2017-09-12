FILESEXTRAPATHS_append := "${THISDIR}/${PN}:"
SRC_URI_append_mx6 += " \
    file://vivante-fbdev-no-cursor-fini-on-exit.patch \
    file://vivante-fbdev-set-resolution-alignment-16.patch \
"
SRC_URI_append_imx6s-dmo-vertigo += " \
    file://vivante-fbdev-disable-xrandr.patch \
"

