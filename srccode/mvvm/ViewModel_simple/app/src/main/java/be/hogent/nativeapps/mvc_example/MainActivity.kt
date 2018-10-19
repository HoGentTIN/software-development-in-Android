package be.hogent.nativeapps.mvc_example

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var inputViewModel: InputViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputViewModel = ViewModelProviders.of(this).get(InputViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()
        updateUI()

        button_main_updateText.setOnClickListener {
            updateUI()
        }

        editText_main_userInput.afterTextChanged {
            inputViewModel.userInput = it
        }
    }

    private fun updateUI() {
        text_main_writtenText.text = "The user wrote ${inputViewModel.userInput}"
    }
}
