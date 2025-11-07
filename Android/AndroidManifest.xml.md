## 清单文件
- <details>
  <summary> 伪代码 </summary>

  ```kotlin
    <!-- 根节点：声明应用包名及命名空间 -->
    <manifest 
        xmlns:android="http://schemas.android.com/apk/res/android"
        package="com.example.app"  <!-- 应用唯一包名 -->
        android:versionCode="1"    <!-- 内部版本号（整数，用于更新判断） -->
        android:versionName="1.0"  <!-- 外部版本名（字符串，用户可见） -->
        android:installLocation="auto"  <!-- 安装位置：auto/internalOnly/preferExternal -->
        android:compileSdkVersion="34"  <!-- 编译 SDK 版本 -->
        android:targetSdkVersion="34">  <!-- 目标 SDK 版本（行为适配基准） -->
    
        <!-- 1. 权限声明：请求系统权限 -->
        <uses-permission android:name="android.permission.INTERNET" />  <!-- 网络权限 -->
        <uses-permission android:name="android.permission.CAMERA" />    <!-- 相机权限 -->
        <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" android:maxSdkVersion="28" />  <!-- 旧版存储权限（带版本限制） -->
    
        <!-- 2. 自定义权限：供其他应用调用本应用时声明 -->
        <permission 
            android:name="com.example.app.CUSTOM_PERMISSION" 
            android:protectionLevel="normal"  <!-- 权限等级：normal/dangerous/signature 等 -->
            android:label="自定义权限" 
            android:description="用于访问应用特定功能" />
    
        <!-- 3. 硬件/软件特性声明：声明应用依赖的设备能力 -->
        <uses-feature 
            android:name="android.hardware.camera" 
            android:required="true" />  <!-- 是否必须（true：无此特性则无法安装） -->
        <uses-feature android:name="android.hardware.bluetooth" android:required="false" />  <!-- 可选特性 -->
    
        <!-- 4. 屏幕适配声明：指定支持的屏幕尺寸/密度 -->
        <supports-screens 
            android:smallScreens="true"
            android:normalScreens="true"
            android:largeScreens="true"
            android:xlargeScreens="true"
            android:anyDensity="true" />  <!-- 是否支持任意屏幕密度 -->
    
        <!-- 5. 包可见性声明：指定可访问的其他应用包（Android 11+ 必需） -->
        <queries>
            <package android:name="com.example.otherapp" />  <!-- 允许访问指定应用 -->
            <intent>  <!-- 允许访问匹配 intent 的应用 -->
                <action android:name="android.intent.action.SEND" />
                <data android:mimeType="image/*" />
            </intent>
        </queries>
    
        <!-- 6. 应用核心配置：包含所有组件和全局属性 -->
        <application 
            android:name=".MyApplication"  <!-- 自定义 Application 类（可选） -->
            android:icon="@mipmap/ic_launcher"  <!-- 应用图标 -->
            android:label="@string/app_name"    <!-- 应用名称 -->
            android:theme="@style/AppTheme"     <!-- 全局主题 -->
            android:allowBackup="true"          <!-- 是否允许备份数据 -->
            android:fullBackupContent="@xml/backup_rules"  <!-- 备份规则文件 -->
            android:supportsRtl="true"          <!-- 是否支持 RTL（从右到左）布局 -->
            android:roundIcon="@mipmap/ic_launcher_round"  <!-- 圆形图标（Android O+） -->
            android:networkSecurityConfig="@xml/network_security_config"  <!-- 网络安全配置 -->
            android:debuggable="false"          <!-- 是否可调试（release 版需设为 false） -->
            android:killAfterRestore="false"    <!-- 恢复后是否杀死应用 -->
            android:persistent="false">         <!-- 是否持久运行（系统应用常用） -->
    
            <!-- 6.1 应用组件：Activity（界面组件） -->
            <activity 
                android:name=".MainActivity"  <!-- Activity 完整类名 -->
                android:label="@string/main_title"  <!-- 标题 -->
                android:theme="@style/MainTheme"    <!-- 单独主题 -->
                android:launchMode="standard"      <!-- 启动模式：standard/singleTop/singleTask/singleInstance -->
                android:screenOrientation="portrait"  <!-- 屏幕方向：portrait/landscape/user/unspecified -->
                android:exported="true">  <!-- 是否允许外部应用调用（含 intent-filter 时默认 true） -->
                
                <!-- 启动入口配置：声明为应用首页 -->
                <intent-filter>
                    <action android:name="android.intent.action.MAIN" />  <!-- 主入口标记 -->
                    <category android:name="android.intent.category.LAUNCHER" />  <!-- 桌面图标启动 -->
                </intent-filter>
                
                <!-- 其他意图过滤器：响应外部隐式 Intent -->
                <intent-filter>
                    <action android:name="com.example.app.ACTION_VIEW" />
                    <category android:name="android.intent.category.DEFAULT" />
                    <data android:scheme="http" android:host="example.com" />  <!-- 匹配 http://example.com 链接 -->
                </intent-filter>
            </activity>
    
            <!-- 6.2 应用组件：Service（后台服务） -->
            <service 
                android:name=".MyService" 
                android:exported="false"  <!-- 不允许外部调用 -->
                android:foregroundServiceType="location"  <!-- 前台服务类型（Android 10+） -->
                android:enabled="true"    <!-- 是否启用（默认 true） -->
                android:isolatedProcess="false" />  <!-- 是否在独立进程运行 -->
    
            <!-- 6.3 应用组件：BroadcastReceiver（广播接收器） -->
            <receiver 
                android:name=".MyReceiver" 
                android:exported="true"
                android:enabled="true">
                <intent-filter>
                    <action android:name="android.intent.action.BOOT_COMPLETED" />  <!-- 监听系统启动完成广播 -->
                    <action android:name="com.example.app.CUSTOM_ACTION" />  <!-- 自定义广播 -->
                </intent-filter>
            </receiver>
    
            <!-- 6.4 应用组件：ContentProvider（内容提供者，数据共享） -->
            <provider 
                android:name=".MyProvider"
                android:authorities="com.example.app.provider"  <!-- 唯一标识（需与代码中一致） -->
                android:exported="false"
                android:readPermission="com.example.app.READ_PERMISSION"  <!-- 读权限 -->
                android:writePermission="com.example.app.WRITE_PERMISSION" />  <!-- 写权限 -->
    
            <!-- 6.5 元数据：全局键值对（可被代码通过 PackageManager 获取） -->
            <meta-data 
                android:name="APP_KEY" 
                android:value="123456" />
            <meta-data 
                android:name="CONFIG_FILE" 
                android:resource="@xml/app_config" />  <!-- 引用 XML 资源 -->
    
            <!-- 6.6 应用内其他配置 -->
            <uses-library android:name="androidx.core" android:required="true" />  <!-- 依赖库声明 -->
            <activity-alias  <!--  Activity 别名（可用于快捷方式启动不同入口） -->
                android:name=".AliasActivity"
                android:targetActivity=".MainActivity"
                android:label="快捷入口">
                <intent-filter>
                    <action android:name="android.intent.action.MAIN" />
                    <category android:name="android.intent.category.LAUNCHER" />
                </intent-filter>
            </activity-alias>
    
        </application>
    
    </manifest>
  ```
</details>

## 参考
- [官方清单文件文档](https://developer.android.com/guide/topics/manifest/manifest-intro?hl=zh-cn)

