package org.deskmood.datetime

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.TemporalAdjusters

class DateTimePicker {

    fun now(): LocalDateTime {
        return ZonedDateTime.now(ZoneId.of(TIME_ZONE_SEOUL)).toLocalDateTime()
    }

    fun monthOfYearRange(
        year: Int,
        month: Int
    ): DateTimeRange {
        val targetDate = ZonedDateTime.of(year, month, 1, 0, 0, 0, 0, ZoneId.of(TIME_ZONE_SEOUL))
            .toLocalDateTime()

        return DateTimeRange(
            targetDate.withDayOfMonth(1),
            targetDate.withDayOfMonth(targetDate.toLocalDate().lengthOfMonth())
        )
    }

    fun thisMonthRange(): DateTimeRange {
        val now = this.now()
        return dateTimePicker.monthOfYearRange(now.year, now.monthValue)
    }

    fun thisWeekRange(): DateTimeRange {
        val now = this.now()
        val startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
        val endOfWeek = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
        return DateTimeRange(startOfWeek, endOfWeek)
    }

    companion object Util {

        private val dateTimePicker = DateTimePicker()

        fun now(): LocalDateTime {
            return dateTimePicker.now()
        }

        fun monthOfYearRange(
            year: Int,
            month: Int
        ): DateTimeRange {
            return dateTimePicker.monthOfYearRange(year, month)
        }

        fun thisMonthRange(): DateTimeRange {
            return dateTimePicker.thisMonthRange()
        }

        fun thisWeekRange(): DateTimeRange {
            return dateTimePicker.thisWeekRange()
        }
    }
}
