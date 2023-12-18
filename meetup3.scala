import java.time.{DayOfWeek, LocalDate}
import scala.collection.immutable.TreeMap

import Schedule.Schedule

case class Meetup(month: Int, year: Int) {

  def day(dayOfWeek: Int, schedule: Schedule): LocalDate = {
    val dayMap: Map[Int,Int] = createDayMap(dayOfWeek)

    val monthDay: Int = countDay(dayMap, schedule: Schedule)
    LocalDate.of(year, month, monthDay)
  }

  private def createDayMap(dayOfWeek: Int): Map[Int,Int] = {
    val monthDaysMap: Map[Int,Int] = {
      TreeMap((1 to daysOfMonthCount(month)).map(key => key -> ((key - 2 + firstMonthDay)%7 + 1)): _*)
    }
    monthDaysMap.filter { case (_, value) => value == dayOfWeek }
  }

  private def countDay(dayMap: Map[Int,Int], schedule: Schedule): Int = {
    schedule.id match {
      case 0 => dayMap.keys.toList.find((13 to 19).contains(_)).getOrElse(-1)
      case 5 => dayMap.keys.toList.last
      case _ => dayMap.keys.toList(schedule.id - 1)
    }
  }

  private def firstMonthDay: Int = {
   LocalDate.of(year, month, 1).getDayOfWeek().getValue()
  }

  private def daysOfMonthCount(monthNo: Int): Int = {
    LocalDate.of(year, month, 1).lengthOfMonth()
  }
}

object Schedule extends Enumeration {
  type Schedule = Value
  val Teenth, First, Second, Third, Fourth, Last = Value
}

object Meetup {
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
