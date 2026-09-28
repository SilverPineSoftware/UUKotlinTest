# UUKotlinTest
Useful Utilities for Testing!

## Libraries

### com.silverpine.uu.test
A collection of helper methods for plain unit tests based off of JUnit6 and Mockito.

### com.silverpine.uu.test.instrumented
A collection of helper methods for Android instrumented tests that run on devices and emulators.

## Permission tests

The normal instrumented suite verifies that granting CAMERA succeeds regardless of its
initial state, and that granting it twice succeeds. The denied-to-granted test is
excluded from normal Gradle connected and managed-device runs using the runner's
`notClass` method filter because CI may pre-grant permissions. It will not appear
as an executed or skipped test in those reports. This avoids relying on ignored-test
result handling. The standalone script invokes instrumentation directly without
that Gradle filter.

Run the transition test separately on an already booted API 33+ test device:

```sh
./scripts/test_permission_transition.sh emulator-5554
```

Use the device's actual adb serial. The script requires `adb`, Python 3, and the
usual Gradle build environment. It builds and installs the test APK without granting
permissions, stops the test package, revokes CAMERA, and launches only the transition
test with `uuCameraInitiallyDenied=true`. The test asserts the denied precondition
before granting permission. Revocation happens before instrumentation because
revoking permissions from the running test package can terminate its process.

For CI, run this script as a separate step against a booted emulator; do not run it
concurrently with another suite on the same device. It exits unsuccessfully for a
failed or skipped test and saves output to
`library_instrumented/build/reports/permission-transition/instrumentation.log`.
