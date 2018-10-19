package be.hogent.nativeapps.mvc_example

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var writtenText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            writtenText = it.getString(USER_INPUT)
        }
    }

    override fun onStart() {
        super.onStart()
        updateUI()

        button_main_updateText.setOnClickListener {
            updateUI()
        }

        editText_main_userInput.afterTextChanged {
            writtenText = it.toString()
        }
    }

    private fun updateUI() {
        text_main_writtenText.text = "The user wrote $writtenText"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(USER_INPUT, writtenText)
    }

    companion object {
        private const val USER_INPUT = "userInput"
    }
}
