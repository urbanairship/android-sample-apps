/* Copyright Airship and Contributors */
package com.airship.sample

import android.content.Context
import com.urbanairship.AirshipConfigOptions
import com.urbanairship.Autopilot
//import com.urbanairship.Airship

/**
 * Autopilot that enables user notifications on first run.
 */
class SampleAutopilot : Autopilot() {
//TODO: uncomment with airship v20
//    override fun onAirshipReady(airship: Airship) {
//        val context = Airship.applicationContext
//
//        val preferences = context.getSharedPreferences(NO_BACKUP_PREFERENCES, MODE_PRIVATE)
//
//        val isFirstRun = preferences.getBoolean(FIRST_RUN_KEY, true)
//        if (isFirstRun) {
//            preferences.edit().putBoolean(FIRST_RUN_KEY, false).apply()
//
//            // Enable user notifications on first run
//            airship.pushManager.userNotificationsEnabled = true
//        }
//
//        // Create notification channel for Live Updates.
//        val sportsChannel =
//            NotificationChannelCompat.Builder("sports", NotificationManagerCompat.IMPORTANCE_HIGH)
//                .setDescription("Live sports updates!")
//                .setName("Sports!")
//                .setVibrationEnabled(false)
//                .build()
//
//        NotificationManagerCompat.from(context).createNotificationChannel(sportsChannel)
//
//        // Register handlers for Live Updates.
//        with(LiveUpdateManager.shared()) {
//            register("sports", SampleLiveUpdate())
//            register("sports-async", SampleAsyncLiveUpdate())
//            register("medals-widget", SampleAppWidgetLiveUpdate())
//        }
//
//        MessageCenter.shared().setOnShowMessageCenterListener { messageId: String? ->
//            // Use an implicit navigation deep link for now as explicit deep links are broken
//            // with multi navigation host fragments
//            val uri = if (messageId != null) {
//               "vnd.urbanairship.sample://deepLink/inbox/message/$messageId"
//            } else {
//                "vnd.urbanairship.sample://deepLink/inbox"
//            }.toUri()
//
//            val intent = Intent(Intent.ACTION_VIEW, uri)
//                .setPackage(context.packageName)
//                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            Airship.applicationContext.startActivity(intent)
//            true
//        }
//
//        val airshipListener = AirshipListener()
//
//        with(airship.pushManager) {
//            addPushListener(airshipListener)
//            addPushTokenListener(airshipListener)
//            notificationListener = airshipListener
//        }
//
//        airship.channel.addChannelListener(airshipListener)
//    }

    override fun createAirshipConfigOptions(context: Context): AirshipConfigOptions? {
        /*
          Optionally, customize your config at runtime:

             AirshipConfigOptions options = new AirshipConfigOptions.Builder()
                    .setInProduction(!BuildConfig.DEBUG)
                    .setDevelopmentAppKey("Your Development App Key")
                    .setDevelopmentAppSecret("Your Development App Secret")
                    .setProductionAppKey("Your Production App Key")
                    .setProductionAppSecret("Your Production App Secret")
                    .setNotificationAccentColor(ContextCompat.getColor(context, R.color.color_accent))
                    .setNotificationIcon(R.drawable.ic_airship)
                    .build();

            return options;
         */

        // defaults to loading config from airshipconfig.properties file
        return super.createAirshipConfigOptions(context)
    }

    companion object {
        private const val NO_BACKUP_PREFERENCES = "com.urbanairship.sample.no_backup"
        private const val FIRST_RUN_KEY = "first_run"
    }
}
