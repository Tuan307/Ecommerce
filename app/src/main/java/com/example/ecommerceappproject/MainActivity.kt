package com.example.ecommerceappproject

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.ecommerceappproject.databinding.ActivityMainBinding
import com.example.ecommerceappproject.ui.main.PagerFragmentAdapter
import com.example.ecommerceappproject.ui.main.home.HomeFragment
import com.example.ecommerceappproject.ui.main.order.MyOrderFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mPagerAdapter: PagerFragmentAdapter
    private val homeFragment = HomeFragment.newInstance()
    private val orderFragment = MyOrderFragment.newInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        binding.bottomNav.setOnNavigationItemSelectedListener(this@MainActivity)
        mPagerAdapter = PagerFragmentAdapter(supportFragmentManager, this@MainActivity.lifecycle)
        mPagerAdapter.addFragment(homeFragment)
        mPagerAdapter.addFragment(orderFragment)
        binding.viewContainer.adapter = mPagerAdapter
        binding.viewContainer.isUserInputEnabled = false
        binding.viewContainer.offscreenPageLimit = mPagerAdapter.itemCount
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                binding.viewContainer.currentItem = 0
                return true
            }
            R.id.navigation_category -> {
                binding.viewContainer.currentItem = 2
                return true
            }
        }
        return false
    }
}