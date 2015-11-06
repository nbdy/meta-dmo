require base-dmo-systemd-distro.inc

QT_MODULE_BRANCH = "5.5"
PREFERRED_VERSION_gstreamer1.0 = "1.5.2"
PREFERRED_VERSION_gstreamer1.0-meta-base = "1.5.2"
PREFERRED_VERSION_gstreamer1.0-plugins-bad = "1.5.2"
PREFERRED_VERSION_gstreamer1.0-plugins-base = "1.5.2"
PREFERRED_VERSION_gstreamer1.0-plugins-good = "1.5.2"
PREFERRED_VERSION_gstreamer1.0-plugins-ugly = "1.5.2"

DISTRO_FEATURES_remove = "irda zeroconf 3g nfc"

PACKAGECONFIG_append_pn-systemd += "networkd resolved"
PACKAGECONFIG_append_pn-qtbase += "tslib icu examples accessibility sql-sqlite"

