package com.example.samplecalculator;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class CalculatorState {

    @NonNull
    private final List<CalcComponent> mCalcHistoryList;

    CalculatorState() {
        mCalcHistoryList = new ArrayList<>();
    }

    CalculatorState calc(@NonNull CalcComponent calcComponent) {

        CalcType type = calcComponent.getType();
        CalcType lastType = getLastCalcComponent().getType();

        if (type.equals(CalcType.AllCancel)) {
            mCalcHistoryList.clear();
        } else if (type.isOperator() && lastType.isOperator()) {
            // replace component if the last type is operator and this type too.
            mCalcHistoryList.remove(mCalcHistoryList.size() - 1);
            mCalcHistoryList.add(calcComponent);
        } else {
            mCalcHistoryList.add(calcComponent);
        }
        return this;
    }

    @NonNull
    String getCalcResult() {
        int displayValue;
        CalcType lastType = getLastCalcComponent().getType();
        if (CalcType.Number.equals(lastType)) {
            displayValue = getNextInputValue();
        } else {
            displayValue = calculateAll();
        }
        return String.valueOf(displayValue);
    }

    @NonNull
    String getCalcHistory() {
        StringBuilder stringBuilder = new StringBuilder();
        for (CalcComponent calcComponent : mCalcHistoryList) {
            stringBuilder.append(calcComponent.getDisplayName());
        }
        return stringBuilder.toString();
    }

    void clear() {
        mCalcHistoryList.clear();
    }

    private CalcComponent getLastCalcComponent() {
        if (mCalcHistoryList.isEmpty()) {
            return new CalcComponent(CalcType.Number, "0", 0);
        } else {
            return mCalcHistoryList.get(mCalcHistoryList.size() - 1);
        }
    }

    private int getNextInputValue() {
        int value = 0;
        int digit = 0;
        for (int i = mCalcHistoryList.size() -1; 0 <= i; i--) {
            CalcComponent component = mCalcHistoryList.get(i);
            if (!(CalcType.Number.equals(component.getType()))) {
                break;
            }
            int nextValue = Integer.valueOf(component.getDisplayName());
            if (digit == 0) {
                value = nextValue;
            } else {
                value = value + nextValue * (int) Math.pow(10, digit);
            }
            digit++;
        }
        return value;
    }

    private int calculateAll() {
        int result = 0;
        int next = 0;
        CalcType operator = CalcType.AllCancel;
        for (CalcComponent component : mCalcHistoryList) {
            CalcType type = component.getType();
            switch (type) {
                case Number:
                    next = next * 10 + Integer.valueOf(component.getDisplayName());
                    break;
                case Decimal:
                case Plus:
                case Minus:
                case Divide:
                case Multiply:
                case Calculate:
                case AllCancel:
                    result = calcWithOperator(operator, result, next);
                    next = 0;
                    operator = type;
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    private int calcWithOperator(@NonNull CalcType buttonType,
                                 int prevInputValue, int nextInputValue) {
        switch (buttonType) {
            case Plus:
                prevInputValue += nextInputValue;
                break;
            case Minus:
                prevInputValue -= nextInputValue;
                break;
            case Multiply:
                prevInputValue *= nextInputValue;
                break;
            case Divide:
                if (nextInputValue != 0) {
                    prevInputValue /= nextInputValue;
                }
                break;
            default:
                prevInputValue = nextInputValue;
                break;
        }
        return prevInputValue;
    }
}
