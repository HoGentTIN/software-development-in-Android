/**
 * Copyright (c) 2017 Razeware LLC
 *
 * @author Lance Gleason, Jens Buysse
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.alltherages.activities

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.raywenderlich.alltherages.R
import com.raywenderlich.alltherages.domain.Comic
import com.raywenderlich.alltherages.fragments.RageComicDetailsFragment
import com.raywenderlich.alltherages.fragments.RageComicListFragment
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainActivity : AppCompatActivity(), RageComicListFragment.OnRageComicSelected, AnkoLogger {

    companion object {
        val MASTER_FRAGMENT = "MASTER_FRAGENT"
        val DETAIL_FRAGMENT = "DETAIL_FRAGMENT"
        val LASTPANEKEY: String = "LAST_SAVE"
    }

    /**
     * Flag which determines whether we are in dual pane mode.
     */
    private var mIsDualPane: Boolean = false


    /**
     * States which pane was opened last.
     */
    private var mLastSinglePaneFragment: String = ""

    private var mComic: Comic? = Comic()
    /**
     * The oncreate method.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        determineLayout()
        if (savedInstanceState != null) {
            mLastSinglePaneFragment = savedInstanceState.getString(LASTPANEKEY)
            mComic = savedInstanceState.getSerializable("comic") as? Comic
            if (mComic != null)
                info("Loaded comic : $mComic")
        }

        val fm = supportFragmentManager

        if (!mIsDualPane) {
            info("In single pane mode,  setting Master")
            val masterFragment = getRageComicListFragment(false)
            fm.beginTransaction().add(R.id.single_pane, masterFragment, MASTER_FRAGMENT).commit()
            if (mLastSinglePaneFragment === DETAIL_FRAGMENT) {
                info("In single pane settign Detail")
                openSinglePaneDetailFragment()
            }
        }
        if (mIsDualPane) {
            info("In dual pane mode, setting Master")
            val masterFragment = getRageComicListFragment(true)
            fm.beginTransaction().add(R.id.master_dual, masterFragment, MASTER_FRAGMENT).commit()
            info("In dual pane setting Detail")
            val detailFragment = getRageComicDetailFragment()
            fm.beginTransaction().add(R.id.detail_dual, detailFragment, DETAIL_FRAGMENT).commit()
        }


    }

    /**
     * Saving the state of the Activity
     */
    override fun onSaveInstanceState(outState: Bundle?) {
        info("Saving the state")
        if (mIsDualPane && mComic != null) {
            outState?.putString(LASTPANEKEY, DETAIL_FRAGMENT)
            outState?.putSerializable("comic", mComic)
            info("Saving state $mComic")
        } else {
            if (mComic != null) {
                outState?.putString(LASTPANEKEY, DETAIL_FRAGMENT)
                outState?.putSerializable("comic", mComic)
                info("Saving state $mComic")
            } else {
                outState?.putString(LASTPANEKEY, MASTER_FRAGMENT)
                info("Saving nothing")
            }
        }
        super.onSaveInstanceState(outState)
    }

    /**
     * Determines the layout of this activity and sets the correspoinding flag
     */
    private fun determineLayout() {
        mIsDualPane = getResources().getBoolean(R.bool.isTablet)
    }

    /**
     * Is triggered when a Comic has been pressed.
     */
    override fun onRageComicSelected(comic: Comic) {
        //We need to react differently depending on the orientation and the screen size
        mComic = comic
        if (mIsDualPane) {
            //If we are in dual pane mode, we need to display the rage comic and it's description.
            val fragment: RageComicDetailsFragment = supportFragmentManager.findFragmentById(R.id.detail_dual) as RageComicDetailsFragment
            fragment.changeComic(comic)

        } else {
            //If we are in single pane mode, we need to change the framents and display only
            //the [com.raywenderlich.alltherages.RageComicDetailsFragment]
            val detailsFragment =
                    RageComicDetailsFragment.newInstance(comic)
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.single_pane, detailsFragment, DETAIL_FRAGMENT)
                    .addToBackStack(null)
                    .commit()
        }


    }


    /**
     * FUNCTIONALITIES TO KEEP THE FRAGMENTS AND REUSE THEM
     * Inspiration from: https://stackoverflow.com/questions/25430283/android-master-detail-flow-dual-pane-using-1-activity
     */

    fun getRageComicListFragment(popBackStack: Boolean): RageComicListFragment {
        val fm = supportFragmentManager
        var masterFragment: RageComicListFragment? = supportFragmentManager.findFragmentByTag(MASTER_FRAGMENT) as? RageComicListFragment
        if (masterFragment == null) {
            masterFragment = RageComicListFragment()
        } else {
            if (popBackStack) {
                // All matching entries will be consumed until one that doesn't match is found or the bottom of the stack is reached.
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                info("Popped the stack")
            }
            fm.beginTransaction().remove(masterFragment).commit()
            info("Removing the maser fragment")
            fm.executePendingTransactions()
        }
        return masterFragment
    }

    fun getRageComicDetailFragment(): RageComicDetailsFragment {
        val fm = supportFragmentManager
        var detailFragment: RageComicDetailsFragment? = supportFragmentManager.findFragmentByTag(DETAIL_FRAGMENT) as? RageComicDetailsFragment
        if (detailFragment == null) {
            detailFragment = RageComicDetailsFragment.newInstance(mComic ?: Comic())
        } else {

            fm.beginTransaction().remove(detailFragment).commit()
            detailFragment = RageComicDetailsFragment.newInstance(mComic ?: Comic())
            fm.executePendingTransactions()
        }
        return detailFragment
    }

    private fun openSinglePaneDetailFragment() {
        val fm = supportFragmentManager
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        val detailFragment = getRageComicDetailFragment()
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.replace(R.id.single_pane, detailFragment, DETAIL_FRAGMENT)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


}
