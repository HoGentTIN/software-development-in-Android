package com.example.eothein.intentexample.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.speech.RecognizerIntent
import android.support.v4.app.Fragment
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.basgeekball.awesomevalidation.AwesomeValidation
import com.example.eothein.intentexample.R
import java.util.Locale

import com.basgeekball.awesomevalidation.ValidationStyle.UNDERLABEL
import com.example.eothein.intentexample.activities.FullscreenActivity
import com.example.eothein.intentexample.activities.MainActivity

import kotlinx.android.synthetic.main.fragment_main.*


/**
 * The fragment containing the Buttons which will send their corresponding intents.
 */
class MainActivityFragment : Fragment() {


    /**
     * Validator for the EditText (https://github.com/thyrlian/AwesomeValidation)
     */
    private var mAwesomeValidation: AwesomeValidation? = null


    /**
     * Creates the view. See the Fragment Life Cycle.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_main, container, false)
        return v
    }

    /**
     * Registers the click listeners on the buttons.
     */
    override fun onResume() {
        super.onResume()
        button_contacts.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            checkForCompatibility(intent, PICK_CONTACT)
        }


        button_dialer.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0123456789")
            checkForCompatibility(intent, PICK_NUMBER)
        }


        button_url.setOnClickListener {
            //Test whether the inserted text is an URL
            if (mAwesomeValidation!!.validate()) {
                val uri = Uri.parse(url_text.text.toString())
                val myIntent = Intent(Intent.ACTION_VIEW, uri)
                checkForCompatibility(myIntent)
            }
        }

        button_google.setOnClickListener {
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            checkForCompatibility(intent)
        }

        button_speak.setOnClickListener {
            startSpeech()
        }

        button_activity.setOnClickListener{
            val intent = MainActivity.newFullScreenIntent(activity!!.applicationContext)
            startActivity(intent)
        }


    }

    /**
     * Unregisters the listeners from the buttons
     */
    override fun onPause() {
        super.onPause()
        button_contacts.setOnClickListener(null)
        button_speak.setOnClickListener(null)
        button_google.setOnClickListener(null)
        button_url.setOnClickListener(null)
        button_dialer.setOnClickListener(null)
    }

    /**
     * Is called when the underlying Activity has been created.
     * @param savedInstanceState
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mAwesomeValidation = AwesomeValidation(UNDERLABEL)
        mAwesomeValidation!!.setContext(activity)  // mandatory for UNDERLABEL style
        mAwesomeValidation!!.addValidation(activity, R.id.url_text, Patterns.WEB_URL, R.string.url)
    }


    /**
     * Checks whether there is an application available to handle the intent. If not, it will
     * start the Google Play market place to see whether an application exists which can be installed.
     * If there is an app available, it will start the Activity of that app.
     */
    private fun checkForCompatibility(intent: Intent, requestCode: Int? = null) {
        //Check whether an activity exists to start with the provided intent
        val manager = activity!!.packageManager
        val name = intent.resolveActivity(manager)
        if (name == null) {
            val marketIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + activity!!.packageName))
            if (marketIntent.resolveActivity(manager) != null) {
                startActivity(marketIntent)
            } else {
                val t = Toast.makeText(activity, "Could not find the market activity", Toast.LENGTH_LONG)
                t.show()
            }
        } else {
            if (requestCode == null)
                startActivity(intent)
            else
                startActivityForResult(intent, requestCode)
        }
    }


    /**
     * Start the speech application. (https://developer.android.com/reference/android/speech/RecognizerIntent.html)
     */
    private fun startSpeech() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH) // takes user’s speech input and returns it
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM) // Considers input in free form English
        checkForCompatibility(intent, SPEECH_INPUT)
    }


    /**
     * Listening to results provided by other activities.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PICK_CONTACT -> {
                    Log.i(this.javaClass.getName(), "Received Contact")
                    Toast.makeText(activity, "Received Contact", Toast.LENGTH_LONG).show()
                }
                PICK_NUMBER -> {
                    Log.i(this.javaClass.getName(), "Received Number")
                    Toast.makeText(activity, "Received number", Toast.LENGTH_LONG).show()
                }
                SPEECH_INPUT -> {
                    val result = data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    txt_result.text = result[0]
                }
            }
        }
    }

    companion object {

        /**
         * Id's for the activities which return results
         */
        private val PICK_CONTACT = 1
        private val PICK_NUMBER = 2
        private val SPEECH_INPUT = 3
    }
}
