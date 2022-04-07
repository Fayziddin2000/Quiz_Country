package uz.openweb.network

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.openweb.network.databinding.FragmentStartQuizBinding

class StartQuizFragment : Fragment() {
    private lateinit var _binding: FragmentStartQuizBinding
    private val mBinding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartQuizBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }
}