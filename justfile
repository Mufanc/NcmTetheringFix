version := `sed -n 's/^version=//p' module/module.prop`
output := "build/ncm-tethering-fix-" + version + ".zip"

default: package

package:
    ./gradlew :app:assembleRelease
    mkdir -p build/module
    cp module/module.prop module/customize.sh module/boot-completed.sh module/uninstall.sh module/action.sh build/module/
    cp app/build/outputs/apk/release/app-release-unsigned.ash build/module/ncm-ethernet-fix.sh
    chmod 0755 build/module/customize.sh build/module/boot-completed.sh build/module/uninstall.sh build/module/action.sh build/module/ncm-ethernet-fix.sh
    rm -f {{output}}
    cd build/module && zip -X ../ncm-tethering-fix-{{version}}.zip module.prop customize.sh boot-completed.sh uninstall.sh action.sh ncm-ethernet-fix.sh
    shasum -a 256 {{output}}
