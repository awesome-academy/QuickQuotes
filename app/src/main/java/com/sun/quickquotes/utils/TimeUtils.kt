package com.sun.quickquotes.utils

import com.sun.quickquotes.R
import com.sun.quickquotes.ui.getstarted.GetStartedViewModel.Companion.DEFAULT_TIME_PREFIX
import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {

    private const val TIME_HOUR_FORMAT = "HH:mm"
    private const val MINIMUM_INTERVAL_TIME = 60000L
    private const val HALF_HOUR = "30"
    const val ONE_DAY = 86400000

    private fun getNumberWith2Letter(value: Int) = if (value < 10) "0$value" else value

    fun getIntervalTime(start: String, end: String, times: String): Long {
        val simpleDateFormat = SimpleDateFormat(TIME_HOUR_FORMAT, Locale.ENGLISH)
        val startTime = simpleDateFormat.parse(start)?.time
        val endTime = simpleDateFormat.parse(end)?.time
        val timesNumber = times.removePrefix(DEFAULT_TIME_PREFIX).toIntOrNull()

        if (startTime != null && endTime != null && timesNumber != null) {
            val delayTime = (endTime - startTime) / timesNumber
            if (delayTime < MINIMUM_INTERVAL_TIME) return MINIMUM_INTERVAL_TIME
            return delayTime
        } else {
            throw Exception()
        }
    }

    fun getTimeDuration(value: String): Long {
        val hour = value.split(":").first().toInt()
        val minute = value.split(":").last().toInt()
        val currentDate = Calendar.getInstance()
        val dueDate = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
        }

        return dueDate.timeInMillis - currentDate.timeInMillis
    }

    fun increaseTime(value: String): String = try {
        var hour = value.split(":").first().toInt()
        var minute = value.split(":").last().toInt()
        if (minute == 0) {
            "${getNumberWith2Letter(hour)}:$HALF_HOUR"
        } else {
            minute = 0
            if (hour == 23) hour = 0 else ++hour
            "${getNumberWith2Letter(hour)}:${getNumberWith2Letter(minute)}"
        }
    } catch (e: NumberFormatException) {
        e.message.toString()
    }

    fun decreaseTime(value: String): String = try {
        var hour = value.split(":").first().toInt()
        var minute = value.split(":").last().toInt()
        if (minute == 0) {
            if (hour == 0) hour = 23 else --hour
            "${if (hour < 10) "0$hour" else hour}:$HALF_HOUR"
        } else {
            minute = 0
            "${getNumberWith2Letter(hour)}:${getNumberWith2Letter(minute)}"
        }
    } catch (e: NumberFormatException) {
        e.message.toString()
    }

    fun compareTime(start: String, end: String) =
        if (start >= end) {
            R.string.error_time_not_valid
        } else {
            R.string.text_set_reminder_successfully
        }
}
