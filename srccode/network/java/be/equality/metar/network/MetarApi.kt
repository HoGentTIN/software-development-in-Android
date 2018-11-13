package be.equality.metar.network

import be.equality.metar.model.Metar
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * The interface which provides methods to get result of the Metar webservice
 */
interface MetarApi {

    /**
     * Get a metar from the API
     */
    @GET("/api/metar/{icao}")
    fun getMetar(@Path("icao") icao : String): Observable<Metar>
}