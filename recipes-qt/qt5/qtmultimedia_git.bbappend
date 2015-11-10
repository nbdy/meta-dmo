FILESEXTRAPATHS_prepend := "${THISDIR}/qtmultimedia:"

PACKAGECONFIG_append_mx6 += "gstreamer"

SRC_URI_append_mx6 += "\
	    file://0001-gstreamer-hack-v4l2sink-in-playbin.patch \
	    file://0002-gstreamer-get-right-window-position.patch \
	    file://0003-gstreamer-fix-for-qml-window-crop-coordinates.patch \
	    file://0004-gstreamer-use-dma-between-decoder-and-converter.patch \
	    file://0005-gstreamer-set-v4l2sink-crop-win-at-play-too.patch \
	    file://0006-gstreamer-fix-stop-function.patch \
	    file://0007-gstreamer-detect-v4l2-device.patch \
	    file://0008-gstreamer-fix-overlay-window-coordinates.patch \
            file://0009-gstreamer-distinguish-audio-and-video-pipelines.patch \
            file://0010-gstreamer-register-typefind-function-on-pause.patch \
"

do_patch_append_mx6() {
    bb.warn ("Additional patching on qtmultimedia is done in meta-dmo, this needs to be reviewed before release")
}
