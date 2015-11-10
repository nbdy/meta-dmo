DEPENDS_remove = "pulseaudio"
DEPENDS += "${@base_contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '', d)}"

EXTRA_OEGYP_append += "${@base_contains('DISTRO_FEATURES', 'pulseaudio', '-Duse_pulseaudio=1', '-Duse_pulseaudio=0', d)}"

