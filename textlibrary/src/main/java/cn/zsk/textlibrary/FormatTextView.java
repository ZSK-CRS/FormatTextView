package cn.zsk.textlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;


import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * Author : ZSK
 * Date : 2020/2/10
 * Description :
 */
public class FormatTextView extends AppCompatTextView {

    private String mContent;

    /**
     * 改变文字大小
     */
    private AbsoluteSizeSpan mSizeSpan;

    /**
     * 改变字体前景色
     */
    private ForegroundColorSpan mForegroundColorSpan;

    /**
     * 改变字体样式
     */
    private StyleSpan mStyleSpan;

    /**
     * 上标
     */
    private SuperscriptSpan mSuperScriptSpan;

    /**
     * 下标
     */
    private SubscriptSpan mSubscriptSpan;

    /**
     * 改变字体背景颜色
     */
    private BackgroundColorSpan mBackgroundColorSpan;

    /**
     * 图片设置
     */
    private DynamicDrawableSpan mImageSpan;

    /**
     * 超链接
     */
    private URLSpan mUrlSpan;

    /**
     * 删除线
     */
    private StrikethroughSpan mStrikethroughSpan;

    /**
     * 下划线
     */
    private UnderlineSpan mUnderLineSpan;


    /**
     * 文字构造器
     */
    private SpannableStringBuilder mBuilder;


    public FormatTextView(Context context) {
        super(context);
    }

