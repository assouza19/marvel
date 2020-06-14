package com.example.marvelsworld.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelsworld.R
import com.example.marvelsworld.adapter.MainPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.marvelsworld.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var pagerAdapter: MainPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = MainViewModel()
        pagerAdapter = MainPagerAdapter(supportFragmentManager)

        view_pager.offscreenPageLimit = 4
        view_pager.adapter = pagerAdapter

        bindViews()
        setupViewPager()
    }


    private fun bindViews() {
        bottom_navigation_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun setupViewPager() {

        val firstFragment = HomeFragment.newInstance()
//        val secondFragment = SearchFragment.newInstance()
//        val thirstFragment =  MapsFragment.newInstance()
        pagerAdapter.addFragment(firstFragment, "Home")
//        pagerAdapter.addFragment(secondFragment, "Descobrir")
//        pagerAdapter.addFragment(thirstFragment, "Favoritos")

        view_pager.adapter = pagerAdapter
        view_pager.currentItem = 0
        toolbar.toolbarTitle.text = getString(R.string.title)

    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_item_home -> {
                    view_pager.currentItem = 0
                    toolbar.toolbarTitle.text = getString(R.string.title)
                }
            }
            false
        }
}
