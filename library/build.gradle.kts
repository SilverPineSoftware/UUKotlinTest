plugins {
    id("com.android.library")
    id("kotlin-parcelize")
    id("maven-publish")
    id("signing")
    alias(uuBuild.plugins.uu.library)
    alias(uuBuild.plugins.uu.android.test)
}

dependencies {
    implementation(uuBuild.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(uuBuild.mockito.core)
    implementation(uuBuild.mockito.inline)
    implementation(uuBuild.androidx.junit)
    implementation(uuBuild.androidx.espresso.core)

    testImplementation(platform(uuBuild.junit.bom))
    testImplementation(uuBuild.junit.jupiter)
    testImplementation(uuBuild.junit.jupiter.api)
    testImplementation(uuBuild.junit.jupiter.engine)
    testImplementation(uuBuild.junit.jupiter.params)
    testRuntimeOnly(uuBuild.junit.platform.launcher)
    testImplementation(uuBuild.mockito.junit.jupiter)
    testImplementation(uuBuild.mockito.core)
    testImplementation(uuBuild.mockito.inline)
    testImplementation(uuBuild.mockito.kotlin)

    androidTestImplementation(uuBuild.androidx.junit)
    androidTestImplementation(uuBuild.androidx.espresso.core)
    androidTestImplementation(uuBuild.kotlin.test)
}

android {
    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}
