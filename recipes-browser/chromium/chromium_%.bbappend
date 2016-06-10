inherit dmo-launcher-app

DMO_LAUNCHER_EXEC = "/usr/bin/google-chrome"
DMO_LAUNCHER_NAME = "Chromium"
DMO_LAUNCHER_DESC = "Chromium web browser"
DMO_LAUNCHER_ICONPATH = "/usr/bin/chromium/product_logo_48.png"

DEPENDS_remove = "pulseaudio"
DEPENDS += "${@base_contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '', d)}"
DEPENDS += "libxtst"

EXTRA_OEGYP_append += "${@base_contains('DISTRO_FEATURES', 'pulseaudio', '-Duse_pulseaudio=1', '-Duse_pulseaudio=0', d)}"

