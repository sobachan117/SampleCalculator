package com.example.samplecalculator;

public enum CalcType {

    Number,
    Decimal,
    Plus,
    Minus,
    Divide,
    Multiply,
    Calculate,
    AllCancel;

    boolean isOperator() {
        return !CalcType.Number.equals(this);
    }
}
