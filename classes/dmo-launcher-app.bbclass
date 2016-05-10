# Class to generate app files for the dmlauncher

# Howto
# inherit dmo-launcher-app
# Single use
# DMO_LAUNCHER_EXEC = "/usr/bin/dmo-play2.sh a"
# DMO_LAUNCHER_NAME = "dmo-play-c"
# DMO_LAUNCHER_DESC = "Version c"

# Multi use
# DMO_LAUNCHER = "a b c"
# DMO_LAUNCHER_EXEC[a] = "/usr/bin/dmo-play2.sh a"
# DMO_LAUNCHER_EXEC[b] = "/usr/bin/dmo-play2.sh b"
# DMO_LAUNCHER_EXEC[c] = "/usr/bin/dmo-play2.sh c"
# DMO_LAUNCHER_NAME[a] = "dmo-play-a"
# DMO_LAUNCHER_NAME[b] = "dmo-play-b"
# DMO_LAUNCHER_NAME[c] = "dmo-play-c"
# DMO_LAUNCHER_DESC[a] = "Version a"
# DMO_LAUNCHER_DESC[b] = "Version b"
# DMO_LAUNCHER_DESC[c] = "Version c"


HOMEPAGE="https://emb.data-modul.com"
FILES_${PN} += '/usr/share/dmlauncher/apps/ /usr/share/dmlauncher/icons/'
DMO_LAUNCHER_DESC ?= ''
DMO_LAUNCHER_ICONPATH ?= ''

def writeappfile(workdir, targetdir, icondir, execpath, name, desc, icon, iconfile):
    import shutil

    if not execpath:
        bb.error("DMO_LAUNCHER_EXEC is not set")
        return

    if not name:
        bb.error("DMO_LAUNCHER_NAME is not set")
        return

    if not os.path.exists(targetdir):
        os.makedirs(targetdir)

    if iconfile:
        if not os.path.exists(icondir):
            os.makedirs(icondir)

        shutil.copy2(workdir + '/' + iconfile, icondir)

    appfile = targetdir + name + '.app'

    fp = open(appfile, 'w')
    fp.write('Exec=' + execpath + '\n')
    fp.write('Name=' + name + '\n')
    fp.write('Descripton=' + desc + '\n')
    fp.write('Icon=' + icon + '\n')
    fp.close()


python do_dmo_launcher_app() { 
    workdir = d.getVar('WORKDIR', True)
    targetpath = '/usr/share/dmlauncher/apps/'
    iconpath = '/usr/share/dmlauncher/icons/'
    imagedir = workdir + '/image'
    targetdir = imagedir + targetpath
    icondir = imagedir + iconpath

    launcher = d.getVar('DMO_LAUNCHER', False)
    if launcher:
        launcher_split = (d.getVar('DMO_LAUNCHER', True)).split()
        if launcher_split:
            for e in launcher_split:
                execpath = d.getVarFlag('DMO_LAUNCHER_EXEC', e, False)
                name = d.getVarFlag('DMO_LAUNCHER_NAME', e, False)
                desc = d.getVarFlag('DMO_LAUNCHER_DESC', e, False) or ""
                iconpath = d.getVarFlag('DMO_LAUNCHER_ICONPATH', e, False) or ""
                iconfile = d.getVarFlag('DMO_LAUNCHER_ICONFILE', e, False) or ""
                writeappfile(workdir, targetdir, icondir, execpath, name, desc, iconpath, iconfile)
    else:
        execpath = d.getVar('DMO_LAUNCHER_EXEC', False)
        if execpath:
            name = d.getVar('DMO_LAUNCHER_NAME', False)
            desc = d.getVar('DMO_LAUNCHER_DESC', False) or ""
            iconpath = d.getVar('DMO_LAUNCHER_ICONPATH', False) or ""
            iconfile = d.getVar('DMO_LAUNCHER_ICONFILE', False) or ""
            writeappfile(workdir, targetdir, icondir, execpath, name, desc, iconpath, iconfile)
}


addtask do_dmo_launcher_app before do_package after do_install

