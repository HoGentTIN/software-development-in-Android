package be.equality.metar.utils.extension

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity


/**
 * Extension function to the FragmentManager which accepts a Lambda with Receiver as its argument, whereas the FragmentTransaction is the Receiver.
 * Here the parameter func is an Extension function to the FragmentTransaction
 * and it has zero parameters and returns a [FragmentTransaction]. We invoke that function after calling
 * the beginTransaction() and end the transaction by calling commit().
 *
 * Sauce: [https://medium.com/thoughts-overflow/how-to-add-a-fragment-in-kotlin-way-73203c5a450b]
 */
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

/**
 * Extension functions to the AppCompatActivity which adds a fragment
 *
 * Sauce: [https://medium.com/thoughts-overflow/how-to-add-a-fragment-in-kotlin-way-73203c5a450b]
 */
fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}

/**
 * Extension functions to the AppCompatActivity which replaces a fragment
 *
 * Sauce: [https://medium.com/thoughts-overflow/how-to-add-a-fragment-in-kotlin-way-73203c5a450b]
 */
fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { replace(frameId, fragment) }
}