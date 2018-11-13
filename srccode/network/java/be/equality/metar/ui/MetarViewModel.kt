package be.equality.metar.ui

import android.arch.lifecycle.MutableLiveData
import android.view.View
import be.equality.metar.base.BaseViewModel
import be.equality.metar.model.Metar
import be.equality.metar.network.MetarApi
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MetarViewModel : BaseViewModel() {


    private val rawMetar = MutableLiveData<String>()



    /**
     * The instance of the MetarApi class
     * to get back the results of the API
     */
    @Inject
    lateinit var metarApi : MetarApi

    /**
     * Indicates whether the loading view should be displayed.
     */
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()


    /**
     * Represents a disposable resources
     */
    private  var subscription: Disposable

    init {
        subscription = metarApi.getMetar("EBOS")
                //we tell it to fetch the data on background by
                .subscribeOn(Schedulers.io())
                //we like the fetched data to be displayed on the MainTread (UI)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe{ onRetrieveMetarStart()}
                .doOnTerminate { onRetrieveMetarFinish()}
                .subscribe(
                        { result -> onRetrieveMetarSucces(result) },
                        { error -> onRetrieveMetarError(error) }
                )

    }

    private fun onRetrieveMetarError(error: Throwable) {
        Logger.e(error.message!!)
    }

    private fun onRetrieveMetarSucces(result: Metar) {
        rawMetar.value = result.rawMetar
        Logger.i(result.rawMetar)

    }

    private fun onRetrieveMetarFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveMetarStart() {
        loadingVisibility.value = View.VISIBLE
    }

    /**
     * Disposes the subscription when the [BaseViewModel] is no longer used.
     */
    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }


    fun getRawMetar(): MutableLiveData<String> {
        return rawMetar
    }




}