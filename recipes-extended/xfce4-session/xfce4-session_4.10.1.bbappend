
pkg_postinst_${PN}() {
#!/bin/bash

for i in /etc/default/xfce4 /etc/init.d/startxfce.sh /etc/rc5.d/S99startxfce4.sh
do
    [ -e $i ] && rm $i
done
}

