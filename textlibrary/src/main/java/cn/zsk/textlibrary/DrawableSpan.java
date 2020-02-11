package cn.zsk.textlibrary;

import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;

/**
 * Author : ZSK
 * Date : 2020/2/11
 * Description :  用图片替换文字
 */
public class DrawableSpan extends DynamicDrawableSpan {

    private Drawable image;

    public DrawableSpan(Drawable drawable) {
        this.image = drawable;
    }

    @Override
    public Drawable getDrawable() {
        return image;
    }
}
