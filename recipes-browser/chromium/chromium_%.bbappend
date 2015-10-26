DEPENDS_remove = "pulseaudio"
DEPENDS += "${@base_contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '', d)}"

EXTRA_OEGYP_append += "${@base_contains('DISTRO_FEATURES', 'pulseaudio', '-Dpulseaudio=1', '-Dpulseaudio=0', d)}"

