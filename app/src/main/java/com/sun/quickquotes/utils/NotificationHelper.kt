package com.sun.quickquotes.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.os.bundleOf
import androidx.navigation.NavDeepLinkBuilder
import com.sun.quickquotes.R
import com.sun.quickquotes.data.model.Quote
import com.sun.quickquotes.ui.main.MainActivity

object NotificationHelper {

    private const val CHANNEL_ID = "100"
    private const val CHANNEL_NAME = "quickquotes.reminder"
    private const val CHANNEL_DESCRIPTION = "reminder for quickquotes"
    private const val NOTIFICATION_ID = 200
    const val KEY_OPEN_HOME_FRAGMENT = "open_home_with_quote"

    fun createNotify(context: Context, quote: Quote) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(quote.author)
            .setContentText(quote.text)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setSilent(true)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(quote.text)
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(createIntent(quote, context))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance).apply {
                description = CHANNEL_DESCRIPTION
            }
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    private fun createIntent(quote: Quote, context: Context) =
        NavDeepLinkBuilder(context)
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.homeFragment)
            .setArguments(
                bundleOf(KEY_OPEN_HOME_FRAGMENT to quote)
            )
            .createPendingIntent()
}
