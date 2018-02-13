FILESEXTRAPATHS_append := "${THISDIR}/${PN}:"
SRC_URI_append_mx6 += " \
    file://vivante-fbdev-no-cursor-fini-on-exit.patch \
    file://vivante-fbdev-set-resolution-alignment-16.patch \
    file://vivante-fbdev-disable-xrandr.patch \
"
