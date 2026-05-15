// UUKotlinTest publishes two libraries (`library` → uu-test-ktx, `library_instrumented` → uu-test-instrumented-ktx),
// so per-module publish metadata (uu_namespace / uu_publish_artifact_id / uu_publish_description) is declared
// in each module's build.gradle.kts rather than at the root.
extra["uu_scm_module_name"] = "UUKotlinTest"

extra["uu_min_sdk"] = 26
extra["uu_target_sdk"] = 36
extra["uu_java_version"] = 17

plugins {
    alias(uuBuild.plugins.android.library) apply false
    alias(uuBuild.plugins.kotlin.android) apply false
    alias(uuBuild.plugins.nexus.publish)
    alias(uuBuild.plugins.uu.library) apply false
    alias(uuBuild.plugins.uu.android.test) apply false
    alias(uuBuild.plugins.uu.publish)
}
