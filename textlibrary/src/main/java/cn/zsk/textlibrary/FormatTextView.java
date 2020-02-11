package cn.zsk.textlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;

import androidx.annotation.ColorInt;
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
                    mSizeSpan = new AbsoluteSizeSpan(specifyTextSize,true);
                } else if (attrResId == R.styleable.FormatTextView_specifyStyle) {
                    specifyextStyle = typedArray.getInt(R.styleable.FormatTextView_specifyStyle, Typeface.NORMAL);
                    mStyleSpan = new StyleSpan(specifyextStyle);
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
            mBuilder.setSpan(mForegroundColorSpan, start, end, SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE);
            mBuilder.setSpan(mSizeSpan, start, end, SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE);
            mBuilder.setSpan(mStyleSpan, start, end, SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE);
            setText(mBuilder);
        }
    }

    /*************************************指定格式*************************************/

    private String specifyText;
    private int specifyTextSize;
    private int specifyTextColor;
    private int specifyextStyle;

    private boolean mCanDraw = true;

    /**
     * 设置整体内容
     * @param content
     * @return
     */
    public FormatTextView setContent(String content){
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
     * 设置需要强调的文字的颜色  需调用{@link #refresh()}生效
     *
     * @param colror ContextCompat.getColor(this, R.color.xxx)或者 Color.xxx 或者 十六进制格式颜色
     * @return
     */
    public FormatTextView setSpecifyTextColor(@ColorInt int colror) {
        this.specifyTextColor = colror;
        this.mForegroundColorSpan = new ForegroundColorSpan(colror);
        return this;
    }

    /**
     * 设置强调部分的风格
     *
     * @param style 0: 无效果  1：加粗  2: 斜体  3:加粗兼斜体
     * @return
     */
    public FormatTextView setSpecifyTextStyle(int style) {
        if (Typeface.NORMAL <= style && Typeface.BOLD_ITALIC >= style) {
            this.specifyextStyle = style;
            mStyleSpan = new StyleSpan(specifyextStyle);
        }
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
