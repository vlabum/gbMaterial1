package ru.vlabum.android.gb.material1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.ToxicBakery.viewpager.transforms.AccordionTransformer
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main2.*
import me.relex.circleindicator.CircleIndicator

class Main2Activity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var circleIndicator: CircleIndicator
    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)

        viewPager = findViewById(R.id.pager)
        val pAdapter = MyPagerAdapter(supportFragmentManager)
        pAdapter.addFragment(UsersFragment(), "All")
        pAdapter.addFragment(UsersFragment(true), "Friends")
        viewPager.adapter = pAdapter

        circleIndicator = findViewById(R.id.circle_indicator)
        circleIndicator.setViewPager(viewPager)

        tabLayout = findViewById(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)

        viewPager.setPageTransformer(true, AccordionTransformer())

        fabLogin.setOnClickListener {
            login()
        }

    }

    private fun login() {

    }

}
