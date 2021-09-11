package com.godgod.tosstranstion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.godgod.tosstranstion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupBottomNavEvent()
    }

    private fun setupBottomNavEvent() {
        binding.btBottomSheet.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_first -> showFirstFragment()
                R.id.menu_second -> showSecondFragment()
                R.id.menu_third -> showThirdFragment()
            }
            true
        }
        binding.btBottomSheet.selectedItemId = R.id.menu_first
    }

    private fun showFirstFragment() {
        val fragment = supportFragmentManager.findFragmentByTag(FirstFragment.TAG)
        showFragment(fragment ?: FirstFragment())
    }

    private fun showSecondFragment() {
        val fragment = supportFragmentManager.findFragmentByTag(SecondFragment.TAG)
        showFragment(fragment ?: SecondFragment())
    }

    private fun showThirdFragment() {
        val fragment = supportFragmentManager.findFragmentByTag(ThirdFragment.TAG)
        showFragment(fragment ?: ThirdFragment())
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fcvContainer.id, fragment)
            .commit()
    }
}