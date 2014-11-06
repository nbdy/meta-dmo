# Class to generate md5sum in given path for given file

HOMEPAGE="https://emb.data-modul.com"

dmo_do_checksum() {
    file_name=$2
    file_path=$1

    if [ "$file_path" = "" ]; then
        bberror "File path missing"
        exit 1
    fi

    if [ "$file_name" = "" ]; then
        bberror "File name missing"
        exit 1
    fi

    bbnote "Generate checksum for" $file_name "in" $file_path
    workingdir=$PWD

    cd $file_path
    if [ -e $file_name ]; then
        md5sum $file_name > $file_name".md5sum"
    else
        bberror "File" $file_name "does not exist!"
    fi

    cd $workingdir
}

