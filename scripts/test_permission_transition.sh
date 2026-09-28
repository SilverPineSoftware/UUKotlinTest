#!/usr/bin/env bash
# Runs only the denied -> granted test on an explicitly selected, already booted test device.
set -euo pipefail

if [ "$#" -ne 1 ]; then
  echo "Usage: $0 <adb-device-serial>" >&2
  exit 2
fi
serial="$1"
cd "$(dirname "$0")/.."
adb_cmd=(adb -s "$serial")
"${adb_cmd[@]}" get-state
api_level=$("${adb_cmd[@]}" shell getprop ro.build.version.sdk | tr -d '\r')
if ! [[ "$api_level" =~ ^[0-9]+$ ]] || [ "$api_level" -lt 33 ]; then
  echo "This test requires Android API 33 or newer" >&2
  exit 1
fi

./gradlew :library_instrumented:assembleDebugAndroidTest
metadata=library_instrumented/build/outputs/apk/androidTest/debug/output-metadata.json
# Read the generated application ID and APK path rather than assuming the test suffix.
apk_info=()
while IFS= read -r line; do apk_info+=("$line"); done < <(python3 - "$metadata" <<'PY'
import json, pathlib, sys
path = pathlib.Path(sys.argv[1])
data = json.loads(path.read_text())
assert len(data['elements']) == 1, 'Expected one test APK'
print(data['applicationId'])
print(path.parent / data['elements'][0]['outputFile'])
PY
)
if [ "${#apk_info[@]}" -ne 2 ]; then
  echo "Could not resolve test APK metadata" >&2
  exit 1
fi
package="${apk_info[0]}"
"${adb_cmd[@]}" install -r -t "${apk_info[1]}"
# No instrumentation process may be running for this package while permission is revoked.
"${adb_cmd[@]}" shell am force-stop "$package"
"${adb_cmd[@]}" shell pm revoke "$package" android.permission.CAMERA
mkdir -p library_instrumented/build/reports/permission-transition
log=library_instrumented/build/reports/permission-transition/instrumentation.log
"${adb_cmd[@]}" shell am instrument -w -r \
  -e class 'com.silverpine.uu.test.instrumented.UUTestPermissionTests#testGrantPermissions_deniedToGranted' \
  -e uuCameraInitiallyDenied true \
  "$package/androidx.test.runner.AndroidJUnitRunner" 2>&1 | tee "$log"
# adb can exit successfully even when an instrumentation test fails or crashes.
if ! grep -Eq 'OK \(1 test\)' "$log" || grep -Eq 'FAILURES|INSTRUMENTATION_FAILED|Process crashed|INSTRUMENTATION_STATUS_CODE: -[1234]' "$log"; then
  echo "Permission transition test failed; see $log" >&2
  exit 1
fi
