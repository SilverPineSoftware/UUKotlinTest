# UUKotlinTest

Testing utilities for Silverpine UU Android/Kotlin libraries: JVM unit tests, Robolectric-friendly helpers, and on-device instrumented test support.

## Maven coordinates

| Artifact | Coordinates |
|----------|-------------|
| Unit test helpers | `com.silverpine.uu:uu-test-ktx` |
| Instrumented test helpers | `com.silverpine.uu:uu-test-instrumented-ktx` |

Published to [Maven Central](https://central.sonatype.com/search?q=com.silverpine.uu) under the `com.silverpine.uu` group.

## What's included

### `com.silverpine.uu.test` (JVM / unit tests)

- **`UUAssert`** — unwrap nullable values, compare `LiveData` on the main thread, nullness helpers.
- **`UUParcelableBaseTest`** — round-trip `Parcelable` instances via an in-memory `MockParcel`.
- **`UURandom` / `uuRandomLetters`** — deterministic-friendly random strings and numeric helpers for test data.
- **`uuDispatchMainAndWait`** — run assertions on the main looper from JVM tests (Robolectric).
- **Mocks** — `MockContext`, `MockSharedPreferences`, `MockParcel` for lightweight Android API stubs.

Designed for **JUnit 5** and Mockito-based unit tests across UU libraries.

### `com.silverpine.uu.test.instrumented` (device / emulator)

- **`UUBaseTestActivity`** — minimal activity with on-screen status text and permission request helpers for manual or semi-automated tests.
- **`@UUIntegrationTest`** — mark tests that depend on live backends or external services (filter in CI vs. local runs).
- **`@UUInteractionRequired`** — mark tests that need human interaction with the test UI.
- **`UUTestPermissions`** — helpers for runtime permission flows in instrumented tests.

## Gradle dependency

```kotlin
// build.gradle.kts (consumer)
dependencies {
    testImplementation("com.silverpine.uu:uu-test-ktx:<version>")
    androidTestImplementation("com.silverpine.uu:uu-test-instrumented-ktx:<version>")
}
```

Requires the UU Kotlin build catalog (`uu_build`) and GitHub Packages credentials for `UUKotlinBuild` — same setup as other UU libraries.

## Requirements

- Android `minSdk` / `targetSdk` aligned with your UU `gradle.properties` (`uu_min_sdk`, `uu_target_sdk`)
- JUnit 5 (`useJUnitPlatform()` in `android.testOptions.unitTests`)
- For instrumented tests: Android Gradle Plugin managed devices or connected hardware

## Changes in this release

- Dual-module publish (`uu-test-ktx` and `uu-test-instrumented-ktx`) in a single Maven Central deployment.
- JUnit 5 migration for unit tests across UU libraries.
- Dokka-generated Javadoc JARs published alongside AARs.
- CI: unit tests, managed-device instrumented tests, and GitHub Release workflow integration.

---

For prior versions and development snapshots, see [GitHub Releases](https://github.com/SilverpineSoftware/UUKotlinTest/releases) and the `develop` branch.
