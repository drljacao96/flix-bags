package com.drbrosdev.flix_bags.util

import android.view.HapticFeedbackConstants
import android.view.View

fun View.vibrate() {
    performHapticFeedback(HapticFeedbackConstants.LONG_PRESS, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING)
}