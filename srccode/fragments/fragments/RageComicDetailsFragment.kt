/**
 * Copyright (c) 2017 Razeware LLC

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

package com.raywenderlich.alltherages.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.alltherages.R
import java.io.Serializable
import com.raywenderlich.alltherages.databinding.FragmentRageComicDetailsBinding
import com.raywenderlich.alltherages.domain.Comic

//1
class RageComicDetailsFragment : Fragment() {

    private lateinit var binding: FragmentRageComicDetailsBinding


    companion object {

        private const val COMIC = "comic"

        fun newInstance(comic: Comic): RageComicDetailsFragment {
            val args = Bundle()
            args.putSerializable(COMIC, comic as Serializable)
            val fragment = RageComicDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }


    /**
     * Oncreateview of the Fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentRageComicDetailsBinding.inflate(inflater,
                container, false)

        //We check whether the arguments have a serializable
        //If so we created a static fragment
        //If not the comic is provided by the [com.raywenderlich.alltherages.RageComicListFragment]

        if (arguments?.containsKey(COMIC) ?: false) {
            val comic = arguments!!.getSerializable(COMIC) as Comic
            binding.comic = comic
            comic.text = String.format(getString(R.string.description_format), comic.description, comic.url)
        }

        return binding.root
    }

    /**
     * Change the binding of the comic
     */
    fun changeComic(comic: Comic?) {
        binding.comic = comic
        comic?.text = String.format(getString(R.string.description_format), comic?.description, comic?.url)
    }

    fun getComic() : Comic?{
        return binding.comic
    }



}
