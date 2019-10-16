package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?): Pair<String?, String?> {
        var fullName1:String? = null
        if (!fullName.isNullOrBlank()) {
             fullName1 = fullName
        }
        val parts:List<String>? = fullName1?.split(" ")
        var firstName = parts?.getOrNull(0)
        var lastName =parts?.getOrNull(1)
        return firstName to lastName
    }
}