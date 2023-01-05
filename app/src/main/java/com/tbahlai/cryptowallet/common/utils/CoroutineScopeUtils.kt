package com.tbahlai.cryptowallet.common.utils

import kotlinx.coroutines.*

fun CoroutineScope.launchPeriodic(
    repeatMillis: Long,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return this.launch {
        if (repeatMillis > 0) {
            while (isActive) {
                block()
                delay(repeatMillis)
            }
        } else {
            block()
        }
    }
}
