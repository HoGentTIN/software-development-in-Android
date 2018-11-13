package be.equality.metar.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import be.equality.metar.R
import be.equality.metar.activities.MainActivity
import be.equality.metar.model.Airport
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_airports.*
import kotlinx.android.synthetic.main.row_layout.view.*


/**
 * A simple [Fragment] subclass which show the list of Airports available.
 * Activities that contain this fragment must implement the
 * [AirportsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AirportsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AirportsFragment : BaseFragment() {

    /**
     * The listener to interact with other Activities and Fragments
     */
    private var listener: OnFragmentInteractionListener? = null

    /**
     * List containing the aiports for which a METAR can be requested.
     */
    private var airports: List<Airport>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_airports, container, false)
    }


    override fun onStart() {
        super.onStart()
        airports = createAirports()
        recyclerview.adapter = SimpleItemRecyclerViewAdapter(activity as MainActivity,airports!!)
        recyclerview.layoutManager = LinearLayoutManager(context)
    }

    /**
     * Creates a basic list of Airports
     */
    private fun createAirports() : List<Airport>{
        val airportList = mutableListOf<Airport>()
        val resources = activity?.applicationContext?.resources
        resources?.let {
            val ids = it.getIntArray(R.array.ids)
            val descriptions = it.getStringArray(R.array.descriptions)
            val locationIndicators = it.getStringArray(R.array.locationIndicator)

            for (i in 0 until ids.size) {
                val airport = Airport(ids[i],locationIndicators[i],descriptions[i])
                airportList.add(airport)
            }
        }
        return airportList
 }


    /**
     * Casting the activity to an [OnFragmentInteractionListener]
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        TAG = "AirportsFragment"


        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    /**
     * Releases the listener of this Fragment when the Fragment is detached.
     */
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
        fun showAirportMetar()
    }


    /***********************************************************************************************
     * Recyclerview showing the aiports
     *
     ***********************************************************************************************
     */
    class SimpleItemRecyclerViewAdapter(private val parentActivity: MainActivity,
                                        private val airports: List<Airport>
    ) :
            RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { _ ->
                //val item = v.tag as Airport
                Logger.i("Pressed an element on the Recyclerview")
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_layout, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val airport = airports[position]
            holder.name.text = airport.description
            holder.thumbNail.setImageResource(R.drawable.airport)

            with(holder.thumbNail) {
                tag = airport // Save the airport represented by this view
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = airports.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name: TextView = view.name
            var description: TextView = view.textview_rowlayout_description
            var thumbNail: ImageView = view.imageview_rowlayout_thumbnail
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment AirportsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                AirportsFragment()
    }
}
