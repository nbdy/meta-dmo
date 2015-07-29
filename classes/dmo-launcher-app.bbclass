# Class to generate app files for the dmlauncher

HOMEPAGE="https://emb.data-modul.com"
FILES_${PN} += '/usr/share/dmlauncher/apps/'
DMO_LAUNCHER_DESC ?= ''
DMO_LAUNCHER_ICONPATH ?= ''

python do_dmo_launcher_app() { 
    workdir = d.getVar('WORKDIR', True)
    execpath = d.getVar('DMO_LAUNCHER_EXEC')
    name = d.getVar('DMO_LAUNCHER_NAME')
    desc = d.getVar('DMO_LAUNCHER_DESC')
    iconpath = d.getVar('DMO_LAUNCHER_ICONPATH')
    targetpath = '/usr/share/dmlauncher/apps/'
    imagedir = workdir + '/image'
    targetdir = imagedir + targetpath
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
    fp.write('Icon=' + iconpath + '\n')
    fp.close()
}

addtask do_dmo_launcher_app before do_package after do_install

