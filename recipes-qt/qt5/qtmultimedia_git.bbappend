require qt5-dmo.inc
FILESEXTRAPATHS_prepend := "${THISDIR}/qtmultimedia:"


SRC_URI += "file://0002-Fix-porting-effort-to-GStreamer-patch.patch"

do_patch_append() {
    bb.warn ("Additional patching on qtmultimedia is done in meta-dmo, this needs to be reviewed before release")
}
