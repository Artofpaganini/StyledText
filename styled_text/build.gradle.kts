plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    `maven-publish`
}

android {
    namespace = "io.github.artofpaganini.styled_text"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    buildTypes {
        debug { }
        release { }
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "io.github.artofpaganini.styled_text"
            version = "0.1"

            afterEvaluate {
                from(components["release"])
            }
            pom {
                name.set("StyledText")
                description.set("A convenient wrapper around AnnotatedString, allowing you to work it, outside of Jetpack Compose code")
                url.set("https://github.com/Artofpaganini/StyledText")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                    developers {
                        developer {
                            id.set("Artofpaganini")
                            name.set("Viktar Dabrou")
                            email.set("artofpaganini@gmail.com")
                        }
                    }
                    issueManagement {
                        system.set("GitHub")
                        url.set("https://github.com/Artofpaganini/StyledText/issues")
                    }
                }
            }
        }
        repositories {
            mavenLocal()
            maven {
                name = "BuildDir"
                url = uri(rootProject.layout.buildDirectory.dir("maven-repo"))
            }
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/artofpaganini/styled_text")
                credentials {
                    username = System.getenv("GITHUB_ACTOR")
                    password = System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.ui.text)
    implementation(libs.androidx.foundation)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}