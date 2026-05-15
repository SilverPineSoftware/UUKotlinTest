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
}

android {
    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}
