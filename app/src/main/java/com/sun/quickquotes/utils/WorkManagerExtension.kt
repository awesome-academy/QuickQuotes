package com.sun.quickquotes.utils

import androidx.work.*
import java.util.concurrent.TimeUnit

inline fun <reified T : ListenableWorker> WorkManager.enqueueOneTimeWorkRequest(
    intervalTime: Long,
    timeUnit: TimeUnit
) {
    val constrains = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresBatteryNotLow(true)
        .build()
    val request = OneTimeWorkRequestBuilder<T>()
        .setConstraints(constrains)
        .setInitialDelay(intervalTime, timeUnit)
        .build()
    enqueue(request)
}
