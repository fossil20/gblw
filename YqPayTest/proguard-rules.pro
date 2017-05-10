# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/hechangquan/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interfaces
# class:
#-keepclassmembers class fqcn.of.javascript.interfaces.for.webview {
#   public *;
#}

-dontshrink
-dontpreverify
-dontoptimize
-dontusemixedcaseclassnames

-flattenpackagehierarchy
-allowaccessmodification
-printmapping map.txt

-optimizationpasses 7
-verbose
-keepattributes Exceptions,InnerClasses
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-ignorewarnings

-dontwarn android.support.v4.**
-keep class android.support.v4.** { *; }
-keep interface android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends java.lang.Throwable {*;}
-keep public class * extends java.lang.Exception {*;}
-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}


#-libraryjars libs/gson-2.2.4.jar
-dontwarn com.google.gson.**
-keep class com.google.gson{*;}

#-libraryjars libs/nineoldandroids-2.4.0.jar
-dontwarn com.nineoldandroids.**
-keep class com.nineoldandroids{*;}

#-libraryjars libs/universal-image-loader-1.9.3.jar
-dontwarn com.nostra13.universalimageloader.**
-keep class com.nostra13.universalimageloader{*;}


#-libraryjars libs/alipaySDK-20150610.jar
-dontwarn com.alipay.**
-dontwarn com.ta.utdid2.**
-dontwarn com.ut.device.**
-keep class com.alipay.** { *; }
-keep class com.ta.utdid2.** { *; }
-keep class com.ut.device.** { *; }

#-libraryjars libs/libammsdk.jar
-dontwarn com.tencent.**
-keep class com.tencent.** { *; }

#-libraryjars libs/AMap_3DMap_V3.2.0.1.jar
-dontwarn com.amap.api.**
-dontwarn com.autonavi.amap.mapcore.**
-keep class assets.**{*;}
-keep class com.amap.api.**{*;}
-keep class com.autonavi.amap.mapcore.**{*;}

#-libraryjars libs/AMap_Location_v2.3.0.jar
-dontwarn com.amap.api.**
-dontwarn com.autonavi.aps.amapapi.model.**
-dontwarn com.loc.**
-keep class com.amap.api.** { *;}
-keep class com.autonavi.aps.amapapi.model.**{*;}
-keep class com.loc.** { *;}

#-libraryjars libs/AMap_Navi_v1.7.0.1.jar
-dontwarn com.amap.api.navi.**
-dontwarn com.autonavi.**
-keep class assets.**{*;}
-keep class com.amap.api.navi.**{*;}
-keep class com.autonavi.**{*;}

#-libraryjars libs/picasso-2.4.0.jar
-dontwarn com.squareup.picasso.**
-keep class com.squareup.picasso.**{*;}

#-libraryjars libs/AMap_Search_V2.8.0.jar
-dontwarn com.autonavi.**
-dontwarn com.amap.api.services.**
-keep class assets.**{*;}
-keep class com.autonavi.**{*;}
-keep class com.amap.api.services.**{*;}

#-libraryjars libs/Msc.jar
-dontwarn com.iflytek.**
-dontwarn com.chinaMobile.**
-keep class com.iflytek.**{*;}
-keep class com.chinaMobile.**{*;}

#-libraryjars libs/Sunflower.jar
-dontwarn com.iflytek.sunflower.**
-keep class com.iflytek.sunflower.**{*;}

#-libraryjars libs/UPPayAssistEx.jar
-dontwarn com.unionpay.**
-keep class com.unionpay.**{*;}

#-libraryjars libs/UPPayPluginExPro.jar
-dontwarn cn.gov.pbc.tsm.client.mobile.android.bank.service.**
-dontwarn com.UCMobile.PayPlugin.**
-dontwarn com.unionpay.**
-keep class com.unionpay.**{*;}
-keep class com.UCMobile.PayPlugin.**{*;}

#-libraryjars libs/Volley.jar
-dontwarn com.android.volley.**
-keep class com.android.volley.**{*;}

#-libraryjars libs/zxing.jar
-dontwarn com.google.zxing.**
-keep class com.google.zxing.**{*;}

#-libraryjars libs/mta-sdk-1.6.2.jar
-dontwarn com.tencent.stat.**
-keep class com.tencent.stat.**{*;}

#-libraryjars libs/open_sdk_r5509.jar
-keep class assets.**{*;}
-dontwarn com.tencent.**
-keep class com.tencent.**{*;}

#-libraryjars libs/weiboSDKCore_3.1.4.jar
-keep class assets.**{*;}
-dontwarn com.sina.**
-keep class com.sina.**{*;}

# removes such information by default, so configure it to keep all of it.
-keepattributes Signature
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }
# Application classes that will be serialized/deserialized over Gson
#-keep class com.google.gson.examples.android.model.** { *; }

-keep class com.fossil20.suso56.model.**{*;}

-keepclassmembers class ** {
    public void onEvent*(**);
}

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keep class vi.com.gdi.bgl.android.**{*;}

# adding this in to preserve line numbers so that the stack traces
# can be remapped
-keepattributes Signature
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable
