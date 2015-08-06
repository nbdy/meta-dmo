FILESEXTRAPATHS_prepend := "${THISDIR}/qtbase:"

# QT 5.5 release

SRC_URI += " \
            file://0021-Fixed-unused-identifier-in-not-yet-used-enum.patch \
            "
do_patch_append() {
    bb.warn ("Additional patching on qtbase is done in meta-dmo, this needs to be reviewed before release")
    bb.warn ("The patch 0021-Fixed-unused-identifier-in-not-yet-used-enum.patch for qtbase, modifies an enum without considering the possible uses of the modified value. This patch is done to get the qtbase build.")
}
