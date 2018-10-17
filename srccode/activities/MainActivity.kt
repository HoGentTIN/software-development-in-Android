package be.equality.activityproject

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainActivity : AppCompatActivity(), AnkoLogger {



    /*
    * The name of the persisted value which is saved between launches.
     */
    private val NAME_VALUE = "name_value"

    /**
     * The name of the shared preferences
     */
    private val PREFS_NAME = "name_prefs"



    /**
     * This method is called right after onPause(), when the activity is no longer visible to the user,
     * and it's a good place to save data that you want to commit to the disk.
     * It's followed by either onRestart(), if this activity is coming back to the foreground, or onDestroy()
     * if it's being released from memory.
     *
     * We are storing the instance state from the activity in the sharedpreferences.
     */
    override fun onStop() {
        //Don't forget to call the super methode
        super.onStop()

        val value = editText.text.toString()
        info("Saving the state : $value")

        //Get the sharedpreferences and store the value
        getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit().putString(NAME_VALUE, editText.text.toString()).apply()


    }

    /**
    * Called by the OS when the activity is first created.
     * This is where you initialize any UI elements or data objects.
     * You also have the savedInstanceState of the activity that contains
     * its previously saved state, and you can use it to recreate that state.
     *
     * We are getting the state from the sharedpreferences in this case.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val savedString = getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE).getString(NAME_VALUE,null)
        info("Starting the activituy with : $savedString")
        if(savedString != null) {
            info("Restoring the state : $savedString")
            editText.setText(savedString)
        }
    }


}
