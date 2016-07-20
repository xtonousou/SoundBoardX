package com.xtonousou.soundboard;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.util.Calendar;

/**
 *  Checks day and returns color.
 *  Store color values in /res/values/colors.xml
 */
public class DayColor {
    private Context context;
    protected int accentColor;

    public DayColor(Context context) {
        this.context = context;
    }

    public int getDayColor() {
        return getAccentColor();
    }

    public int getAccentColor() {
        Calendar rightNow = Calendar.getInstance();
        switch (rightNow.get(Calendar.DAY_OF_WEEK)) {
            default:
                accentColor = ContextCompat.getColor(context, R.color.colorWhiteMinimalistic);
                return accentColor;
            case Calendar.MONDAY:
                accentColor = ContextCompat.getColor(context, R.color.colorMonday);
                return accentColor;
            case Calendar.TUESDAY:
                accentColor = ContextCompat.getColor(context, R.color.colorTuesday);
                return accentColor;
            case Calendar.WEDNESDAY:
                accentColor = ContextCompat.getColor(context, R.color.colorWednesday);
                return accentColor;
            case Calendar.THURSDAY:
                accentColor = ContextCompat.getColor(context, R.color.colorThursday);
                return accentColor;
            case Calendar.FRIDAY:
                accentColor = ContextCompat.getColor(context, R.color.colorFriday);
                return accentColor;
            case Calendar.SATURDAY:
                accentColor = ContextCompat.getColor(context, R.color.colorSaturday);
                return accentColor;
            case Calendar.SUNDAY:
                accentColor = ContextCompat.getColor(context, R.color.colorSunday);
                return accentColor;
        }
    }
}
