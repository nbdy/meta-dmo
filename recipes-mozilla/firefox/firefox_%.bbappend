DEPENDS_remove = "pulseaudio startup-notification libxt libxi libnotify"
DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '', d)}"
DEPENDS_append += "gstreamer1.0"

mozilla_do_configure_prepend() {
    echo "${@bb.utils.contains('DISTRO_FEATURES', 'pulseaudio', '', 'ac_add_options --disable-pulseaudio', d)}" >> ${MOZCONFIG}
    echo "ac_add_options --enable-gstreamer=1.0" >> ${MOZCONFIG}
    echo "ac_add_options --disable-startup-notification" >> ${MOZCONFIG}
}

