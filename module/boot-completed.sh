#!/system/bin/sh
set -eu

/system/bin/settings put global tether_force_usb_functions 1

MODDIR=${0%/*}
"$MODDIR/ncm-ethernet-fix.sh" >/dev/null 2>&1 &
