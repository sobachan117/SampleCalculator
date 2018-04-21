package com.example.samplecalculator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class CalculatorFragment extends Fragment implements CalculatorContract.View {

    private final CalculatorContract.Presenter mPresenter;

    private View mRootView;

    public CalculatorFragment() {
        mPresenter = new CalculatorPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_calculator, container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mPresenter.start();
    }

    @Override
    public void show() {
        setupButtons(mRootView);
    }

    @Override
    public void updateText(@NonNull CalculatorState calculatorState) {

        View mainTextView = mRootView.findViewById(R.id.output_main_text);
        if (mainTextView == null || !(mainTextView instanceof TextView)) {
            return;
        }
        ((TextView) mainTextView).setText(calculatorState.getCalcResult());

        View subTextView = mRootView.findViewById(R.id.output_sub_text);
        if (subTextView == null || !(subTextView instanceof TextView)) {
            return;
        }
        ((TextView) subTextView).setText(calculatorState.getCalcHistory());
    }

    private void setupButtons(@NonNull View view) {

        List<CalcComponent> column;

        // Column 1
        column = Arrays.asList(
                new CalcComponent(CalcType.Number, "7", R.color.bg_calc_number_button),
                new CalcComponent(CalcType.Number, "4", R.color.bg_calc_number_button),
                new CalcComponent(CalcType.Number, "1", R.color.bg_calc_number_button),
                new CalcComponent(CalcType.AllCancel, "AC", R.color.bg_calc_all_cancel_button));
        setupButtonVisDesign((ViewGroup) view.findViewById(R.id.column1_area), column);

        // Column 2
        column = Arrays.asList(
                new CalcComponent(CalcType.Number, "8", R.color.bg_calc_number_button),
                new CalcComponent(CalcType.Number, "5", R.color.bg_calc_number_button),
                new CalcComponent(CalcType.Number, "2", R.color.bg_calc_number_button),
                new CalcComponent(CalcType.Number, "0", R.color.bg_calc_number_button));
        setupButtonVisDesign((ViewGroup) view.findViewById(R.id.column2_area), column);

        // Column 3
        column = Arrays.asList(
                new CalcComponent(CalcType.Number, "9", R.color.bg_calc_number_button),
                new CalcComponent(CalcType.Number, "6", R.color.bg_calc_number_button),
                new CalcComponent(CalcType.Number, "3", R.color.bg_calc_number_button),
                new CalcComponent(CalcType.Decimal, ".", R.color.bg_calc_number_button));
        setupButtonVisDesign((ViewGroup) view.findViewById(R.id.column3_area), column);

        // Column 4
        column = Arrays.asList(
                new CalcComponent(CalcType.Divide, "÷", R.color.bg_calc_operator_button),
                new CalcComponent(CalcType.Multiply, "×", R.color.bg_calc_operator_button),
                new CalcComponent(CalcType.Minus, "−", R.color.bg_calc_operator_button),
                new CalcComponent(CalcType.Plus, "+", R.color.bg_calc_operator_button),
                new CalcComponent(CalcType.Calculate, "=", R.color.bg_calc_operator_button));
        setupButtonVisDesign((ViewGroup) view.findViewById(R.id.column4_area), column);
    }

    private void setupButtonVisDesign(@NonNull ViewGroup columnView,
                                      @NonNull List<CalcComponent> components) {

        for (final CalcComponent component : components) {
            CalculatorButton button = new CalculatorButton(getContext());
            button.setText(component.getDisplayName());
            button.setBackgroundColor(getResources().getColor(component.getBackgroundColorResId()));
            columnView.addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPresenter.onClickButton(component);
                }
            });
        }
    }
}
