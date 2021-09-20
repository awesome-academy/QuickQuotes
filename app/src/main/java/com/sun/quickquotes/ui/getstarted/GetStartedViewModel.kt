package com.sun.quickquotes.ui.getstarted

import android.content.SharedPreferences
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sun.quickquotes.R
import com.sun.quickquotes.base.BaseViewModel
import com.sun.quickquotes.base.Event
import com.sun.quickquotes.utils.SharedPreferencesExtension.get
import com.sun.quickquotes.utils.SharedPreferencesExtension.set
import com.sun.quickquotes.utils.SharedPrefsKey.KEY_END_TIME
import com.sun.quickquotes.utils.SharedPrefsKey.KEY_FIRST_TIME_OPEN
import com.sun.quickquotes.utils.SharedPrefsKey.KEY_START_TIME
import com.sun.quickquotes.utils.SharedPrefsKey.KEY_TIMES_NUMBER
import com.sun.quickquotes.utils.TimeUtils
import com.sun.quickquotes.utils.WorkerKey.KEY_TIME_INVALID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetStartedViewModel(
    private val sharedPreferences: SharedPreferences
) : BaseViewModel() {

    private val _isEnableBackButton = MutableLiveData<Int>().apply { value = View.INVISIBLE }
    val isEnableBackButton: LiveData<Int>
        get() = _isEnableBackButton
    private val _startTime = MutableLiveData<String>()
    val startTime: LiveData<String>
        get() = _startTime
    private val _endTime = MutableLiveData<String>()
    val endTime: LiveData<String>
        get() = _endTime
    private val _timesNumber = MutableLiveData<String>()
    val timesNumber: LiveData<String>
        get() = _timesNumber

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.Default + exceptionHandler) {
            _startTime.postValue(sharedPreferences[KEY_START_TIME, DEFAULT_START_TIME])
            _endTime.postValue(sharedPreferences[KEY_END_TIME, DEFAULT_END_TIME])
            _timesNumber.postValue(sharedPreferences[KEY_TIMES_NUMBER, DEFAULT_TIMES_NUMBER])
        }
    }

    private fun validateTime(start: String, end: String): Boolean {
        TimeUtils.compareTime(start, end).also {
            message.value = Event(it)
            return it != R.string.error_time_not_valid
        }
    }

    fun changeStartTime(key: Int) = _startTime.value?.let {
        when (key) {
            KEY_INCREASE -> _startTime.postValue(TimeUtils.increaseTime(it))
            KEY_DECREASE -> _startTime.postValue(TimeUtils.decreaseTime(it))
            else -> Unit
        }
    }

    fun changeEndTime(key: Int) = _endTime.value?.let {
        when (key) {
            KEY_INCREASE -> _endTime.postValue(TimeUtils.increaseTime(it))
            KEY_DECREASE -> _endTime.postValue(TimeUtils.decreaseTime(it))
            else -> Unit
        }
    }

    fun increaseTimes() {
        _timesNumber.value?.let {
            var times = it.removePrefix(DEFAULT_TIME_PREFIX).toInt()
            if (times >= MAX_TIMES_PER_DAY) {
                message.value = Event(R.string.error_max_times_per_day)
            } else {
                ++times
                _timesNumber.postValue("$DEFAULT_TIME_PREFIX$times")
            }
        }
    }

    fun decreaseTimes() {
        _timesNumber.value?.let {
            var times = it.removePrefix(DEFAULT_TIME_PREFIX).toInt()
            if (times < MIN_TIMES_PER_DAY) {
                message.value = Event(R.string.error_turned_off_reminder)
            } else {
                --times
                _timesNumber.postValue("$DEFAULT_TIME_PREFIX$times")
            }
        }
    }

    fun getIntervalTime(): Long {
        val startTime = _startTime.value
        val endTime = _endTime.value
        val times = _timesNumber.value

        if (startTime != null
            && endTime != null
            && times != null
            && validateTime(startTime, endTime)
        ) {
            sharedPreferences.apply {
                set(KEY_FIRST_TIME_OPEN, false)
                set(KEY_START_TIME, startTime)
                set(KEY_END_TIME, endTime)
                set(KEY_TIMES_NUMBER, times)
            }
            if (times == TURN_OFF_REMINDER) return KEY_TIME_INVALID

            var timeUntilStart = TimeUtils.getTimeDuration(startTime)
            val timeUntilStop = TimeUtils.getTimeDuration(endTime)
            var intervalTime = TimeUtils.getIntervalTime(startTime, endTime, times)
            if (timeUntilStart >= NO_TIME) {
                intervalTime = timeUntilStart
            } else {
                if (timeUntilStop < NO_TIME) {
                    timeUntilStart += TimeUtils.ONE_DAY
                    intervalTime = timeUntilStart
                }
            }
            return intervalTime
        }
        return KEY_TIME_INVALID
    }

    companion object {
        private const val DEFAULT_START_TIME = "07:00"
        private const val DEFAULT_END_TIME = "23:00"
        private const val DEFAULT_TIMES_NUMBER = "X5"
        private const val TURN_OFF_REMINDER = "0"
        private const val MAX_TIMES_PER_DAY = 10
        private const val MIN_TIMES_PER_DAY = 1
        const val NO_TIME = 0L
        const val DEFAULT_TIME_PREFIX = "X"
        const val KEY_INCREASE = 100
        const val KEY_DECREASE = 101
    }
}
