require qt5-dmo.inc
FILESEXTRAPATHS_prepend := "${THISDIR}/qtmultimedia:"

PACKAGECONFIG_append += "gstreamer"

SRC_URI += "file://0002-Fix-porting-effort-to-GStreamer-patch.patch \
	    file://0001-gstreamer-hack-v4l2sink-in-playbin.patch \
	    file://0002-gstreamer-get-right-window-position.patch \
	    file://0003-gstreamer-fix-for-qml-window-crop-coordinates.patch \
	    file://0004-gstreamer-use-dma-between-decoder-and-converter.patch \
	    file://0005-gstreamer-set-v4l2sink-crop-win-at-play-too.patch \
	    file://0006-gstreamer-fix-stop-function.patch \
	    file://0007-gstreamer-detect-v4l2-device.patch \
"



SRCREV="04e086e33667ca1a7aa8a7be7427bfba491a72b0"

do_patch_append() {
    bb.warn ("Additional patching on qtmultimedia is done in meta-dmo, this needs to be reviewed before release")
}
