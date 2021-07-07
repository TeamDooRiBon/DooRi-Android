package kr.co.dooribon.ui.existingtrip.tendency.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.dooribon.databinding.FragmentDetailBinding
import kr.co.dooribon.utils.AutoClearBinding

class DetailFragment : Fragment() {

    private var binding by AutoClearBinding<FragmentDetailBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDetailBinding.inflate(inflater,container,false).also { FragmentDetailBinding ->
        binding = FragmentDetailBinding
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}