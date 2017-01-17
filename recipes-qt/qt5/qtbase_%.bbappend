PACKAGECONFIG_CONFARGS_append += "-qpa ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xcb', 'eglfs', d)}"

