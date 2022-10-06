package com.example.alihabibandroidexam


import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import java.util.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewPager: ViewPager = findViewById(R.id.viewPager)
        val adapter: ImageAdapter = ImageAdapter(this)
        viewPager.adapter = adapter
        val sliderDotspanel: LinearLayout = findViewById(R.id.SliderDots)
        val dotscount = adapter.count
        val dots: Array<ImageView?> = arrayOfNulls<ImageView>(dotscount)

        for (i in 0 until dotscount) {
            dots.set(i, ImageView(this))
            dots[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.non_active_dot
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            sliderDotspanel!!.addView(dots[i], params)
        }
        dots[0]?.setImageDrawable(
            ContextCompat.getDrawable(
                getApplicationContext(),
                R.drawable.active_dot
            )
        )
        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                for (i in 0 until dotscount) {
                    dots[i]?.setImageDrawable(
                        ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.non_active_dot
                        )
                    )
                }
                dots[position]?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.active_dot
                    )
                )
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })



        val foodItems: List<FoodItem> = listOf<FoodItem>(FoodItem( "Burger",R.drawable.burger),
            FoodItem( "Pizza",R.drawable.pizza),
            FoodItem( "Pasta",R.drawable.pasta))
        val listView: ListView = findViewById(R.id.listview)
        var customList = CustomList(this,  foodItems)
        listView.adapter = customList

        val that = this
        val searchBar : SearchView =  findViewById(R.id.action_search)
        searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                customList.filter.filter(query)

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

               customList.filter.filter(newText)
                return false
            }
        })

    }

}