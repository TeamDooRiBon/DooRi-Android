package kr.co.dooribon.ui.existingtrip.board

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.dooribon.databinding.FragmentBoardBinding
import kr.co.dooribon.ui.existingtrip.board.extension.initializeBoardTabNavigation
import kr.co.dooribon.ui.existingtrip.extension.initializeTab
import kr.co.dooribon.utils.AutoClearBinding

class BoardFragment : Fragment() {

    private var binding by AutoClearBinding<FragmentBoardBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentBoardBinding.inflate(layoutInflater, container, false)
        .also { FragmentBoardBinding ->
            binding = FragmentBoardBinding
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tabBoard.initializeTab(listOf("소통", "체크리스트"))
        binding.tabBoard.initializeBoardTabNavigation(childFragmentManager)
    }
}