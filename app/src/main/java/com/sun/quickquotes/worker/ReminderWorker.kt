package com.sun.quickquotes.worker

import android.content.Context
import android.content.SharedPreferences
import androidx.work.CoroutineWorker
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.sun.quickquotes.data.repository.QuoteRepository
import com.sun.quickquotes.ui.getstarted.GetStartedViewModel.Companion.NO_TIME
import com.sun.quickquotes.utils.NotificationHelper
import com.sun.quickquotes.utils.SharedPreferencesExtension.get
import com.sun.quickquotes.utils.SharedPrefsKey.KEY_END_TIME
import com.sun.quickquotes.utils.SharedPrefsKey.KEY_START_TIME
import com.sun.quickquotes.utils.SharedPrefsKey.KEY_TIMES_NUMBER
import com.sun.quickquotes.utils.TimeUtils
import com.sun.quickquotes.utils.enqueueOneTimeWorkRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.concurrent.TimeUnit

class ReminderWorker(
    private val appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params), KoinComponent {

    private val quoteRepository: QuoteRepository by inject()
    private val sharedPreferences: SharedPreferences by inject()
    private val workManager = WorkManager.getInstance(appContext)

    override suspend fun doWork() = withContext(Dispatchers.IO) {
        try {
            getQuote()
            val startTime: String = sharedPreferences[KEY_START_TIME]
            val endTime: String = sharedPreferences[KEY_END_TIME]
            val times: String = sharedPreferences[KEY_TIMES_NUMBER]
            val timeUntilStop = TimeUtils.getTimeDuration(endTime)
            var timeUntilStart = TimeUtils.getTimeDuration(startTime)
            var intervalTime = TimeUtils.getIntervalTime(startTime, endTime, times)
            if (timeUntilStop < NO_TIME) {
                workManager.cancelAllWork()
                timeUntilStart += TimeUtils.ONE_DAY
                intervalTime = timeUntilStart

            }
            workManager.enqueueOneTimeWorkRequest<ReminderWorker>(
                intervalTime,
                TimeUnit.MILLISECONDS
            )
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

    private suspend fun getQuote() {
        val quote = quoteRepository.getRandomQuotes(DEFAULT_ONE_QUOTE)
        NotificationHelper.createNotify(appContext, quote.quotes.first())
    }

    companion object {
        private const val DEFAULT_ONE_QUOTE = "1"
    }
}
