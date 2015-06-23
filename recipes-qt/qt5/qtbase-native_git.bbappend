FILESEXTRAPATHS_prepend := "${THISDIR}/qtbase:"


SRC_URI += " \
            file://0003-Add-external-hostbindir-option.patch \
            file://0012-Add-external-hostbindir-option-for-native-sdk.patch \
            file://0020-configure-debug.patch \
            "

do_patch_append() {
    bb.warn ("Additional patching on qtbase-native is done in meta-dmo, this needs to be reviewed before release")
}
