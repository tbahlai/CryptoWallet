package com.tbahlai.cryptowallet.uikit.utils

import java.time.Instant
import java.time.temporal.ChronoUnit
import kotlin.math.abs

private val now: Instant
    get() = Instant.now()

fun Instant.toTimeAgo(): String {
    val seconds = abs(now.until(this, ChronoUnit.SECONDS))
    val minutes = abs(now.until(this, ChronoUnit.MINUTES))
    val hours = abs(now.until(this, ChronoUnit.HOURS))
    val days = abs(now.until(this, ChronoUnit.DAYS))

    return when {
        seconds < 60 -> "$seconds sec ago"
        minutes < 60 -> "$minutes min ago"
        hours < 24 -> "$hours hours ago"
        else -> "$days days ago"
    }
}