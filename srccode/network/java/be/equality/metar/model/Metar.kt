package be.equality.metar.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


/**
 * Data class representing a Metar. METAR is a format for reporting weather information.
 * A METAR weather report is predominantly used by pilots in fulfillment of a part of a
 * pre-flight weather briefing, and by meteorologists, who use aggregated METAR
 * information to assist in weather forecasting.
 *
 *
 * @constructor Sets all properties of the post
 * @property id The id of this metar
 * @property rawMetar The raw code of the metar
 * @property airport The airport where this Metar belongs to
 * @property dayOfMonth The day of the month for this Metar
 * @property time The time of this Metar
 * @property windDirection The wind direction in Metar format (e.g. 12012MPS indicates
 * the wind direction is from 120° (east-southeast) at a speed of 12 m/s (23 knots; 27 mph; 44 km/h)
 * @property windSpeed Speed of the wind
 * @property gusts The possible gusts of the weather
 * @property lineOfSight The line of sight (expressed in meter)
 *
 * We use the Android Extensions plugin which now includes an automatic
 * Parcelable implementation generator. Declare the serialized properties in
 * a primary constructor and add a @Parcelize annotation, and writeToParcel()/createFromParcel()
 * methods will be created automatically.
 */
@Parcelize
class Metar(val id: Long,
            @field:Json(name = "Raw-Report") val rawMetar: String,
            @field:Json(name = "Station") val airport: String,
            val dayOfMonth: Int,
            val time: Int,
            @field:Json(name = "Wind-Direction") val windDirection: Int,
            @field:Json(name = "Wind-Speed") val windSpeed: Int,
            @field:Json(name = "Wind-Gust") val gusts: String,
            @field:Json(name = "Visibility") val lineOfSight: Int
) : Parcelable