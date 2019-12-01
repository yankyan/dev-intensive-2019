package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs


const val SECOND = 1000L
const val MINUTE = 60*SECOND
const val HOUR =60*MINUTE
const val DAY =24*HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units:TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when(units){
        TimeUnits.SECOND-> value* SECOND
        TimeUnits.MINUTE-> value* MINUTE
        TimeUnits.HOUR-> value* HOUR
        TimeUnits.DAY-> value* DAY

    }
    this.time=time
    return this
}
fun Date.humanizeDiff(date:Date= Date()): String {
    val old:Boolean = (this.time-date.time)<0

    date.time= abs(this.time-date.time)

    val res = when (date.time){
        in 0..SECOND -> "только что"
        in SECOND..45* SECOND -> if (old) {"несколько секунд назад"} else {"через несколько секунд" }
        in 45*SECOND..75*SECOND -> if (old) {"минуту назад"} else {"через минуту"}
        in 45* SECOND..45* MINUTE->if (old) {"${TimeUnits.MINUTE.plural((date.time/ MINUTE).toInt())} назад"}
        else {"через ${TimeUnits.MINUTE.plural((date.time/ MINUTE).toInt())}"}
        in 45* MINUTE..75* MINUTE -> if (old) {"час назад"} else {"через час"}

        in 75* MINUTE..22* HOUR-> if (old) {"${TimeUnits.HOUR.plural((date.time/ HOUR).toInt())} назад" }
        else "через ${TimeUnits.HOUR.plural((date.time/ HOUR).toInt())}"

        in 22* HOUR..26* HOUR-> if (old) {"день назад"} else {"через день"}
        in 26* HOUR..360* DAY-> if (old) {"${TimeUnits.DAY.plural((date.time/ DAY).toInt())} назад"}
        else {"через ${TimeUnits.DAY.plural((date.time/ DAY).toInt())}"}
        else -> if (old) {"более года назад"} else {"более чем через год"}
    }

    return res
}


fun textDate(type: TimeUnits) = when(type){
    TimeUnits.SECOND -> arrayOf("секунд", "секунду", "секунды")
    TimeUnits.MINUTE-> arrayOf("минут","минуту","минуты")
    TimeUnits.HOUR-> arrayOf("часов", "час", "часа")
    TimeUnits.DAY-> arrayOf("дней", "день", "дня")

}
enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY;
    fun plural(i:Int): String {
        val i1 = i%100
        val t = when {
            i1 in 10..20 -> textDate(this)[0]
            i1 % 10 == 1 -> textDate(this)[1]
            i1 % 10 in 2..4 -> textDate(this)[2]
            i1 % 10 in 5..9 || i % 10 == 0 -> textDate(this)[0]


            else -> "херь"
        }
        return "$i $t"
    }


}