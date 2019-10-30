package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?): Pair<String?, String?> {

        val parts:List<String>? = fullName?.trim()?.split(" ")
        var firstName = parts?.getOrNull(0)?.ifEmpty { null }
        var lastName =parts?.getOrNull(1)?.ifEmpty { null }
        return firstName to lastName
    }
    fun toInitials(firstName:String?, lastName:String?): String? {
        var firstIn = firstName?.orEmpty()?.trim()?.getOrNull(0)?.toString()?:""
        var lastIn = lastName?.orEmpty()?.trim()?.getOrNull(0)?.toString()?:""

        return "$firstIn$lastIn".toUpperCase().ifEmpty { null }
    }
    fun transliteration(payload:String, divider:String=" "): String {
        var translit:String =""
        payload.forEach {
            var ch ="$it"
            var up = false
            if (it.isUpperCase()){
                ch = it.toLowerCase().toString()
                up=true
            }
            var char = when (ch){
                "а"->"a"

                "б"->"b"

                "в"->"v"

                "г"->"g"

                "д"->"d"

                "е"->"e"

                "ё"->"e"

                "ж"->"zh"

                "з"->"z"

                "и"->"i"

                "й"->"i"

                "к"->"k"

                "л"->"l"

                "м"->"m"

                "н"->"n"

                "о"->"o"

                "п"->"p"

                "р"->"r"

                "с"->"s"

                "т"->"t"

                "у"->"u"

                "ф"->"f"

                "х"->"h"

                "ц"->"c"

                "ч"->"ch"

                "ш"->"sh"

                "щ"->"sh'"

                "ъ"->""

                "ы"->"i"

                "ь"->""

                "э"->"e"

                "ю"->"yu"

                "я"->"ya"
                " "-> divider
                else->ch
            }
if (up){
    char = char.capitalize()
}
            translit = "$translit$char"
        }
        return translit

    }
}