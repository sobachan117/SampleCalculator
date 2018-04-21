package com.example.samplecalculator;

import android.support.annotation.NonNull;

class CalculatorPresenter implements CalculatorContract.Presenter {

    @NonNull
    private CalculatorContract.View mView;

    @NonNull
    private CalculatorState mCalculatorState;

    CalculatorPresenter(@NonNull CalculatorContract.View view) {
        mView = view;
        mCalculatorState = new CalculatorState();
    }

    @Override
    public void start() {
        mView.show();
    }

    @Override
    public void onClickButton(@NonNull CalcButtonComponent calcButtonComponent) {
        mView.updateText(mCalculatorState.calc(calcButtonComponent));
    }
}
