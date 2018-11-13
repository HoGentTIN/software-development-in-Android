package be.equality.metar.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.equality.metar.R
import be.equality.metar.ui.MetarViewModel


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [RawFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [RawFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RawFragment : BaseFragment() {


    /**
     * The [MetarViewModel] we will use to display the datza
     */
    private lateinit var viewModel: MetarViewModel


    /**
     * Listener we have defines to allow inter-fragment interactions.
     */
    private var listener: OnFragmentInteractionListener? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_raw2, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        TAG = "RAWFragment"
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {

        fun rawFragmentClicker()
    }

    companion object {
        val TAG = this::class.java.simpleName

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *

         * @return A new instance of fragment RawFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                RawFragment()
    }
}
