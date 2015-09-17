FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " file://0010-vivante_wl_egl_window_destroy_workaround.patch"

do_patch_append() {
    bb.warn ("Additional patching on qtwayland is done in meta-dmo-intern, this needs to be reviewed before release")
    bb.warn ("The patch 0010-vivante_wl_egl_window_destroy_workaround.patch skips a destruction call under some circumstances. THIS IS NOT SAFE AND ONLY A WORKAROUND!")
}

