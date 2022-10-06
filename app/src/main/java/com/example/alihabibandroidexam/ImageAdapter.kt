package com.example.alihabibandroidexam
import android.content.Context
import android.view.View;
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

public class ImageAdapter(context: Context) : PagerAdapter() {
    private val mContext: Context = context

    private  val mImageIds: IntArray = intArrayOf(R.drawable.burger, R.drawable.pasta, R.drawable.pizza)


    override fun getCount(): Int {
       return mImageIds.count()
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView: ImageView = ImageView(mContext)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setImageResource(mImageIds[position])
        container.addView(imageView, 0)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ImageView)
    }
}