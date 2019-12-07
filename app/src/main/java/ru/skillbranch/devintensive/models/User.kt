package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User (
    val id : String,
    val firstName : String?,
    var lastName : String?,
    var avatar : String? = null,
    var rating : Int = 0,
    var respect : Int = 0,
    var lastVisit : Date? = Date(),
    var isOnline : Boolean = false) {

    constructor(id: String, firstName: String?, lastName: String?):this(
        id=id,
        firstName=firstName,
        lastName = lastName,
        avatar = null
    )
    constructor(id: String):this(id,"Jon","Doe $id")

    companion object Factory{
        private var lastId:Int =-1
        fun makeUser(fullName:String?): User {

        lastId++

               var (firstName, lastName) = Utils.parseFullName(fullName)
                return User(id = "$lastId", firstName = "$firstName", lastName = "$lastName")
        }

    }
    fun printMe()= println("""
        id : $id 
        firstName : $firstName 
        lastName : $lastName 
        avatar : $avatar 
        rating : $rating 
        respect : $respect 
        lastVisit : $lastVisit 
        isOnline : $isOnline    
        """.trimIndent())

    class Builder {
        var id : String = ""
        var firstName : String? = null
        var lastName : String? = null
        var avatar : String? = null
        var rating : Int = 0
        var respect : Int = 0
        var lastVisit : Date? = Date()
        var isOnline : Boolean = false

        fun id(id: String) = apply { this.id = id }

        fun firstName(firstName: String?) = apply { this.firstName = firstName }

        fun lastName(lastName: String?)= apply { this.lastName = lastName }

        fun avatar(avatar: String?)= apply { this.avatar = avatar }

        fun rating(rating: Int)= apply { this.rating = rating }

        fun respect(respect: Int)= apply { this.respect = respect }

        fun lastVisit(lastVisit: Date?)= apply { this.lastVisit = lastVisit }

        fun isOnline(isOnline: Boolean)= apply { this.isOnline = isOnline }

        fun build() = User(id, firstName, lastName, avatar, rating, respect, lastVisit, isOnline)
    }
}