package ise308.ozbakan.vahikutluhan.g16_carshowroomapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import java.lang.Exception

private const val TAG = "CarPagerActivity"
private var carList: ArrayList<Car>? = null
private var jsonSerializer: JSONSerializer? = null

class CarPagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstances: Bundle?) {
        super.onCreate(savedInstances)
        setContentView(R.layout.activity_car_pager)
        jsonSerializer = JSONSerializer("Car Showroom", applicationContext)
        try {
            carList = jsonSerializer!!.load()
        } catch (e: Exception) {
            carList = ArrayList()
            Log.e(TAG, "Error Loading cars o.o")
        }

        //create list of fragments, one fragment for each car.
        var carFragmentList = java.util.ArrayList<Fragment>()
        for (car in carList!!) {
            carFragmentList.add(ShowCarFragment.newInstance(car))
        }
        val carPageAdapter = CarPagerAdapter(supportFragmentManager, carFragmentList)
        findViewById<ViewPager>(R.id.pager_cars).adapter = carPageAdapter


    }


    class CarPagerAdapter(fm: FragmentManager, private val carFragmentList: ArrayList<Fragment>) :
        FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int) = carFragmentList[position]

        override fun getCount() = carFragmentList.size

    }
}