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
FILES_${PN} += '/usr/share/dmlauncher/apps/'
DMO_LAUNCHER_DESC ?= ''
DMO_LAUNCHER_ICONPATH ?= ''

def writeappfile(targetdir, execpath, name, desc, icon):

    appfile = targetdir + name + '.app'

    if not execpath:
        bb.error("DMO_LAUNCHER_EXEC is not set")

    if not name:
        bb.error("DMO_LAUNCHER_NAME is not set")

    if not os.path.exists(targetdir):
        os.makedirs(targetdir)

    fp = open(appfile, 'w')
    fp.write('Exec=' + execpath + '\n')
    fp.write('Name=' + name + '\n')
    fp.write('Descripton=' + desc + '\n')
    fp.write('Icon=' + icon + '\n')
    fp.close()


python do_dmo_launcher_app() { 
    workdir = d.getVar('WORKDIR', True)
    targetpath = '/usr/share/dmlauncher/apps/'
    imagedir = workdir + '/image'
    targetdir = imagedir + targetpath

    launcher = d.getVar('DMO_LAUNCHER')
    if launcher:
        launcher_split = (d.getVar('DMO_LAUNCHER', True)).split()
        if launcher_split:
            for e in launcher_split:
                bb.warn(e)
                execpath = d.getVarFlag('DMO_LAUNCHER_EXEC', e)
                name = d.getVarFlag('DMO_LAUNCHER_NAME', e)
                desc = d.getVarFlag('DMO_LAUNCHER_DESC', e) or ""
                iconpath = d.getVarFlag('DMO_LAUNCHER_ICONPATH', e) or ""
                writeappfile(targetdir, execpath, name, desc, iconpath)
    else:
        execpath = d.getVar('DMO_LAUNCHER_EXEC')
        name = d.getVar('DMO_LAUNCHER_NAME')
        desc = d.getVar('DMO_LAUNCHER_DESC') or ""
        iconpath = d.getVar('DMO_LAUNCHER_ICONPATH') or ""
        writeappfile(targetdir, execpath, name, desc, iconpath)

}


addtask do_dmo_launcher_app before do_package after do_install

