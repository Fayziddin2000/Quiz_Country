package uz.openweb.network.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import uz.openweb.network.R
import uz.openweb.network.data.entities.Country
import uz.openweb.network.databinding.FragmentCountryInfoBinding
import java.lang.StringBuilder


class CountryInfoFragment(private val country: Country) : Fragment() {

    private lateinit var _binding: FragmentCountryInfoBinding
    private val mBinding: FragmentCountryInfoBinding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryInfoBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(view.context).load(country.flags.png).into(mBinding.ivFlagInfo)
        mBinding.areaInfo.text = getString(R.string.text_area, country.area)

        val borders = StringBuilder()
        if (country.borders.isNullOrEmpty()) mBinding.borderInfo.text =
            getString(R.string.text_borders, "Nan")
        else {
            for (i in country.borders) {
                borders.append(i)
                borders.append(" ")
            }
            mBinding.borderInfo.text = getString(R.string.text_borders, borders)
        }

        mBinding.capitalInfo.text = getString(R.string.text_capital, country.capital)
        mBinding.populationInfo.text = getString(R.string.text_population, country.population)
        mBinding.nameInfo.text = country.name

        val phone = StringBuilder()
        if (country.callingCodes != null) {
            for (i in country.callingCodes) phone.append(i)
            mBinding.phoneCodeInfo.text = getString(R.string.text_phone, phone)
        }
    }

}