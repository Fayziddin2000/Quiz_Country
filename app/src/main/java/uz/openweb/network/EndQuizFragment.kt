package uz.openweb.network

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.openweb.network.databinding.FragmentEndQuizBinding

class EndQuizFragment : Fragment() {
    private lateinit var _binding: FragmentEndQuizBinding
    private val mBinding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEndQuizBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.btnRestart.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, QuizFragment(), QuizFragment::javaClass.name)
                .commit()
        }
        mBinding.btnExit.setOnClickListener {
            mBinding.btnRestart.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, HomeFragment(), HomeFragment::javaClass.name)
                    .commit()
            }
        }
    }
}