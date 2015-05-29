package org.itstep.android.lesson_44;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.util.Random;

public abstract class Gcm {
    public static final String TAG = "GCM";

    private static final String PREF_REGISTRATION_ID = "prefRegistrationId";
    private static final String PREF_REGISTERED_VERSION = "prefRegisteredVersion";

    private static final String PREF_USER_ID = "prefUserId";
    private static final String PREF_USER_REGISTRATION_ID = "prefUserRegistrationId";

    public static void subscribe(Context context) {
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) !=
                ConnectionResult.SUCCESS) {
            Log.w(TAG, "No Play services found. Push notification may be disabled.");
            return;
        }

        String registrationId = getRegistrationId(context);
        if (TextUtils.isEmpty(registrationId)) {
            subscribeInBackground(context);
        }
    }

    public static void register(Context context) {
        String registrationId = getRegistrationId(context);
        if (TextUtils.isEmpty(registrationId)) {
            Log.w(TAG, "Skip registration, registration id not found. Please subscribe first.");
            return;
        }

        final SharedPreferences prefs = getGcmPreferences(context);
        String userId = prefs.getString(PREF_USER_ID, "");
        String userRegistrationId = prefs.getString(PREF_USER_REGISTRATION_ID, "");

        if (TextUtils.isEmpty(userId) || !registrationId.equals(userRegistrationId)) {
            registerInBackground(context, registrationId);
        }
    }

    public static void unregister(Context context) {
        final SharedPreferences prefs = getGcmPreferences(context);
        String userId = prefs.getString(PREF_USER_ID, "");

        if (!TextUtils.isEmpty(userId)) {
            unregisterInBackground(context);
        }
    }

    /**
     * Gets the current registration ID for application on GCM service.
     * <p>
     * If result is empty, the app needs to register.
     *
     * @return registration ID, or empty string if there is no existing registration ID.
     */
    public static String getRegistrationId(Context context) {
        final SharedPreferences prefs = getGcmPreferences(context);
        String registrationId = prefs.getString(PREF_REGISTRATION_ID, "");

        if (registrationId.isEmpty()) {
            return "";
        }

        int registeredVersion = prefs.getInt(PREF_REGISTERED_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);

        if (registeredVersion != currentVersion) {
            return "";
        }

        return registrationId;
    }

    /**
     * Stores the registration ID and the app versionCode in the application's
     * {@code SharedPreferences}.
     *
     * @param context application's context.
     * @param regId registration ID
     */
    @SuppressLint("CommitPrefEdits")
    private static void storeRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = getGcmPreferences(context);
        int appVersion = getAppVersion(context);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREF_REGISTRATION_ID, regId);
        editor.putInt(PREF_REGISTERED_VERSION, appVersion);
        editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    private static void storeUserRegistration(Context context, String userId, String regId) {
        final SharedPreferences prefs = getGcmPreferences(context);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREF_USER_ID, userId);
        editor.putString(PREF_USER_REGISTRATION_ID, regId);
        editor.commit();
    }

    private static SharedPreferences getGcmPreferences(Context context) {
        return context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
    }

    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo =
                    context.getPackageManager().getPackageInfo(context.getPackageName(), 0);

            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return Integer.MIN_VALUE;
        }
    }

    private static void subscribeInBackground(final Context context) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
                    String senderId = BuildConfig.SENDER_ID;

                    try {
                        gcm.unregister();
                    } catch (Exception e) {
                        Log.w(TAG, "Cannot unregister from GCM.", e);
                    }

                    String registrationId = gcm.register(senderId);
                    if (!TextUtils.isEmpty(registrationId)) {
                        storeRegistrationId(context, registrationId);
                        Log.i(TAG, "Device registered, registration ID=" + registrationId);

                        register(context);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Gcm registration failed.", e);
                }

                return null;
            }
        }.execute(null, null, null);
    }

    private static void registerInBackground(final Context context, final String registrationId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    if (subscribeUser(registrationId)) {
                        String userId = "user"; // TODO [aivanchenko | 29.05.2015 16:40]
                        storeUserRegistration(context, userId, registrationId);

                        Log.i(TAG, "Registration completed with id: "  + registrationId
                                        + ", user=" + userId
                        );

                        return null;
                    }

                    Log.w(TAG, "Registration failed.");
                } catch (Exception e) {
                    Log.e(TAG, "Gcm registration failed.", e);
                }

                return null;
            }
        }.execute(null, null, null);
    }

    private static void unregisterInBackground(final Context context) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    if (unsubscribeUser()) {
                        storeUserRegistration(context, null, null);
                        Log.i(TAG, "Un-registration completed.");
                        return null;
                    }

                    Log.w(TAG, "Un-registration failed.");
                } catch (Exception e) {
                    Log.e(TAG, "Gcm un-registration failed.", e);
                }

                return null;
            }
        }.execute(null, null, null);
    }

    private static boolean subscribeUser(String regId) {
        // TODO [aivanchenko | 29.05.2015 16:39]
        return true;
    }

    private static boolean unsubscribeUser() {
        // TODO [aivanchenko | 29.05.2015 16:40]
        return true;
    }
}