    public FormatTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormatTextView);
            int num = typedArray.getIndexCount();
            for (int i = 0; i < num; i++) {
                int attrResId = typedArray.getIndex(i);
                if (attrResId == R.styleable.FormatTextView_specifyText) {
                    specifyText = typedArray.getString(R.styleable.FormatTextView_specifyText);
                } else if (attrResId == R.styleable.FormatTextView_specifyColor) {
                    specifyTextColor = typedArray.getColor(R.styleable.FormatTextView_specifyColor, Color.BLUE);
                    mForegroundColorSpan = new ForegroundColorSpan(specifyTextColor);
                } else if (attrResId == R.styleable.FormatTextView_specifySize) {
                    specifyTextSize = typedArray.getInt(R.styleable.FormatTextView_specifySize, (int) getTextSize());
                    mSizeSpan = new AbsoluteSizeSpan(specifyTextSize, true);
                } else if (attrResId == R.styleable.FormatTextView_specifyStyle) {
                    specifytextStyle = typedArray.getInt(R.styleable.FormatTextView_specifyStyle, Typeface.NORMAL);
                    mStyleSpan = new StyleSpan(specifytextStyle);
                } else if (attrResId == R.styleable.FormatTextView_specifyTextBackgroundColor) {
                    specifyTextBackgroundColor = typedArray.getColor(R.styleable.FormatTextView_specifyTextBackgroundColor, Color.GREEN);
                    mBackgroundColorSpan = new BackgroundColorSpan(specifyTextBackgroundColor);
                } else if (attrResId == R.styleable.FormatTextView_specifyDeleteLine) {
                    specifyDeleteLine = typedArray.getBoolean(R.styleable.FormatTextView_specifyDeleteLine, false);
                    mStrikethroughSpan = new StrikethroughSpan();
                } else if (attrResId == R.styleable.FormatTextView_specifyUnderLine) {
                    specifyUnderLine = typedArray.getBoolean(R.styleable.FormatTextView_specifyUnderLine, false);
                    mUnderLineSpan = new UnderlineSpan();
                } else if (attrResId == R.styleable.FormatTextView_specifyImage) {
                    specifyImageWidth = typedArray.getInt(R.styleable.FormatTextView_specifyImageWidth, 100);
                    specifyImageHeight = typedArray.getInt(R.styleable.FormatTextView_specifyImageWidth, 100);
                    specifyImage = typedArray.getDrawable(R.styleable.FormatTextView_specifyImage);
                    specifyImage.setBounds(0, 0, specifyImageWidth, specifyImageHeight);
                    mImageSpan = new DrawableSpan(specifyImage);
                } else if (attrResId == R.styleable.FormatTextView_specifyURL) {
                    specifyURL = typedArray.getString(R.styleable.FormatTextView_specifyURL);
                    setMovementMethod(new LinkMovementMethod());
                    mUrlSpan = new URLSpan(specifyURL);
                } else if (attrResId == R.styleable.FormatTextView_specifyScript) {
                    int scriptType = typedArray.getInt(R.styleable.FormatTextView_specifyScript, 0);
                    if (scriptType == 0) {
                        specifySuperscript = true;
                        specifySubscript = false;
                    } else {
                        specifySuperscript = false;
                        specifySubscript = true;
                    }
                    mSubscriptSpan = new SubscriptSpan();
                    mSuperScriptSpan = new SuperscriptSpan();
                }
            }
            typedArray.recycle();
        }
    }

    public FormatTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mContent = getText().toString();
        mBuilder = new SpannableStringBuilder(mContent);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCanDraw && specifyText != null && mContent.contains(specifyText)) {
            mCanDraw = false;
            setSpecifyFormat();
        }
    }

    private void setSpecifyFormat() {
        if (specifyText.length() != 0) {
            int start = mContent.indexOf(specifyText);
            int end = start + specifyText.length();

            //字体大小
            mBuilder.setSpan(mSizeSpan, start, end, SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE);
            //字体风格
            mBuilder.setSpan(mStyleSpan, start, end, SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE);
            //背景色
            mBuilder.setSpan(mBackgroundColorSpan, start, end, SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE);
            //删除线
            if (specifyDeleteLine) {
                mBuilder.setSpan(mStrikethroughSpan, start, end, SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE);
            }
            //下划线
            if (specifyUnderLine) {
                mBuilder.setSpan(mUnderLineSpan, start, end, SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE);
            }
            //下标
            if (specifySubscript) {
                mBuilder.setSpan(mSubscriptSpan, start, end, SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE);
            }
            //上标
            if (specifySuperscript) {
                mBuilder.setSpan(mSuperScriptSpan, start, end, SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE);
            }
            //URL
            if (!"".equals(specifyURL)){
                mBuilder.setSpan(mUrlSpan, start, end, SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE);
            }
            //图片
            mBuilder.setSpan(mImageSpan, start, end, SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE);
            //字体颜色
            mBuilder.setSpan(mForegroundColorSpan, start, end, SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE);
            setText(mBuilder);
        }
    }

    /*************************************指定格式*************************************/

    private String specifyText;
    private int specifyTextSize;
    private int specifyTextColor;
    private int specifytextStyle;
    private int specifyTextBackgroundColor;
    private Drawable specifyImage;
    private int specifyImageWidth;
    private int specifyImageHeight;
    private String specifyURL="";
    private boolean specifyDeleteLine = false;
    private boolean specifyUnderLine = false;
    private boolean specifySubscript = false;
    private boolean specifySuperscript = false;
    private boolean mCanDraw = true;

    /**
     * 设置整体内容
     *
     * @param content
     * @return
     */
    public FormatTextView setContent(String content) {
        this.mContent = content;
        mBuilder = new SpannableStringBuilder(mContent);
        return this;
    }

    /**
     * 设置需要强调的字符串  需调用{@link #refresh()}生效
     *
     * @param specifyText
     * @return
     */
    public FormatTextView setSpecifyText(String specifyText) {
        this.specifyText = specifyText;
        return this;
    }

    /**
     * 设置需要强调的字符串的大小  需调用{@link #refresh()}生效
     *
     * @param size
     * @return
     */
    public FormatTextView setSpecifyTextSize(int size) {
        this.specifyTextSize = size;
        this.mSizeSpan = new AbsoluteSizeSpan(size, true);
        return this;
    }

    /**
     * 设置需要强调的文字背景颜色  需调用{@link #refresh()}生效
     *
     * @param color ContextCompat.getColor(this, R.color.xxx)或者 Color.xxx 或者 十六进制格式颜色
     * @return
     */
    public FormatTextView setSpecifyTextColor(@ColorInt int color) {
        this.specifyTextColor = color;
        this.mForegroundColorSpan = new ForegroundColorSpan(color);
        return this;
    }

    /**
     * 设置需要强调的文字的颜色  需调用{@link #refresh()}生效
     *
     * @param color ContextCompat.getColor(this, R.color.xxx)或者 Color.xxx 或者 十六进制格式颜色
     * @return
     */
    public FormatTextView setSpecifyTextBackgroundColor(@ColorInt int color) {
        this.specifyTextBackgroundColor = color;
        this.mBackgroundColorSpan = new BackgroundColorSpan(color);
        return this;
    }

    /**
     * 设置强调部分的风格 需调用{@link #refresh()}生效
     *
     * @param style 0: 无效果  1：加粗  2: 斜体  3:加粗兼斜体
     * @return
     */
    public FormatTextView setSpecifyTextStyle(int style) {
        if (Typeface.NORMAL <= style && Typeface.BOLD_ITALIC >= style) {
            this.specifytextStyle = style;
            mStyleSpan = new StyleSpan(specifytextStyle);
        }
        return this;
    }

    /**
     * 是否添加删除线 需调用{@link #refresh()}生效
     *
     * @param deleteLine
     * @return
     */
    public FormatTextView setSpecifyDeleteLine(boolean deleteLine) {
        this.specifyDeleteLine = deleteLine;
        mStrikethroughSpan = new StrikethroughSpan();
        return this;
    }

    /**
     * 是否添加下划线 需调用{@link #refresh()}生效
     *
     * @param underLine
     * @return
     */
    public FormatTextView setSpecifyUnderLine(boolean underLine) {
        this.specifyUnderLine = underLine;
        mUnderLineSpan = new UnderlineSpan();
        return this;
    }

    /**
     * 0：上标  1:下标 需调用{@link #refresh()}生效
     *
     * @param style
     * @return
     */
    public FormatTextView setSpecifyScriptStyle(int style) {
        if (style == 0) {
            specifySuperscript = true;
            specifySubscript = false;
        } else if (style == 1) {
            specifySuperscript = false;
            specifySubscript = true;
        }
        mSuperScriptSpan = new SuperscriptSpan();
        mSubscriptSpan = new SubscriptSpan();
        return this;
    }

    /**
     * 替换指定文字为图片 需要同时使用{@link #setSpecifyImageRect(Size)}设定图片尺寸否则不生效
     *
     * @param image
     * @return
     */
    public FormatTextView setSpecifyImage(@DrawableRes int image) {
        this.specifyImage = getResources().getDrawable(image);
        return this;
    }

    /**
     * 指定文字设置链接
     * @param url
     * @return
     */
    public FormatTextView setSpecifyURL(String url){
        this.specifyURL = url;
        mUrlSpan = new URLSpan(specifyURL);
        setMovementMethod(new LinkMovementMethod());
        return this;
    }

    /**
     * 设置替换图片的尺寸
     *
     * @param size
     * @return
     */
    public FormatTextView setSpecifyImageRect(Size size) {
        specifyImage.setBounds(0, 0, size.getWidth(), size.getHeight());
        mImageSpan = new DrawableSpan(specifyImage);
        return this;
    }

    /**
     * 上述效果
     */
    public void refresh() {
        mCanDraw = true;
        invalidate();
    }

}
