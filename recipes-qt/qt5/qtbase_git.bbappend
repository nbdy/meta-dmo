FILESEXTRAPATHS_prepend := "${THISDIR}/qtbase:"


SRC_URI += "file://0003-Add-external-hostbindir-option.patch \
            file://0011-qmake-don-t-build-it-in-configure-but-allow-to-build.patch \
            file://0012-Set-paths-for-target-properly.patch \
            file://0021-Fixed-unused-identifier-in-not-yet-used-enum.patch \
            "

SRC_URI_remove = "file://0006-eglfs-fix-egl-error-for-platforms-only-supporting-on.patch"

QT_CONFIG_FLAGS += "-external-hostbindir ${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}"

do_patch_append() {
    bb.warn ("Additional patching on qtbase is done in meta-dmo, this needs to be reviewed before release")
    bb.warn ("The patch 0021-Fixed-unused-identifier-in-not-yet-used-enum.patch for qtbase, modifies an enum without considering the possible uses of the modified value. This patch is done to get the qtbase build.")
}
