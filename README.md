# NCM Tethering Fix

Keeps Android's USB NCM interface in Ethernet server mode so macOS can use native CDC-NCM tethering.

Tested on PJZ110 running Android 16. The helper talks directly to `IEthernetManager`; transaction numbers may differ on other Android releases.

## Build

Requires Android SDK 36, JDK 17+, and `zip`.

```sh
just package
```

The installable ZIP is written to `build/ncm-tethering-fix-v1.0.0.zip`.

## What the module does

At boot it sets `tether_force_usb_functions=1`, then starts a small [AppProcess](https://github.com/Mufanc/AppProcess) helper that holds an Ethernet tethered-interface Binder request. The app links against compile-only framework stubs in `hiddenapi`; those classes are provided by Android at runtime. Uninstalling removes the setting and stops the helper.

The KernelSU Action button opens Android's tethering settings page.
