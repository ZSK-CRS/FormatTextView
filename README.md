# FormatTextView

示意效果如下
![Image 效果图](https://github.com/ZSK-CRS/FormatTextView/blob/master/docs/images/demo.png)

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
  
		implementation 'com.github.ZSK-CRS:FormatTextView:v1.0.3'
    
	}
```
#### 方法列表

方法 | 描述
---- | ----
setContent(String content) | 设置整体字符串
setSpecifyText(String specifyText) | 设置需要强调的字符
setSpecifyTextSize(int size) | 设置需要强调字符的大小
setSpecifyTextColor(int color) | 设置需要强调的文字颜色
setSpecifyTextBackgroundColor(int color) | 设置需要强调的文字背景颜色
setSpecifyTextStyle(int style) | 设置强调部分的风格 0: 无效果  1：加粗  2: 斜体  3:加粗兼斜体
setSpecifyDeleteLine(boolean deleteLine) | 是否添加删除线
setSpecifyUnderLine(boolean underLine) | 是否添加下划线
setSpecifyScriptStyle(int style) | 0：上标  1:下标
setSpecifyImage(@DrawableRes int image) | 替换指定文字为图片 需要配合setSpecifyImageRect使用
setSpecifyImageRect(Size size) | 设置替换图片的尺寸 配合setSpecifyImage
setSpecifyURL(String url) | 指定文字设置链接
refresh() | 上述效果必须调用refresh方法生效


**使用方式**
```
方式一：xml
   <cn.zsk.textlibrary.FormatTextView
        android:layout_width="wrap_content"
        android:layout_height="100dp"
        android:gravity="center"
        android:text="指定特殊字符的大小字体"
        android:textColor="@color/colorPrimary"
        android:textSize="17dp"
        app:specifyColor="@color/colorAccent"
        app:specifySize="30"
        app:specifyStyle="bold_italic"
        app:specifyText="大小字体" />

```
```
方式二：code
           formatTextView.setContent(content)
                    .setSpecifyText("大小字体")
                    .setSpecifyTextColor(Color.RED)
                    .setSpecifyTextSize(30)
                    .setSpecifyTextStyle(Typeface.NORMAL)
                    .refresh();
```
