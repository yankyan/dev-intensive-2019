package ru.skillbranch.devintensive.models

import java.util.*

class User (
    val id : String,
    var firstName : String?,
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
init {
    println(" $firstName $lastName $id")
}
    companion object Factory{
        private var lastId:Int =-1
        fun makeUser(fullname:String?): User? {

        lastId++
            val parts:List<String>? = fullname?.split(" ")
               var firstname = parts?.getOrNull(0)
               var lastName =parts?.getOrNull(1)
           if (fullname!=null) {return User("$lastId","$firstname" ,"$lastName" )}
           else{
               return null}
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

}