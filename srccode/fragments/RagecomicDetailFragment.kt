package be.equality.dualpane

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.equality.dualpane.domain.Comic
import kotlinx.android.synthetic.main.activity_ragecomic_detail.*
import kotlinx.android.synthetic.main.ragecomic_detail.view.*

/**
 * A fragment representing a single Rage comic detail screen.
 * This fragment is either contained in a [RagecomicListActivity]
 * in two-pane mode (on tablets) or a [RagecomicDetailActivity]
 * on handsets.
 */
class RagecomicDetailFragment : Fragment() {

    /**
     * The comic this fragment is representing
     */
    private lateinit var comic: Comic

    /**
     * Creates the Fragment
     * - initialise dependencies
     * - restore state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //We only allow constructing the fragment through newInstance
        // so we can force non-null arguments
        arguments!!.let {
            if (it.containsKey(ARG_COMIC)) {
                // Load the comic specified by the fragment
                // arguments.
                comic = it.getSerializable(ARG_COMIC) as Comic
            }
        }
    }

    /**
     * Creating the view for this fragment.
     * - update UI
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.ragecomic_detail, container, false)

        activity?.toolbar_layout?.title = comic.name

        // Show the comic content
        // The let construct calls the specified function block with this value as its argument
        // and returns its result.
        comic.let {
            rootView.name.text = it.name
            rootView.description.text = it.description
            rootView.comic_image.setImageResource(it.imageResId)
        }
        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_COMIC = "item_id"

        fun newInstance(comic: Comic): RagecomicDetailFragment {
            val args = Bundle()
            args.putSerializable(ARG_COMIC, comic)
            val fragment = RagecomicDetailFragment()
            fragment.arguments = args

            return fragment
        }
    }
}
