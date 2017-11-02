package com.aystub.muhbeers.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;


public class UiUtils {

    public static int dpToPx(Context context, int dp) {
        Resources r = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return (int) px;
    }
}
