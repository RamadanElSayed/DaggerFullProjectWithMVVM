package com.android.daggerfullprojectwithmvvm.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

public class KeyboardUtil {

    public static void dismissKeyboard(AppCompatActivity activity) {
        if(activity == null)
            return;
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != activity.getCurrentFocus()) {
            assert imm != null;
            imm.hideSoftInputFromWindow(activity.getCurrentFocus()
                    .getApplicationWindowToken(), 0);
        }
    }
}
