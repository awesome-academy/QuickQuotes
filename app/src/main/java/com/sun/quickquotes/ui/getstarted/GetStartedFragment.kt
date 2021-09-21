package com.sun.quickquotes.ui.getstarted

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.work.WorkManager
import com.sun.quickquotes.R
import com.sun.quickquotes.base.BaseFragment
import com.sun.quickquotes.databinding.FragmentGetStartedBinding
import com.sun.quickquotes.ui.getstarted.GetStartedViewModel.Companion.KEY_DECREASE
import com.sun.quickquotes.ui.getstarted.GetStartedViewModel.Companion.KEY_INCREASE
import com.sun.quickquotes.ui.main.MainActivity
import com.sun.quickquotes.utils.WorkerKey.KEY_TIME_INVALID
import com.sun.quickquotes.utils.enqueueOneTimeWorkRequest
import com.sun.quickquotes.worker.ReminderWorker
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit


class GetStartedFragment : BaseFragment<FragmentGetStartedBinding>(), View.OnClickListener {

    override val layoutResource get() = R.layout.fragment_get_started
    override val viewModel: GetStartedViewModel by viewModel()
    private var workManager: WorkManager? = null

    override fun initViews() {

    }

    override fun initData() {
        context?.let {
            workManager = WorkManager.getInstance(it)
        }
        with(viewBinding) {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@GetStartedFragment.viewModel
        }
    }

    override fun initEvents() = with(viewBinding) {
        listOf(
            imageDecreaseStartTime,
            imageIncreaseStartTime,
            imageDecreaseEndTime,
            imageIncreaseEndTime,
            imageDecreaseTimesNumber,
            imageIncreaseTimesNumber,
            buttonGetStartedContinue,
            buttonBack
        ).forEach {
            it.setOnClickListener(this@GetStartedFragment)
        }
    }

    override fun onClick(v: View?): Unit = with(viewBinding) {
        when (v) {
            imageDecreaseStartTime -> changeStartTime(KEY_DECREASE)
            imageIncreaseStartTime -> changeStartTime(KEY_INCREASE)
            imageDecreaseEndTime -> changeEndTime(KEY_DECREASE)
            imageIncreaseEndTime -> changeEndTime(KEY_INCREASE)
            imageDecreaseTimesNumber -> decreaseTimes()
            imageIncreaseTimesNumber -> increaseTimes()
            buttonGetStartedContinue -> {
                setReminder()
                findNavController().popBackStack()
                (activity as? MainActivity)?.setUpBottomNavigation()
            }
            buttonBack -> findNavController().popBackStack()
        }
    }

    private fun changeStartTime(key: Int) = viewModel.changeStartTime(key)

    private fun changeEndTime(key: Int) = viewModel.changeEndTime(key)

    private fun decreaseTimes() = viewModel.decreaseTimes()

    private fun increaseTimes() = viewModel.increaseTimes()

    private fun setReminder() {
        val intervalTime = viewModel.getIntervalTime()
        if (intervalTime > KEY_TIME_INVALID) {
            workManager?.apply {
                cancelAllWork()
                enqueueOneTimeWorkRequest<ReminderWorker>(
                    intervalTime,
                    TimeUnit.MILLISECONDS
                )
            }
        } else {
            workManager?.cancelAllWork()
        }
    }
}
