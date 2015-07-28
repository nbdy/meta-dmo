FILESEXTRAPATHS_prepend := "${THISDIR}/qtbase:"

SRCREV = "${AUTOREV}"
# QT 5.5 release
#SRCREV = "fae33bfbe35f8d082b420ee09662ff60634cb355"

LIC_FILES_CHKSUM = "file://LICENSE.LGPLv21;md5=58a180e1cf84c756c29f782b3a485c29 \
                    file://LICENSE.LGPLv3;md5=52e79d98c9d87b0461f3f708c6cb76dc\
                    file://LGPL_EXCEPTION.txt;md5=9625233da42f9e0ce9d63651a9d97654 \
                    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e"

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
