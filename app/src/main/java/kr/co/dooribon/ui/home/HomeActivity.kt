package kr.co.dooribon.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityHomeBinding
import kr.co.dooribon.utils.StatusBarUtil

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this

        StatusBarUtil.changeColor(this,ContextCompat.getColor(this,
            R.color.doo_ri_bon_home_tool_bar_blue_color
        ))
    }
}