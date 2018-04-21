package com.example.samplecalculator;

import android.support.annotation.NonNull;

class CalcButtonComponent {

    private CalcButtonType mButtonType;

    private final String mDisplayName;

    private final int mBackgroundColorResId;

    CalcButtonComponent(@NonNull CalcButtonType calcButtonType,
                        @NonNull String displayName,
                        int backgroundColorResId) {
        mButtonType = calcButtonType;
        mDisplayName = displayName;
        mBackgroundColorResId = backgroundColorResId;
    }

    CalcButtonType getButtonType() {
        return mButtonType;
    }

    String getDisplayName() {
        return mDisplayName;
    }

    int getBackgroundColorResId() {
        return mBackgroundColorResId;
    }
}
