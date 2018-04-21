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
        mView.updateText(mCalculatorState);
    }

    @Override
    public void onClickButton(@NonNull CalcComponent calcComponent) {

        mView.updateText(mCalculatorState.calc(calcComponent));

        if (calcComponent.getType().equals(CalcType.Calculate)) {
            mCalculatorState.clear();
        }
    }
}
