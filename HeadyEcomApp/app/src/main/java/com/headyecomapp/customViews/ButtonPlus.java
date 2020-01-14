package com.headyecomapp.customViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

import com.headyecomapp.utils.FontCache;

public class ButtonPlus extends AppCompatButton {

    public ButtonPlus(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public ButtonPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public ButtonPlus(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("Nunito-Bold.ttf", context);
        setTypeface(customFont);
    }
}
