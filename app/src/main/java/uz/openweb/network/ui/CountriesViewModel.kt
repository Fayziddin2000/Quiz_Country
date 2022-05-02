package uz.openweb.network.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import uz.openweb.network.data.entities.Country
import uz.openweb.network.data.local.CountriesRepository
import uz.openweb.network.data.remote.countriesApi

class CountriesViewModel(application: Application) : AndroidViewModel(application) {

    private val countriesRepository = CountriesRepository(application.applicationContext)


    private val _countriesList: MutableLiveData<List<Country>> = MutableLiveData<List<Country>>()
    private val _errorLiveData: MutableLiveData<String> = MutableLiveData<String>()


    val mCountriesList: LiveData<List<Country>> get() = _countriesList
    val mErrorLiveData: LiveData<String> get() = _errorLiveData


    fun getCountries(countryName: String) {
        if (countryName.isEmpty())
            getAllCountries()
        else
            getCountryByName(countryName)
    }

    private fun getCountryByName(countryName: String) {
        viewModelScope.launch {

            try {
                val countriesList = countriesApi.getCountryByName(countryName)
                _countriesList.value = countriesList
            } catch (e: Exception) {
                _errorLiveData.value = e.message.toString()
            }

        }
    }

    private fun getAllCountries() {
        viewModelScope.launch {
            val countriesList = countriesApi.getAllCountries()
            _countriesList.value = countriesList
        }
    }


    fun saveCountriesToDB() {
        Log.d("XXXXX", "saveCountriesToDB: ${mCountriesList.value} ")
        if (mCountriesList.value != null) {
            countriesRepository.saveCountries(mCountriesList.value!!)
        }
    }

    fun getCountriesFromDB() {
        viewModelScope.launch {
            val list = countriesRepository.getCountries()
            _countriesList.value = list
        }
    }

}
