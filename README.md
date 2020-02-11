# FormatTextView

示意效果如下
![Image 效果图](https://github.com/ZSK-CRS/FormatTextView/blob/master/docs/images/demo1.png)

#### 使用步骤

**step 1 工程build文件中添加JitPack仓库**

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

**step 2 添加依赖**

```
  dependencies {
  
		implementation 'com.github.User:Repo:Tag
    
	}
```
**使用方式**
```
方式一：xml
   <cn.zsk.textlibrary.FormatTextView
        android:layout_width="wrap_content"
        android:layout_height="100dp"
        android:gravity="center"
        android:text="花谢花飞飞满天，红消香断有谁怜"
        android:textColor="@color/colorPrimary"
        android:textSize="17dp"
        app:specifyColor="@color/colorAccent"
        app:specifySize="30"
        app:specifyStyle="bold_italic"
        app:specifyText="飞满天" />

```
```
方式二：code
           formatTextView.setContent(content)
                    .setSpecifyText("知是谁")
                    .setSpecifyTextColor(Color.RED)
                    .setSpecifyTextSize(30)
                    .setSpecifyTextStyle(Typeface.ITALIC)
                    .refresh();
```
