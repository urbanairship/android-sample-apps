/* Copyright Airship and Contributors */
package com.airship.sample

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.NotificationManagerCompat.IMPORTANCE_HIGH
import androidx.core.content.edit
import androidx.core.net.toUri
import com.airship.sample.glance.SampleAppWidgetLiveUpdate
import com.urbanairship.Airship
import com.urbanairship.AirshipConfigOptions
import com.urbanairship.Autopilot
import com.urbanairship.liveupdate.liveUpdateManager
import com.urbanairship.messagecenter.messageCenter

/** Autopilot that enables user notifications on first run. */
class SampleAutopilot : Autopilot() {

    override fun onAirshipReady(context: Context) {

        val preferences = context.getSharedPreferences(NO_BACKUP_PREFERENCES, MODE_PRIVATE)

        // Enable user notifications on first run
        val isFirstRun = preferences.getBoolean(FIRST_RUN_KEY, true)
        if (isFirstRun) {
            preferences.edit { putBoolean(FIRST_RUN_KEY, false) }

            Airship.push.enableUserNotifications()
        }

        // Build notification channels for Live Updates.
        val sportsChannel = NotificationChannelCompat.Builder("sports", IMPORTANCE_HIGH)
            .setDescription("Live sports updates!")
            .setName("Sports!")
            .setVibrationEnabled(false)
            .build()

        val deliveryChannel = NotificationChannelCompat.Builder("delivery", IMPORTANCE_HIGH)
            .setDescription("Delivery updates!")
            .setName("Delivery")
            .setVibrationEnabled(false)
            .build()

        // Create notification channels for Live Updates.
        with(NotificationManagerCompat.from(context)) {
            createNotificationChannel(sportsChannel)
            createNotificationChannel(deliveryChannel)
        }

        // Register handlers for Live Updates.
        with(Airship.liveUpdateManager) {
            register("sports", SampleLiveUpdate())
            register("sports-async", SampleAsyncLiveUpdate())
            register("medals-widget", SampleAppWidgetLiveUpdate())
            register("delivery", SampleDeliveryLiveUpdate())
        }

        Airship.messageCenter.setOnShowMessageCenterListener { messageId: String? ->
            val uri = if (messageId != null) {
                "vnd.urbanairship.sample://deepLink/inbox/message/$messageId"
            } else {
                "vnd.urbanairship.sample://deepLink/inbox"
            }.toUri()

            context.startActivity(
                Intent(Intent.ACTION_VIEW, uri)
                    .setPackage(context.packageName)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )

            true
        }

        // Register AirshipListener
        val airshipListener = AirshipListener()
        with(Airship.push) {
            addPushListener(airshipListener)
            addPushTokenListener(airshipListener)
            notificationListener = airshipListener
        }

        Airship.channel.addChannelListener(airshipListener)

        // Register the "squareview" InApp Message Content Extender
        SampleInAppMessageContentExtender.register()
    }

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
        private const val NO_BACKUP_PREFERENCES = "com.airship.sample.no_backup"
        private const val FIRST_RUN_KEY = "first_run"
    }
}
