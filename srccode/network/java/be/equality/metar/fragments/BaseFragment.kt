package be.equality.metar.fragments

import android.support.v4.app.Fragment

open class BaseFragment : Fragment() {


    open var TAG : String = ""

    companion object {
        val AIRPORTS = 0
        val RAW = 1
        val DETAILS = 2
        val OLD = 3
    }

}