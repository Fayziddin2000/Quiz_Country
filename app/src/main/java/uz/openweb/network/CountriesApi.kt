import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import uz.openweb.network.Country

interface CountriesApi {

    @GET("all")
    suspend fun getCountry(): ArrayList<Country>

}

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://restcountries.com/v2/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val countriesApi: CountriesApi = retrofit.create(CountriesApi::class.java)