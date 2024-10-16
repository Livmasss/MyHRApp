// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false

    alias(libs.plugins.serialization) apply false
    alias(libs.plugins.ksp) apply false
//    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
}