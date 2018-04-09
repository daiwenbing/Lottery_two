# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#-----------------混淆配置设定------------------------------------------------------------------------
-optimizationpasses 5                                                       #指定代码压缩级别
-dontusemixedcaseclassnames                                                 #混淆时不会产生形形色色的类名
-dontskipnonpubliclibraryclasses                                            #指定不忽略非公共类库
-dontpreverify                                                              #不预校验，如果需要预校验，是-dontoptimize
-ignorewarnings                                                             #屏蔽警告
-verbose                                                                    #混淆时记录日志
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*    #优化

#-----------------导入第三方包,但是在当前版本中使用会报 input jar file is specified twice 错误，所以注释掉
#-libraryjars libs/android.support.v4.jar
#-libraryjars libs/BaiduLBS_Android.jar
#-libraryjars libs/commons-httpclient-3.1.jar
#-libraryjars libs/jackson-annotations-2.1.4.jar
#-libraryjars libs/jackson-core-2.1.4.jar
#-libraryjars libs/jackson-databind-2.1.4.jar
#-libraryjars libs/xUtils-2.6.14.jar

#-----------------不需要混淆第三方类库------------------------------------------------------------------
-dontwarn android.support.v4.**                                             #去掉警告
-keep class android.support.v4.** { *; }                                    #过滤android.support.v4
-keep interface android.support.v4.app.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment

-keep class org.apache.**{*;}                                               #过滤commons-httpclient-3.1.jar

-keep class com.fasterxml.jackson.**{*;}                                    #过滤jackson-core-2.1.4.jar等

-dontwarn com.lidroid.xutils.**                                             #去掉警告
-keep class com.lidroid.xutils.**{*;}                                       #过滤xUtils-2.6.14.jar
-keep class * extends java.lang.annotation.Annotation{*;}                   #这是xUtils文档中提到的过滤掉注解

#-dontwarn com.baidu.**                                                      #去掉警告
#-dontwarn com.baidu.mapapi.**
#-keep class com.baidu.** {*;}                                               #过滤BaiduLBS_Android.jar
#-keep class vi.com.gdi.bgl.android.**{*;}
#-keep class com.baidu.platform.**{*;}
#-keep class com.baidu.location.**{*;}
#-keep class com.baidu.vi.**{*;}

  #-----------------------如果引用了v4或者v7包------------------------------------
  -dontwarn android.support.**
  #------------------------JavaBean文件不混淆-----------------------------
  -keep class com.dwb.renrendaipai.model.** { *; }
  #-------------------------eventbus混淆报错------------------
  -keep class de.greenrobot.event.** {*;}
  -keepclassmembers class ** {
      public void onEvent*(**);
      void onEvent*(**);
  }
#-----------------不需要混淆系统组件等-------------------------------------------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

-keep class com.classtc.test.entity.**{*;}                                   #过滤掉自己编写的实体类

    ## ----------------------------------
    ##      OkHttp相关
    ## ----------------------------------
    -keepattributes Signature
    -keepattributes *Annotation*
    -keep class com.squareup.okhttp3.** { *; }
    -keep interface com.squareup.okhttp3.** { *; }
    -dontwarn com.squareup.okhttp3.**

    ## ----------------------------------
    ##      Okio相关
    ## ----------------------------------
    -keep class sun.misc.Unsafe { *; }
    -dontwarn java.nio.file.*
    -dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
    -dontwarn okio.**
    ## ----------------------------------
    ##      Glide相关
    ## ----------------------------------
    -keep class com.bumptech.glide.Glide { *; }
    -keep public class * implements com.bumptech.glide.module.GlideModule
    -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
      **[] $VALUES;
      public *;
    }
    -dontwarn com.bumptech.glide.**
    #-----------------butterknife混淆----------------
    -keep class butterknife.** { *; }
    -dontwarn butterknife.internal.**
    -keep class **$$ViewBinder { *; }

    -keepclasseswithmembernames class * {
        @butterknife.* <fields>;
    }

    -keepclasseswithmembernames class * {
        @butterknife.* <methods>;
    }