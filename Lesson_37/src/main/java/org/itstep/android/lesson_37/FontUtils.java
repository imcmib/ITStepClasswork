package org.itstep.android.lesson_37;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.StringDef;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/*
 * FontUtils.java
 *
 * Created by mib on 04.03.15, 18:25
 *
 * Copyright(c) 2015 Provectus IT Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of Provectus IT Company.
 *
 */
public class FontUtils {

    @StringDef({FONT_1, FONT_2})
    public @interface FontName {}

    public static final String FONT_1 = "font1.ttf";
    public static final String FONT_2 = "font2.ttf";

    private static final Map<String, WeakReference<Typeface>> mFonts = new HashMap<>();

    public static Typeface getTypeFace(final Context context, @FontName final String fontName) {
        if (mFonts.containsKey(fontName)) {
            final WeakReference<Typeface> typefaceWeakReference = mFonts.get(fontName);
            final Typeface typeface = typefaceWeakReference.get();
            if (typeface != null) {
                return typeface;
            }
        }

        final Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontName);
        final WeakReference<Typeface> typefaceWeakReference = new WeakReference<>(typeface);
        mFonts.put(fontName, typefaceWeakReference);
        
        return typeface;
    }
}