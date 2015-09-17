FILESEXTRAPATHS_append := "${THISDIR}/${PN}:"
SRC_URI_append_mx6 += "file://vivante-fbdev-disable-xrandr.patch \
	    file://vivante-fbdev-no-cursor-fini-on-exit.patch"
