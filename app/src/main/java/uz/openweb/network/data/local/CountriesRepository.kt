package uz.openweb.network.data.local

import android.content.Context
import android.util.Log
import kotlinx.coroutines.*
import uz.openweb.network.data.entities.Country
import kotlin.coroutines.CoroutineContext

class CountriesRepository(context: Context) : CoroutineScope {

    private val countriesDao = CountriesDataBase.invoke(context).countriesDao()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    fun saveCountries(countries: List<Country>) {
        launch {
            countriesDao.insertData(countries)
            Log.d("XXXXX", "saveCountries: ")
        }
    }

    suspend fun getCountries() = withContext(Dispatchers.IO) { countriesDao.getCountries() }
}