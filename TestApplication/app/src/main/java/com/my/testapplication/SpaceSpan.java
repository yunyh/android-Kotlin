package com.my.testapplication;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;

public class SpaceSpan extends ReplacementSpan {
    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        float padding = paint.measureText("\u2002", 0, 1);
        float textSize = paint.measureText(text, start, end);
        return (int) (textSize + padding);
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        canvas.drawText(text.subSequence(start, end) + "\u2002", x, y, paint);
    }

    @Override
    public void updateMeasureState(TextPaint p) {
        super.updateMeasureState(p);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
    }

    private static final class CreditCardMaskCharSequence implements CharSequence {

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
            switch (index - 1) {
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
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
