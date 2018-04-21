package com.example.samplecalculator;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class CalculatorState {

    @NonNull
    private final List<CalcButtonComponent> mCalcHistoryList;

    CalculatorState() {
        mCalcHistoryList = new ArrayList<>();
    }

    CalculatorState calc(@NonNull CalcButtonComponent calcButtonComponent) {
        mCalcHistoryList.add(calcButtonComponent);
        return this;
    }

    @NonNull
    String getCalcResult() {
        //TODO Need to implement calculation
        return mCalcHistoryList.get(mCalcHistoryList.size() - 1).getDisplayName();
    }

    @NonNull
    String getCalcHistory() {
        StringBuilder stringBuilder = new StringBuilder();
        for (CalcButtonComponent calcButtonComponent : mCalcHistoryList) {
            stringBuilder.append(calcButtonComponent.getDisplayName());
        }
        return stringBuilder.toString();
    }
}
