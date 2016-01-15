inherit dmo-launcher-app

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://preview_cinematic.png"

DMO_LAUNCHER_EXEC = "/usr/bin/Qt5_CinematicExperience"
DMO_LAUNCHER_NAME = "Cinematicexperienece"
DMO_LAUNCHER_DESC = "QT5 cinematic demo"
DMO_LAUNCHER_ICONPATH = "/usr/share/dmlauncher/icons/preview_cinematic.png"
DMO_LAUNCHER_ICONFILE = "preview_cinematic.png"
