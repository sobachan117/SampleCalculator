package com.example.samplecalculator;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class CalculatorButton extends AppCompatButton {

    public CalculatorButton(Context context) {
        super(context);
        init();
    }

    public CalculatorButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalculatorButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setTextSize(TypedValue.COMPLEX_UNIT_PX,
                getResources().getDimension(R.dimen.calc_button_text_size));
        setTextColor(getResources().getColor(android.R.color.white));
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        ViewGroup.LayoutParams lp = getLayoutParams();
        if (lp instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams lllp = (LinearLayout.LayoutParams) lp;
            final int margin = (int) getResources().getDimension(R.dimen.calc_button_margin);
            lllp.setMargins(margin, margin, margin, margin);
            lllp.weight = 1;
        }
    }
}
