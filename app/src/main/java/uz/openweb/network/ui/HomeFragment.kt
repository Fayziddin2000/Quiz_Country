package uz.openweb.network.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.openweb.network.R
import uz.openweb.network.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var _binding: FragmentHomeBinding
    private val mBinding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mBinding.btnExit.setOnClickListener { requireActivity().finish() }
        mBinding.btnSearchFragment.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, SearchCountryFragment(), QuizFragment::javaClass.name)
                .commit()
        }
        mBinding.btnQuizFragment.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, QuizFragment(), QuizFragment::javaClass.name)
                .addToBackStack(QuizFragment::javaClass.name).commit()
        }


    }

}