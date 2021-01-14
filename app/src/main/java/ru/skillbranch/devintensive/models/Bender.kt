package ru.skillbranch.devintensive.models

class Bender (var status:Status = Status.NORMAL, var question: Question = Question.NAME){

   fun askQuestion() = question.question

    fun listenAnswer(anser:String): Pair<String,Triple<Int,Int,Int>> {

        return if (question.ansers.contains(anser)){
            question=question.nextQuestion()
            "Отлично - ты справился\\n ${question.question}" to status.color

            }else{
            status = status.nextStatus()
            if (status.ordinal==0){

                question = Question.NAME
                "Это неправильный ответ. Давай все по новой\\n ${question.question}" to status.color

            }else{
                "Это неправильный ответ\\n ${question.question}" to status.color

            }
        }
    }


    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus() =
            if (ordinal < values().lastIndex) {
                values()[ordinal + 1]
            } else {
                values()[0]
            }
    }
    enum class Question(val question:String, val ansers:List<String>){
        NAME("Как меня зовут?", listOf("Бендер", "bender")){
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")){
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")){
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()){
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion():Question
    }
}