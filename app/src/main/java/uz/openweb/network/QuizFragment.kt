package uz.openweb.network

import android.animation.Animator
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import countriesApi
import kotlinx.coroutines.launch
import uz.openweb.network.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {

    private lateinit var _binding: FragmentQuizBinding
    private val mBinding get() = _binding
    private var country: Country? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(layoutInflater, container, false)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getRandomCountry()

    }

    private fun initView() {
        if (country != null) {
            mBinding.tvCapitalQuiz.text = getString(R.string.text_capital, country!!.capital)
            mBinding.tvAreaQuiz.text = getString(R.string.text_area, country!!.area.toString())

            val borders = StringBuilder()
            if (country!!.borders.isNullOrEmpty()) mBinding.tvBordersQuiz.text =
                getString(R.string.text_borders, "Nan")
            else {
                for (i in country!!.borders) {
                    borders.append(i)
                    borders.append(" ")
                }
                mBinding.tvBordersQuiz.text = getString(R.string.text_borders, borders)
            }

            mBinding.tvPopulationQuiz.text =
                getString(R.string.text_population, country!!.population)
            mBinding.tvCallingCodesQuiz.text =
                getString(R.string.text_phone, country!!.callingCodes)
        } else getRandomCountry()
        mBinding.animQuiz.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator?) {

            }

            override fun onAnimationEnd(p0: Animator?) {

            }

            override fun onAnimationCancel(p0: Animator?) {

            }
            override fun onAnimationRepeat(p0: Animator?) {
                mBinding.animQuiz.pauseAnimation()
                mBinding.animQuiz.isVisible = false
            }

        })
        mBinding.btnAnswer.setOnClickListener { compareCountryNames(mBinding.etCountryQuiz.text.toString()) }
        mBinding.btnNext.setOnClickListener { getRandomCountry() }
    }

    private fun getRandomCountry() {
        val connectivityManager: ConnectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager.isActiveNetworkMetered)
        lifecycleScope.launch {
            val countries = countriesApi.getCountry()
            country = countries[(0..countries.size).shuffled()[0]]
            initView()
        }
        else Toast.makeText(requireContext(), " iltimos inet yoqing", Toast.LENGTH_SHORT).show()

    }

    private fun compareCountryNames(inputName: String) {
        mBinding.animQuiz.isVisible = true
        if (inputName == country!!.name) {
            mBinding.animQuiz.setAnimation(R.raw.anim_con)
            mBinding.animQuiz.playAnimation()
            getRandomCountry()
        } else {
            mBinding.animQuiz.setAnimation(R.raw.wrong)
            mBinding.animQuiz.playAnimation()
            getRandomCountry()
        }
    }
}