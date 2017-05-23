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

# adding this in to preserve line numbers so that the stack traces
# can be remapped
-keepattributes Signature
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

#utopay
-keep public class cn.utopay.sdk.pay.YQPay{
	public protected *;
}
-keep class cn.utopay.sdk.interfaces.** { *; }

#gblw
#-keep public class cn.utopay.gblwsdk.utils.InvokeUtil{*;}
-keep public class cn.utopay.gblwsdk.utils.HttpConnect{*;}
-keep class cn.utopay.gblwsdk.pay.UniCallback{
    public protected *;
}
#-keep class cn.utopay.gblwsdk.httpserver.DeviceConfig{*;}
-keep public class cn.utopay.gblwsdk.pay.Unipay {
	public protected *;
}
-keep public class cn.utopay.gblwsdk.manager.InvokeFactory{*;}

#中至
-keep public class com.eplus.internet.** {*;}

#大麦
-keep class com.android.dimtale.**{*;}
-keep class com.miiadnroidframework.a.**{*;}
-keep class com.miiadnroidframework.mutils.**{*;}

#玉峰
-dontwarn com.mj.**
-keep class com.mj.** { *;}
-keep class com.mj.jar.pay.**{*;}

 #上岸
-keep class com.wc.v.** {*;}
-keep class com.wc.k.** {*;}
-keep class com.wc.ut.ph.** {*;}
-keep class com.wc.ss.** {*;}
-keep class com.wc.k.Pau {
  public protected *;
}

#微云
-dontwarn com.wyzf.**
-keep class com.wyzf.** { *;}

#
-keep class com.android.dimtale.mtools.e.a.**{*;}

#-keep class cn.utopay.gblwsdk.log.LogManager{
# public protected *;
#}