package uz.openweb.network.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.openweb.network.data.entities.Country
import uz.openweb.network.data.remote.countriesApi
import kotlin.Exception

class MainViewModel : ViewModel() {

    private val _countriesList: MutableLiveData<List<Country>> = MutableLiveData<List<Country>>()
    private val _errorLiveData: MutableLiveData<String> = MutableLiveData<String>()

    private val _currentQuestionNumber: MutableLiveData<Int> = MutableLiveData(1)
    private val _rightAnswersCount: MutableLiveData<Int> = MutableLiveData(0)

    val mCurrentQuestion: LiveData<Int> get() = _currentQuestionNumber
    val mRightAnswerCount: LiveData<Int> get() = _rightAnswersCount

    val mCountriesList: LiveData<List<Country>> get() = _countriesList
    val mErrorLiveData: LiveData<String> get() = _errorLiveData


    fun onRightAnswer() {
        _rightAnswersCount.value = _rightAnswersCount.value!! + 1
    }

    fun nextQuestion() {
        _currentQuestionNumber.value = _currentQuestionNumber.value!! + 1
    }

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

    fun setDefaultValues() {
        _currentQuestionNumber.value = 1
        _rightAnswersCount.value = 0
    }

}