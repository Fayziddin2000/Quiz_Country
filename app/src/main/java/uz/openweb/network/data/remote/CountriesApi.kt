package uz.openweb.network.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import uz.openweb.network.data.entities.Country
import java.util.*

interface CountriesApi {

    @GET("all")
    suspend fun getAllCountries(): ArrayList<Country>

    @GET("name/{name}")
    suspend fun getCountryByName(
        @Path("name") name: String
    ): ArrayList<Country>
}


private val loggingInterceptor = run {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.apply {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }
}
val client = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()
val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://restcountries.com/v2/")
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val countriesApi: CountriesApi = retrofit.create(CountriesApi::class.java)


