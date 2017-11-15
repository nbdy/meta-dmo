FILESEXTRAPATHS_append := "${THISDIR}/${PN}:"
SRC_URI_append_mx6 += " \
    file://vivante-fbdev-no-cursor-fini-on-exit.patch \
    file://vivante-fbdev-set-resolution-alignment-16.patch \
"
SRC_URI_append_imx6s-dmo-vertigo += " \
    file://vivante-fbdev-disable-xrandr.patch \
"

SRC_URI_remove_mx6 = "git://source.codeaurora.org/external/imx/xf86-video-imx-vivante.git;protocol=https;branch=${SRCBRANCH}"

SRC_URI_append_mx6 += "${FSL_MIRROR}/xserver-xorg-video-imx-viv-${PV}.tar.gz"

SRC_URI[md5sum] = "8acbdddd51c9b1b0fd25137eeabd786d"
SRC_URI[sha256sum] = "3eed38193e31bb5ba8d2c817bd9b4f6e2fe7540d2cab36de9098cb4f11946a9f"
