package uz.openweb.network

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import countriesApi
import kotlinx.coroutines.launch
import uz.openweb.network.databinding.FragmentSearchCountryBinding

class SearchCountryFragment : Fragment(), CountryHelper {

    private lateinit var _binding: FragmentSearchCountryBinding
    private val mBinding get() = _binding
    private lateinit var adapter: CountriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchCountryBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = CountriesAdapter(this)
        mBinding.rvCountries.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvCountries.adapter = adapter
        mBinding.btnGetCountries.setOnClickListener {
            loadCountries()
        }
    }

    private fun loadCountries() {
        lifecycleScope.launch {


            val countries = countriesApi.getCountry()
            adapter.setCountries(countries)


        }

    }

    private fun addFragment(fragment: Fragment){

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(CountryInfoFragment::javaClass.name).commit()
    }

    override fun onItemClicked(country: Country) {
        addFragment(CountryInfoFragment(country))
    }


}