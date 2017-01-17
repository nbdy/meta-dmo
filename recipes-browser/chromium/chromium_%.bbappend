DEPENDS_remove = "pulseaudio"
DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '', d)}"
DEPENDS += "libxtst"

EXTRA_OEGYP_append += "${@bb.utils.contains('DISTRO_FEATURES', 'pulseaudio', '-Duse_pulseaudio=1', '-Duse_pulseaudio=0', d)}"

