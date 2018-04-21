package com.example.samplecalculator;

import android.support.annotation.NonNull;

class CalcComponent {

    private CalcType mButtonType;

    private final String mDisplayName;

    private final int mBackgroundColorResId;

    CalcComponent(@NonNull CalcType calcType,
                  @NonNull String displayName,
                  int backgroundColorResId) {
        mButtonType = calcType;
        mDisplayName = displayName;
        mBackgroundColorResId = backgroundColorResId;
    }

    CalcType getType() {
        return mButtonType;
    }

    String getDisplayName() {
        return mDisplayName;
    }

    int getBackgroundColorResId() {
        return mBackgroundColorResId;
    }
}
