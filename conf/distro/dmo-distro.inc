require conf/distro/poky.conf

DISTRO_FEATURES_remove = "wayland irda zeroconf 3g nfc"
DISTRO_FEATURES_append = " systemd "
VIRTUAL-RUNTIME_init_manager = " systemd "
VIRTUAL-RUNTIME_initscripts = ""
PACKAGECONFIG_append_pn-systemd += "networkd resolved"

# disable this BACKFILL distro features
# you don't know what BACKFILL is? *tz* look here: http://patches.openembedded.org/patch/21849/
DISTRO_FEATURES_BACKFILL_CONSIDERED += " pulseaudio sysvinit "

# Data-Modul FTP-Server
DMOFTP = "ftp://Public_EMB@ftp.data-modul.com"

# Render rootfs as readonly
IMAGE_FEATURES += " read-only-rootfs package-management "

# some license things here
LICENSE_FLAGS_WHITELIST = "commercial_libav commercial_x264"

# Ship all kernel modules by default
MACHINE_EXTRA_RRECOMMENDS = " kernel-modules"

# Remove files from comercial license recipes
#DMO_REMOVE_FILES_FROM_COMMERCIAL_RECIPES = "1"

PREFERRED_PROVIDER_jpeg = "jpeg"
PREFERRED_PROVIDER_jpeg-native = "jpeg-native"

PREFERRED_VERSION_kern-tools-native_git = "0.2+git83f468ab8ef748a8880cba7d26779c35abfcc0bf"

