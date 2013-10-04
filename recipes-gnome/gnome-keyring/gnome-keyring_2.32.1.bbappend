
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://egg-asn1x.patch;name=eggpatch"

SRC_URI[eggpatch.md5sum] = "92e01fbf43f1889de49b5d0daae0a782"
SRC_URI[eggpatch.sha256sum] = "94c1ee27a7dc2ab9e601665d37b6c39d4744599f2ddd72d464751c60296fef4b"
