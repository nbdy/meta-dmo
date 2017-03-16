# Class to generate md5sum in given path for given file

HOMEPAGE="https://emb.data-modul.com"

DEPENDS_append += "imagemagick-native"

USE_DIETSPLASH = "${@bb.utils.contains('SPLASH_SCREEN_TYPE', 'dietsplash', 'true', '', d)}"
USE_KERNEL = "${@bb.utils.contains('SPLASH_SCREEN_TYPE', 'kernel', 'true', '', d)}"

do_dmo_convert_logo() {
    cp ${SPLASH_SCREEN_PATH}${SPLASH_SCREEN_IMAGE} ${WORKDIR}

    ${STAGING_BINDIR_NATIVE}/convert.im7 ${WORKDIR}/${SPLASH_SCREEN_IMAGE} -resize ${SPLASH_SCREEN_DIMENSION} -compress none -colors 224 ${WORKDIR}/logo.ppm 
    
    if [[ "${USE_KERNEL}" = "true" && "${PN}" != "dietsplash" ]]; then
        cp ${WORKDIR}/logo.ppm ${S}/drivers/video/logo/logo_linux_clut224.ppm
    fi
    if [[ "${USE_DIETSPLASH}" = "true" ]]; then
        bbnote "Needed modifications are done in the dietsplash recipe"
    fi
    if [[ "${USE_KERNEL}" = ""  &&  "${USE_DIETSPLASH}" = "" ]]; then
        bberror "SPLASH_SCREEN_TYPE is not defined or not supported. Supported values are 'kernel' or 'dietsplash'"
        exit -1
    fi
}

addtask do_dmo_convert_logo after do_configure before do_compile

