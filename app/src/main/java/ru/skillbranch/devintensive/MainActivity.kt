package  ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity() {
    lateinit var benderImage:ImageView
    lateinit var textTxt:TextView
    lateinit var messageEt:EditText
    lateinit var sendBtn:ImageView
    lateinit var benderObj:Bender
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("M_MainActivity","")
        benderImage=iv_bender
        textTxt=tv_text
        messageEt=et_message
        sendBtn=iv_send
        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val quastion = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        benderObj= Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(quastion))
        val (r,g,b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)

        textTxt.text=benderObj.askQuestion()
        sendBtn.setOnClickListener{sendAnswer()}
        messageEt.setOnEditorActionListener { _, actionId, _ ->
            when(actionId){
            EditorInfo.IME_ACTION_DONE -> { sendAnswer() ; true}
            else -> false
        }
        }

    }

    override fun onRestart() {
        super.onRestart()
        Log.d("M_MainActivity","on")
    }

    private fun sendAnswer() {

            val (phrase , color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase())
            messageEt.setText("")
            val (r,g,b) = color
            benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)
            textTxt.text= phrase
            hideKeyboard()


    }



    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("STATUS", benderObj.status.name)
        outState?.putString("QUESTION", benderObj.question.name)
    }
}
