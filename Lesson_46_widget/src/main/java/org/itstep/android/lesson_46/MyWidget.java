package org.itstep.android.lesson_46;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyWidget extends AppWidgetProvider {

    final static String ACTION_CHANGE = BuildConfig.APPLICATION_ID + ".ACTION_CHANGE";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for (int i : appWidgetIds) {
            updateWidget(context, appWidgetManager, i);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);

        SharedPreferences.Editor editor = context.getSharedPreferences(
                ConfigActivity.WIDGET_PREF, Context.MODE_PRIVATE).edit();

        for (int widgetID : appWidgetIds) {
            editor.remove(ConfigActivity.WIDGET_TIME_FORMAT + widgetID);
            editor.remove(ConfigActivity.WIDGET_COUNT + widgetID);
        }

        editor.apply();
    }

    static void updateWidget(Context context, AppWidgetManager appWidgetManager, int widgetID) {
        SharedPreferences sp = context.getSharedPreferences(
                ConfigActivity.WIDGET_PREF, Context.MODE_PRIVATE);

        String timeFormat = sp.getString(ConfigActivity.WIDGET_TIME_FORMAT + widgetID, "HH:mm:ss");

        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
        String currentTime = sdf.format(new Date(System.currentTimeMillis()));

        int count = sp.getInt(ConfigActivity.WIDGET_COUNT + widgetID, 0);

        RemoteViews widgetView = new RemoteViews(context.getPackageName(), R.layout.widget);
        widgetView.setTextViewText(R.id.tvTime, currentTime);
        widgetView.setTextViewText(R.id.tvCount, String.valueOf(count));

        Intent configIntent = new Intent(context, ConfigActivity.class);
        configIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_CONFIGURE);
        configIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);

        PendingIntent pIntent = PendingIntent.getActivity(context, widgetID, configIntent, 0);
        widgetView.setOnClickPendingIntent(R.id.tvPressConfig, pIntent);

        Intent updateIntent = new Intent(context, MyWidget.class);
        updateIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, new int[] { widgetID });

        pIntent = PendingIntent.getBroadcast(context, widgetID, updateIntent, 0);
        widgetView.setOnClickPendingIntent(R.id.tvPressUpdate, pIntent);

        Intent countIntent = new Intent(context, MyWidget.class);
        countIntent.setAction(ACTION_CHANGE);
        countIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);

        pIntent = PendingIntent.getBroadcast(context, widgetID, countIntent, 0);
        widgetView.setOnClickPendingIntent(R.id.tvPressCount, pIntent);

        appWidgetManager.updateAppWidget(widgetID, widgetView);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction().equalsIgnoreCase(ACTION_CHANGE)) {
            int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

            Bundle extras = intent.getExtras();
            if (extras != null) {
                mAppWidgetId = extras.getInt(
                        AppWidgetManager.EXTRA_APPWIDGET_ID,
                        AppWidgetManager.INVALID_APPWIDGET_ID);

            }

            if (mAppWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
                SharedPreferences sp = context.getSharedPreferences(
                        ConfigActivity.WIDGET_PREF, Context.MODE_PRIVATE);
                int cnt = sp.getInt(ConfigActivity.WIDGET_COUNT + mAppWidgetId,  0);
                sp.edit()
                  .putInt(ConfigActivity.WIDGET_COUNT + mAppWidgetId, ++cnt)
                  .apply();

                updateWidget(context, AppWidgetManager.getInstance(context), mAppWidgetId);
            }
        }
    }


}