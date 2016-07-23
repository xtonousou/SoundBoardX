package com.xtonousou.soundboard;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.util.Calendar;

/**
 *  Checks day and returns color.
 *  Store color values in res/values/colors.xml
 */
class DayColor {
    private final Context context;

    public DayColor(Context context) {
        this.context = context;
    }

    public int getDayColor() {
        return getAccentColor();
    }

    private int getAccentColor() {
        Calendar rightNow = Calendar.getInstance();
        switch (rightNow.get(Calendar.DAY_OF_WEEK)) {
            default:
                return ContextCompat.getColor(context, R.color.colorPrimary);
            case Calendar.MONDAY:
                return ContextCompat.getColor(context, R.color.colorMonday);
            case Calendar.TUESDAY:
                return ContextCompat.getColor(context, R.color.colorTuesday);
            case Calendar.WEDNESDAY:
                return ContextCompat.getColor(context, R.color.colorWednesday);
            case Calendar.THURSDAY:
                return ContextCompat.getColor(context, R.color.colorThursday);
            case Calendar.FRIDAY:
                return ContextCompat.getColor(context, R.color.colorFriday);
            case Calendar.SATURDAY:
                return ContextCompat.getColor(context, R.color.colorSaturday);
            case Calendar.SUNDAY:
                return ContextCompat.getColor(context, R.color.colorSunday);
        }
    }
}
