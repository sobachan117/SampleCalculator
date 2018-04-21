package com.example.samplecalculator;

import android.support.annotation.NonNull;

public interface CalculatorContract {

    interface View {

        void show();

        void updateText(@NonNull CalculatorState calculatorState);
    }

    interface Presenter {

        void start();

        void onClickButton(@NonNull CalcButtonComponent calcButtonComponent);
    }
}
