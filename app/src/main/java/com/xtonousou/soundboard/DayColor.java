package com.xtonousou.soundboard;

import android.content.Context;
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
                accentColor = context.getResources().getColor(R.color.colorWhiteMinimalistic);
                return accentColor;
            case Calendar.MONDAY:
                accentColor = context.getResources().getColor(R.color.colorMonday);
                return accentColor;
            case Calendar.TUESDAY:
                accentColor = context.getResources().getColor(R.color.colorTuesday);
                return accentColor;
            case Calendar.WEDNESDAY:
                accentColor = context.getResources().getColor(R.color.colorWednesday);
                return accentColor;
            case Calendar.THURSDAY:
                accentColor = context.getResources().getColor(R.color.colorThursday);
                return accentColor;
            case Calendar.FRIDAY:
                accentColor = context.getResources().getColor(R.color.colorFriday);
                return accentColor;
            case Calendar.SATURDAY:
                accentColor = context.getResources().getColor(R.color.colorSaturday);
                return accentColor;
            case Calendar.SUNDAY:
                accentColor = context.getResources().getColor(R.color.colorSunday);
                return accentColor;
        }
    }
}
