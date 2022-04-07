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
import kotlin.random.Random

class QuizFragment : Fragment() {

    private lateinit var _binding: FragmentQuizBinding
    private val mBinding get() = _binding
    private var country: Country? = null
    private var wrong1: Country? = null
    private var wrong2: Country? = null
    private var wrong3: Country? = null
    private var numb: Int? = 1

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
        mBinding.animQuiz.addAnimatorListener(object : Animator.AnimatorListener {
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
        mBinding.btnAnswer1.setOnClickListener { compareCountryNames(mBinding.btnAnswer1.text.toString()) }
        mBinding.btnAnswer2.setOnClickListener { compareCountryNames(mBinding.btnAnswer2.text.toString()) }
        mBinding.btnAnswer3.setOnClickListener { compareCountryNames(mBinding.btnAnswer3.text.toString()) }
        mBinding.btnAnswer4.setOnClickListener { compareCountryNames(mBinding.btnAnswer4.text.toString()) }
        mBinding.btnNext.setOnClickListener { getRandomCountry() }
        mBinding.tvQuiz.text = getString(R.string.text_quiz, numb!!.toString())
        numb = numb!! + 1
    }

    private fun getRandomCountry() {
        if (numb!! > 20) {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, EndQuizFragment(), EndQuizFragment::javaClass.name).commit()
        }else {
            val connectivityManager: ConnectivityManager =
                requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager.isActiveNetworkMetered)
                lifecycleScope.launch {
                    val countries = countriesApi.getCountry()
                    country = countries[(0..countries.size).shuffled()[0]]
                    wrong1 = countries[(0..countries.size).shuffled()[0]]
                    wrong2 = countries[(0..countries.size).shuffled()[0]]
                    wrong3 = countries[(0..countries.size).shuffled()[0]]
                    initView()
                    answerRandom()
                }
            else Toast.makeText(requireContext(), "Iltimos iternetni yoqing!", Toast.LENGTH_SHORT)
                .show()

        }
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

    private fun answerRandom() {
        when (Random.nextInt(0, 3)) {
            0 -> {
                mBinding.btnAnswer1.text = country!!.name
                if (wrong1 != country || wrong2 != country || wrong3 != country) {
                    mBinding.btnAnswer2.text = wrong1!!.name
                    mBinding.btnAnswer3.text = wrong2!!.name
                    mBinding.btnAnswer4.text = wrong3!!.name
                } else {
                    getRandomCountry()
                }
            }
            1 -> {
                mBinding.btnAnswer2.text = country!!.name
                if (wrong1 != country || wrong2 != country || wrong3 != country) {
                    mBinding.btnAnswer1.text = wrong1!!.name
                    mBinding.btnAnswer3.text = wrong2!!.name
                    mBinding.btnAnswer4.text = wrong3!!.name
                } else {
                    getRandomCountry()
                }
            }
            2 -> {
                mBinding.btnAnswer3.text = country!!.name
                if (wrong1 != country || wrong2 != country || wrong3 != country) {
                    mBinding.btnAnswer1.text = wrong1!!.name
                    mBinding.btnAnswer2.text = wrong2!!.name
                    mBinding.btnAnswer4.text = wrong3!!.name
                } else {
                    getRandomCountry()
                }
            }
            3 -> {
                mBinding.btnAnswer4.text = country!!.name
                if (wrong1 != country || wrong2 != country || wrong3 != country) {
                    mBinding.btnAnswer1.text = wrong1!!.name
                    mBinding.btnAnswer2.text = wrong2!!.name
                    mBinding.btnAnswer3.text = wrong3!!.name
                } else {
                    getRandomCountry()
                }
            }

        }

    }

}
