package com.my.testapplication;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.TransformationMethod;
import android.text.style.ImageSpan;
import android.text.style.MaskFilterSpan;
import android.util.AttributeSet;
import android.util.Log;

public class CreditCardEditText extends AppCompatEditText implements TextWatcher {

    private static final int[] SPAN_INDEX = new int[]{4, 8, 12, 16};
    private static final int[] MASK_INDEX = new int[]{5, 6, 7, 8, 9, 10, 11, 12};

    private OnCardNumberWatcher mWatcher;
    private TransformationMethod mTransfermationMethod;

    public CreditCardEditText(Context context) {
        super(context);
        init();
    }

    public CreditCardEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CreditCardEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //  setTransformationMethod(new CreditCardTransformation());
        //mTransfermationMethod = getTransformationMethod();
        //  setTransformationMethod(new CreditCardTransformation());
        addTextChangedListener(this);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
       /* if (!focused) {
            setTransformationMethod(new CreditCardTransformation());
        } else {
            setTransformationMethod(mTransfermationMethod);
        }*/
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mWatcher != null) {
            mWatcher.onCardNumber(s.toString());
        }

    }

    public void setCardNumberWatcher(OnCardNumberWatcher watcher) {
        mWatcher = watcher;
    }

    @Override
    public void afterTextChanged(Editable s) {
        SpaceSpan[] paddingSpans = s.getSpans(0, s.length(), SpaceSpan.class);
        for (SpaceSpan spaceSpan : paddingSpans) {
            s.removeSpan(spaceSpan);
        }
    /*    ImageSpan[] maskingSpans = s.getSpans(0, s.length(), ImageSpan.class);
        for (ImageSpan maskingSpan : maskingSpans) {
            s.removeSpan(maskingSpan);
        }
*/
        addSpans(s);
    }

    private void addSpans(Editable s) {
        int length = s.length();
        ImageSpan imageSpan = new ImageSpan(getContext(), R.drawable.ic_fiber_manual_record_black_24dp);

        for (int index : MASK_INDEX) {
            if (index <= length) {
                s.setSpan(new MaskingSpan(), index - 1, index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
            }
        }

        for (int index : SPAN_INDEX) {
            if (index <= length) {
                s.setSpan(new SpaceSpan(), index - 1, index, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        }
        Log.d("###", s.toString());

    }

    public interface OnCardNumberWatcher {
        void onCardNumber(String number);
    }
}

