package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?): Pair<String?, String?> {

        val parts:List<String>? = fullName?.trim()?.split(" ")
        var firstName = parts?.getOrNull(0)?.ifEmpty { null }
        var lastName =parts?.getOrNull(1)?.ifEmpty { null }
        return firstName to lastName
    }
}