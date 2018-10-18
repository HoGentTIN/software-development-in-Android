/***********************************************************************************************
 * Recyclerview
 ***********************************************************************************************
 */
class SimpleItemRecyclerViewAdapter(private val parentActivity: RagecomicListActivity,
                                    private val comics: List<Comic>,
                                    private val twoPane: Boolean) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

    /**
     * The onClickListener
     */
    private val onClickListener: View.OnClickListener


    /**
     * The primary constructor cannot contain any code. Initialization code
     * can be placed in initializer blocks, which are prefixed with the
     * init keyword.
     */
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

    /**
     * Creates the ViewHolder, i.e. inflates the layout.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.ragecomic_list_content, parent, false)
        return ViewHolder(view)
    }

    /**
     * Binds the layout to the ViewHolder.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comic = comics[position]
        holder.name.text = comic.name
        holder.image.setImageResource(comic.imageResId)

        with(holder.itemView) {
            tag = comic // Save the comic represented by this view
            setOnClickListener(onClickListener)
        }
    }

    /**
     * Returns the total number of items of the items displayed in the RecyclerView.
     */
    override fun getItemCount() = comics.size

    /**
     * The class definition of the ViewHolder.
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.listname
        val image: ImageView = view.list_comic_image
    }
}


