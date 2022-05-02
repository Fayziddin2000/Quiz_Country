package uz.openweb.network.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.openweb.network.CountryHelper
import uz.openweb.network.data.entities.Country
import uz.openweb.network.databinding.ItemCountryBinding

class CountriesAdapter(private val countryHelper: CountryHelper) : RecyclerView.Adapter<VH>() {

    private var countriesList: ArrayList<Country>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding, countryHelper)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (!countriesList.isNullOrEmpty())
            holder.bind(countriesList!![position])
    }

    override fun getItemCount(): Int {
        return if (countriesList.isNullOrEmpty()) 0 else countriesList!!.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCountries(list: List<Country?>) {
        countriesList = ArrayList()
        list.forEach { it -> if (it != null) countriesList!!.add(it) }
        notifyDataSetChanged()
    }

}

class VH(private val binding: ItemCountryBinding, private val countryHelper: CountryHelper) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Country) {
        binding.root.setOnClickListener {
            countryHelper.onItemClicked(
                item
            )
        }
        Glide.with(itemView.context).load(item.flags.png).into(binding.ivCountryFlag)
        binding.tvCountryName.text = item.name
    }
}
