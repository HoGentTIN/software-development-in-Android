package be.hogent.nativeapps.mvc_example

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import be.hogent.nativeapps.mvc_example.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var inputViewModel: InputViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputViewModel = ViewModelProviders.of(this).get(InputViewModel::class.java)

        //The binding is what allows us to link the layout file with the viewmodel.
        //It holds a reference to the view in binding.root, and references to all variables defined in the layout file.
        // In Fragments onCreateView call you'll need the binding.root to return the inflated view.
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //In the layoutfile we defined a variable for the inputViewModel.
        //Here we assign a value to it.
        binding.inputViewModel = inputViewModel
        //Setting the lifecycleowner to this activity is required, as otherwise the UI won't update when changes occur.
        //This is because LiveData only updates when the owner is in an ACTIVE state.
        //If there's no owner, it can't be active.
        binding.setLifecycleOwner(this)
    }

}
