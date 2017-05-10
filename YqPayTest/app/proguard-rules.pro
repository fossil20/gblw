# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/hechangquan/Android_File/android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#-dontusemixedcaseclassnames
#-dontskipnonpubliclibraryclasses
#-verbose
#-keepattributes Signature
#-dontoptimize
#-dontpreverify
#-dontwarn android.support.**
#-dontwarn android.support.**
#-dontwarn com.tencent.**
#-dontwarn org.dom4j.**
#-dontwarn org.slf4j.**
#-dontwarn org.http.mutipart.**
#-dontwarn org.apache.**
#-dontwarn org.apache.log4j.**
#-dontwarn org.apache.commons.logging.**
#-dontwarn org.apache.commons.codec.binary.**
#-dontwarn weibo4android.**
#-optimizationpasses 5
#-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#-keep public class * extends android.app.Activity
#-keep public class * extends android.app.Application
#-keep public class * extends android.app.Service
#-keep public class * extends android.content.BroadcastReceiver
#-keep public class * extends android.content.ContentProvider
#-keep public class * extends android.app.backup.BackupAgentHelper
#-keep public class * extends android.preference.Preference
#-keepattributes *Annotation*
#-keep public class com.google.vending.licensing.ILicensingService
#-keep public class com.android.vending.licensing.ILicensingService
#-keep public class cn.utopay.sdk.pay.YQPay{
#	public protected *;
#}
#-keepclasseswithmembernames class * {
# native <methods>;
#}
#-keepclassmembers enum * {
#public static **[] values();
#public static ** valueOf(java.lang.String);
#}
#-keep class * implements android.os.Parcelable {
#public static final android.os.Parcelable$Creator *;
#}
#-keep class cn.utopay.sdk.interfaces.** { *; }
#
#-keep class cn.utopay.** {*;}
#
#-keep public class com.eplus.internet.** {*;}
