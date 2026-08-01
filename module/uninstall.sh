#!/system/bin/sh

/system/bin/settings delete global tether_force_usb_functions
killall -q ncm-tethering
exit 0
