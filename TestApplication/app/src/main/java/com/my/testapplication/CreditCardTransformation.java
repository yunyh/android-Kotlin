package com.my.testapplication;

import android.graphics.Rect;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.Transformation;

public class CreditCardTransformation implements TransformationMethod {

    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
       /* if (TextUtils.isEmpty(source)) {
            return source;
        }
        if(source instanceof Spannable){
        }
        return new CreditCardMaskCharSequence(source);*/
       return source;
    }

    @Override
    public void onFocusChanged(View view, CharSequence sourceText, boolean focused, int direction, Rect previouslyFocusedRect) {
        //Do Nothing.
        Log.d("###", sourceText.toString());
    }

    public static final class CreditCardMaskCharSequence implements CharSequence {

        private CharSequence mSource;

        public CreditCardMaskCharSequence(CharSequence source) {
            mSource = source;
        }

        @Override
        public int length() {
            return mSource.length();
        }

        @Override
        public char charAt(int index) {
            switch (index) {
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                    return '*';
                default:
                    return mSource.charAt(index);
            }
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            return mSource.subSequence(start, end);
        }
    }
}
