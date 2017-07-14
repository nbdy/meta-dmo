
PACKAGES_prepend = " \
    ${PN}-iwlwifi-3160-13 \
"

RDEPENDS_${PN}-iwlwifi-3160-13   = "${PN}-iwlwifi-license"

FILES_${PN}-iwlwifi-3160-13 = "/lib/firmware/iwlwifi-3160-13.ucode"
