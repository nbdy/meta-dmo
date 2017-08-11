require base-dmo-systemd-distro.inc

DISTRO_FEATURES_remove = "irda zeroconf 3g nfc"

PACKAGECONFIG_append_pn-systemd += "networkd resolved gcrypt"
PACKAGECONFIG_append_pn-qtbase += "tslib icu examples accessibility sql-sqlite fontconfig"

PREFERRED_VERSION_dmec-driver ?= "2017.07.31"

