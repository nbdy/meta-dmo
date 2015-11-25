inherit dmo-launcher-app

DMO_LAUNCHER_EXEC = "/usr/bin/firefox"
DMO_LAUNCHER_NAME = "FireFox"
DMO_LAUNCHER_DESC = "FireFox web browser"
DMO_LAUNCHER_ICONPATH = "/usr/lib/firefox-38.2/browser/icons/mozicon128.png"

DEPENDS_remove = "pulseaudio startup-notification libxt libxi gtk+ libnotify"
DEPENDS += "${@base_contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '', d)}"
DEPENDS_append += "gstreamer1.0"

mozilla_do_configure_prepend() {
    echo "${@base_contains('DISTRO_FEATURES', 'pulseaudio', '', 'ac_add_options --disable-pulseaudio', d)}" >> ${MOZCONFIG}
    echo "ac_add_options --enable-gstreamer=1.0" >> ${MOZCONFIG}
}

