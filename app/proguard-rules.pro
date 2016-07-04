# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in G:\studiosdk/tools/proguard/proguard-android.txt
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

##---------------Begin: proguard configuration common for all Android apps ----------
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
#-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
#-dump class_files.txt
#-printseeds seeds.txt
#-printusage unused.txt
#-printmapping mapping.txt
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#-allowaccessmodification
-keepattributes *Annotation*
#-renamesourcefileattribute SourceFile
#-keepattributes SourceFile,LineNumberTable
-repackageclasses com.baidu

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

-keep class * extends java.lang.annotation.Annotation { *; }
#-dontnote com.android.vending.licensing.ILicensingService

# Explicitly preserve all serialization members. The Serializable interface
# is only a marker interface, so it wouldn't save them.
#-keepclassmembers class * implements java.io.Serializable {
#    static final long serialVersionUID;
#    private static final java.io.ObjectStreamField[] serialPersistentFields;
#    private void writeObject(java.io.ObjectOutputStream);
#    private void readObject(java.io.ObjectInputStream);
#    java.lang.Object writeReplace();
#    java.lang.Object readResolve();
#}

# Preserve all native method names and the names of their classes.
-keepclasseswithmembers class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# Preserve static fields of inner classes of R classes that might be accessed
# through introspection.
-keepclassmembers class **.R$* {
  public static <fields>;
}

# Preserve the special static methods that are required in all enumeration classes.
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

#-keep public class * {
#    public protected *;
#}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
# Explicitly preserve all serialization members. The Serializable interface
# is only a marker interface, so it wouldn't save them.
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
##---------------End: proguard configuration common for all Android apps ----------


##---------------Begin: proguard configuration for android-support-v4  ----------
#-libraryjars libs/android-support-v4.jar
-dontwarn android.support.v4.**
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment
-keep class android.support.v4.view.**{ *;}
-keep class android.support.v4.content.**{ *;}
##---------------End: proguard configuration for android-support-v4  ----------


-keep class  com.gjjx.carvideo.db.** { *; }

##---------------Begin: proguard configuration for butterknife  ----------
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
##---------------End: proguard configuration for butterknife  ----------


-dontshrink
#-dontoptimize
-dontwarn android.webkit.WebView
-keepattributes Exceptions,InnerClasses,Signature
-keep public class javax.**
-keep public class android.webkit.**


-dontwarn com.fasterxml.jackson.databind.**
-dontwarn okio.*
-dontwarn retrofit2.*
-dontwarn rx.internal.util.unsafe.*
-assumenosideeffects class android.util.Log{ public static *** d(...); public static *** w(...); public static *** e(...); public static *** i(...); public static *** v(...); }
