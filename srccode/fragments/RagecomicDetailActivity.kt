package be.equality.dualpane

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import be.equality.dualpane.domain.Comic
import kotlinx.android.synthetic.main.activity_ragecomic_detail.*

/**
 * An activity representing a single Rage comic detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [RagecomicListActivity].
 */
class RagecomicDetailActivity : AppCompatActivity() {

    /**
     * Creates the Activity
     * - dependencies (none)
     * - restore saved state (none)
     * - set view
     * - restore state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ragecomic_detail)
        setSupportActionBar(detail_toolbar)

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.

            val detailFragment = RagecomicDetailFragment.newInstance(
                    intent.getSerializableExtra(RagecomicDetailFragment.ARG_COMIC) as Comic
            )

            supportFragmentManager.beginTransaction()
                    .add(R.id.ragecomic_detail_container, detailFragment)
                    .commit()
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

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Maybe you could add Rage Comics here?", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

    }


    /**
     * Stops the Activity
     * - unregister listeners
     * - release allocated resources
     */
    override fun onStop() {
        super.onStop()

        fab.setOnClickListener(null)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    // This ID represents the Home or Up button. In the case of this
                    // activity, the Up button is shown. For
                    // more details, see the Navigation pattern on Android Design:
                    //
                    // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                    navigateUpTo(Intent(this, RagecomicListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}
