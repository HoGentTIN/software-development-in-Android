package be.equality.dualpane


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import be.equality.dualpane.domain.Comic
import kotlinx.android.synthetic.main.activity_ragecomic_list.*
import kotlinx.android.synthetic.main.ragecomic_list.*
import kotlinx.android.synthetic.main.ragecomic_list_content.view.*

/**
 * An activity representing a list of Comics. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [RagecomicDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class RagecomicListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    private var comics: List<Comic>? = null


    /**
     * Creates the Activity
     * - dependencies (none)
     * - restore saved state (none)
     * - set view
     * - restore state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ragecomic_list)

        setSupportActionBar(toolbar)

        if (ragecomic_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

    }

    /**
     * Starts the Activity
     * - Allocate resources
     * - register click listeners
     * - update UI
     */
    override fun onStart() {
        super.onStart()

        comics = createComics()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        toolbar.title = title
        ragecomic_list.adapter = SimpleItemRecyclerViewAdapter(this, comics!!, twoPane)
    }

    private fun createComics(): List<Comic> {
        val comicList = mutableListOf<Comic>()

        val resources = applicationContext.resources
        val names = resources.getStringArray(R.array.names)
        val descriptions = resources.getStringArray(R.array.descriptions)
        val urls = resources.getStringArray(R.array.urls)

        // Get rage face images.
        val typedArray = resources.obtainTypedArray(R.array.images)
        val imageResIds = IntArray(names.size)
        for (i in 0 until names.size) {
            imageResIds[i] = typedArray.getResourceId(i, 0)
            val theComic = Comic(imageResIds[i], names[i], descriptions[i], urls[i], names[i])
            comicList.add(theComic)
        }
        typedArray.recycle()

        return comicList
    }


    /**
     * Stops the Activity
     * - unregister listeners
     * - release allocated resources
     */
    override fun onStop() {
        super.onStop()

        fab.setOnClickListener(null)


        ragecomic_list.adapter = null
    }

    /***********************************************************************************************
     * Recyclerview
     *
     ***********************************************************************************************
     */
    class SimpleItemRecyclerViewAdapter(private val parentActivity: RagecomicListActivity,
                                        private val comics: List<Comic>,
                                        private val twoPane: Boolean) :
            RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                // Every view has a tag that can be used to store data related to that view
                // Here each item in the RecyclerView keeps a reference to the comic it represents.
                // This allows us to reuse a single listener for all items in the list
                val item = v.tag as Comic
                if (twoPane) {
                    val fragment = RagecomicDetailFragment.newInstance(item)
                    parentActivity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.ragecomic_detail_container, fragment)
                            .commit()
                } else {
                    val intent = Intent(v.context, RagecomicDetailActivity::class.java).apply {
                        putExtra(RagecomicDetailFragment.ARG_COMIC, item)

                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.ragecomic_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val comic = comics[position]
            holder.name.text = comic.name
            holder.image.setImageResource(comic.imageResId)

            with(holder.itemView) {
                tag = comic // Save the comic represented by this view
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = comics.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name: TextView = view.listname
            val image: ImageView = view.list_comic_image
        }
    }
}
