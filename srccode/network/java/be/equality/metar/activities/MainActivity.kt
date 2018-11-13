package be.equality.metar.activities


import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import be.equality.metar.R
import be.equality.metar.fragments.*
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.main_layout.*


/**
 * Recourse naming questions? : see [https://jeroenmols.com/blog/2016/03/07/resourcenaming/]
 */

class MainActivity : AppCompatActivity(), AirportsFragment.OnFragmentInteractionListener, DetailsFragment.OnFragmentInteractionListener,
OldmetarsFragment.OnFragmentInteractionListener,RawFragment.OnFragmentInteractionListener{



    /**
     * Creates this Activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        Logger.addLogAdapter(AndroidLogAdapter())

        //setSupportActionBar()

    }

    /**
     * Adding the listener to the [BottomNavigationView] of this Activity.
     *
     * Why?: Sauce: [https://www.techyourchance.com/android-activity-life-cycle-for-professional-developers/]
     */
    override fun onStart() {
        super.onStart()

        navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_item_airportlist -> {
                    viewpager_main.currentItem = BaseFragment.AIRPORTS
                    true
                }
                R.id.navigation_item_details -> {
                    viewpager_main.currentItem = BaseFragment.DETAILS
                    true
                }
                R.id.navigation_item_oldmetars -> {

                    viewpager_main.currentItem = BaseFragment.OLD
                    true
                }
                R.id.navigation_item_raw -> {
                    viewpager_main.currentItem = BaseFragment.RAW
                    true
                }
                else -> {
                    viewpager_main.currentItem = BaseFragment.AIRPORTS
                    true
                }
            }
        }

        viewpager_main.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(p0: Int): Fragment {
                when(p0){
                    BaseFragment.AIRPORTS -> return AirportsFragment()
                    BaseFragment.RAW -> return RawFragment()
                    BaseFragment.DETAILS -> return DetailsFragment()
                    BaseFragment.OLD -> return OldmetarsFragment()
                }
                return AirportsFragment()
            }

            override fun getCount(): Int {
                return 4
            }
        }

    }

    /**
     * Deattaching the listener of the BottomNavigation
     * Why?: Sauce: [https://www.techyourchance.com/android-activity-life-cycle-for-professional-developers/]
     */
    override fun onStop() {
        super.onStop()
        navigation.setOnNavigationItemReselectedListener (null)
    }

    override fun showAirportMetar() {
        Logger.i("Showing the METAR")
    }

    override fun detailsClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun oldMetarsClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rawFragmentClicker() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }







}
