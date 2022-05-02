package uz.openweb.network.ui

import android.animation.Animator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.openweb.network.R
import uz.openweb.network.databinding.FragmentStartQuizBinding

class StartQuizFragment : Fragment(), Animator.AnimatorListener {
    private lateinit var _binding: FragmentStartQuizBinding
    private val mBinding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartQuizBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onAnimationStart(p0: Animator?) {

    }

    override fun onAnimationEnd(p0: Animator?) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, QuizFragment(), QuizFragment::javaClass.name)
            .commit()
    }

    override fun onAnimationCancel(p0: Animator?) {

    }

    override fun onAnimationRepeat(p0: Animator?) {

    }
}