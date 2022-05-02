package uz.openweb.network.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import uz.openweb.network.R
import uz.openweb.network.databinding.FragmentEndQuizBinding

class EndQuizFragment : Fragment() {
    private lateinit var _binding: FragmentEndQuizBinding
    private val mBinding get() = _binding

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEndQuizBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        mBinding.btnRestart.setOnClickListener {
            viewModel.setDefaultValues()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, QuizFragment(), QuizFragment::javaClass.name)
                .commit()
        }
        mBinding.btnExit.setOnClickListener {
            viewModel.setDefaultValues()
//            mBinding.btnRestart.setOnClickListener {
//                requireActivity().supportFragmentManager.beginTransaction()
//                    .replace(R.id.main_container, HomeFragment(), HomeFragment::javaClass.name)
//                    .commit()
//            }
            requireActivity().onBackPressed()
        }
        viewModel.mRightAnswerCount.observe(viewLifecycleOwner) { rightAnswersCount ->
            mBinding.tvResult.text =
                getString(R.string.text_true, rightAnswersCount.toString())
        }
    }
}