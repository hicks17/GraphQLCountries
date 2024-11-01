// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false

}
buildscript{
    val hiltVersion = "2.51.1"
    val gsversion = "4.4.2"
    dependencies{
        classpath(libs.hilt.android.gradle.plugin)
        classpath ("com.google.gms:google-services:$gsversion")
    }
}