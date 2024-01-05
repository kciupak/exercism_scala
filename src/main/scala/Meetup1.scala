import java.time.{DayOfWeek, LocalDate}
import scala.collection.immutable.TreeMap

import Schedule.Schedule

case class Meetup1(month: Int, year: Int) {

  def day(dayOfWeek: Int, schedule: Schedule): LocalDate = {
    val dayMap: Map[Int, Int] = createDayMap(dayOfWeek)

    val monthDay: Int = countDay(dayMap, countSchedule(schedule))
    LocalDate.of(year, month, monthDay)
  }

  private def createDayMap(dayOfWeek: Int): Map[Int, Int] = {
    val monthDaysMap: Map[Int, Int] = {
      TreeMap(
        (1 to daysOfMonthCount(month)).map(key =>
          key -> ((key - 2 + firstMonthDay) % 7 + 1)
        ): _*
      )
    }
    monthDaysMap.filter { case (_, value) => value == dayOfWeek }
  }

  private def countSchedule(schedule: Schedule): Int = {
    schedule match {
      case Schedule.Teenth => 10
      case Schedule.First  => 1
      case Schedule.Second => 2
      case Schedule.Third  => 3
      case Schedule.Fourth => 4
      case Schedule.Last   => -1
    }
  }

  private def countDay(dayMap: Map[Int, Int], n: Int): Int = {
    n match {
      case 10 =>
        dayMap.keys.toList
          .find(List(13, 14, 15, 16, 17, 18, 19).contains(_))
          .getOrElse(-1)
      case -1 => dayMap.keys.toList.last
      case _  => dayMap.keys.toList(n - 1)
    }
  }

  private def firstMonthDay: Int = {
    val ages: Int = year / 100
    val yearsInAge: Int = year % 100
    val leap: Int = if (leapYear(year)) 1 else 0

    var y: Int = yearsInAge
    var m: Int = month - 2
    if (m < 1) {
      m += 12
      y -= 1
    }
    val day: Int =
      ((((26 * m - 2) / 10) + 1 + y + y / 4 + ages / 4 + 5 * ages) % 7)
    if (day == 0) 7 else day
  }

  private def leapYear(year: Int): Boolean = {
    hasNoReminder(year, 400) || hasNoReminder(year, 4) && !hasNoReminder(
      year,
      100
    )
  }

  private def hasNoReminder(brave: Int, divisor: Int): Boolean =
    brave % divisor == 0

  private def daysOfMonthCount(monthNo: Int): Int = {
    monthNo match {
      case 1 | 3 | 5 | 7 | 8 | 10 | 12 => 31
      case 4 | 6 | 9 | 11              => 30
      case 2                           => if (year % 4 == 0) 29 else 28
    }
  }
}

object Schedule1 extends Enumeration {
  type Schedule = Value
  val Teenth, First, Second, Third, Fourth, Last = Value
}

object Meetup1 {
  val Mon = DayOfWeek.MONDAY.getValue
  val Tue = DayOfWeek.TUESDAY.getValue
  val Wed = DayOfWeek.WEDNESDAY.getValue
  val Thu = DayOfWeek.THURSDAY.getValue
  val Fri = DayOfWeek.FRIDAY.getValue
  val Sat = DayOfWeek.SATURDAY.getValue
  val Sun = DayOfWeek.SUNDAY.getValue
}

// Instructions
// Calculate the date of meetups.

// Typically meetups happen on the same day of the week. In this exercise, you will take a description of a meetup date, and return the actual meetup date.

// Examples of general descriptions are:

// The first Monday of January 2017
// The third Tuesday of January 2017
// The wednesteenth of January 2017
// The last Thursday of January 2017
// The descriptors you are expected to parse are: first, second, third, fourth, fifth, last, monteenth, tuesteenth, wednesteenth, thursteenth, friteenth, saturteenth, sunteenth

// Note that "monteenth", "tuesteenth", etc are all made up words. There was a meetup whose members realized that there are exactly 7 numbered days in a month that end in '-teenth'. Therefore, one is guaranteed that each day of the week (Monday, Tuesday, ...) will have exactly one date that is named with '-teenth' in every month.

// Given examples of a meetup dates, each containing a month, day, year, and descriptor calculate the date of the actual meetup. For example, if given "The first Monday of January 2017", the correct meetup date is 2017/1/2.
