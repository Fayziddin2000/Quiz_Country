package uz.openweb.network.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import uz.openweb.network.CountryHelper
import uz.openweb.network.R
import uz.openweb.network.data.entities.Country
import uz.openweb.network.databinding.FragmentSearchCountryBinding


class SearchCountryFragment : Fragment(), CountryHelper {

    private lateinit var _binding: FragmentSearchCountryBinding
    private val mBinding get() = _binding
    private lateinit var adapter: CountriesAdapter
    private lateinit var    viewModel: CountriesViewModel

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
        viewModel =
            ViewModelProvider(requireActivity())[CountriesViewModel(requireActivity().application)::class.java]
        viewModel.mCountriesList.observe(viewLifecycleOwner) {
            if (it != null)
                adapter.setCountries(it)
            else {
                Log.d("XXXXX", "onViewCreated:$it ")
            }
        }

        mBinding.btnSaveInDb.setOnClickListener {
            viewModel.saveCountriesToDB()
        }
        mBinding.getFromDb.setOnClickListener {
            viewModel.getCountriesFromDB()
        }

    }

    private fun loadCountries() {
        viewModel.getCountries(mBinding.etCityName.text.toString())
    }

    private fun addFragment(fragment: Fragment) {

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(CountryInfoFragment::javaClass.name).commit()
    }

    override fun onItemClicked(country: Country) {
        addFragment(CountryInfoFragment(country))
    }


}