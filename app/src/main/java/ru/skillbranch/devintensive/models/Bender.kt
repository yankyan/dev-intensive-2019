package ru.skillbranch.devintensive.models

import android.util.Log
import kotlin.text.contains as contains1

class Bender (var status:Status = Status.NORMAL, var question: Question = Question.NAME){

   fun askQuestion() = question.question

    fun listenAnswer(anser:String): Pair<String,Triple<Int,Int,Int>> {

        return if (!question.isAnswerValid(anser).first) {
            "${question.isAnswerValid(anser).second}\n${question.question}" to status.color
        } else {

            if (question.ansers.contains(anser)) {
                question = question.nextQuestion()
                "Отлично - ты справился\n${question.question}" to status.color


            } else {
                status = status.nextStatus()
                if (status.ordinal == 0) {

                    question = Question.NAME
                    "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color

                } else {
                    "Это неправильный ответ\n${question.question}" to status.color

                }
            }
        }
    }


    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)) ,
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0)) ;

        fun nextStatus() =
            if (ordinal < values().lastIndex) {
                values()[ordinal + 1]
            } else {
                values()[0]
            }
    }
    enum class Question(val question:String, val ansers:List<String>) {
        NAME("Как меня зовут?", listOf("Бендер", "Bender")) {
            override fun nextQuestion(): Question = PROFESSION
            override fun isAnswerValid(anser: String): Pair<Boolean, String> {
             //   Log.e("VALIDATION","Валидация ${anser.first().isUpperCase()}  $anser ")
                return anser.first().isUpperCase() to "Имя должно начинаться с заглавной буквы"
            }

        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")){
            override fun isAnswerValid(anser: String): Pair<Boolean, String> {
                return anser.first().isLowerCase() to "Профессия должна начинаться со строчной буквы"
            }

            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")){
            override fun isAnswerValid(anser: String): Pair<Boolean, String> {
                val regex = """[\d]""".toRegex()
                return !regex.containsMatchIn(anser) to "Материал не должен содержать цифр"
            }

            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun isAnswerValid(anser: String): Pair<Boolean, String> {
                val regex = """[\D]""".toRegex()
                return !regex.containsMatchIn(anser) to "Год моего рождения должен содержать только цифры"
            }

            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun isAnswerValid(anser: String): Pair<Boolean, String> {
                val regex = """[\D]""".toRegex()
                return ((!regex.containsMatchIn(anser))&&(anser.length ==7))  to "Серийный номер содержит только цифры, и их 7"
            }

            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()){
            override fun isAnswerValid(anser: String): Pair<Boolean, String> {
                TODO("Not yet implemented")
            }

            override fun nextQuestion(): Question = IDLE
        };
        abstract fun isAnswerValid(anser: String):Pair<Boolean,String>
        abstract fun nextQuestion():Question
    }
}