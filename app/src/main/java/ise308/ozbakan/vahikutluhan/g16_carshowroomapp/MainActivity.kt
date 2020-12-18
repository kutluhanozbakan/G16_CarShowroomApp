package ise308.ozbakan.vahikutluhan.g16_carshowroomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private var carList: ArrayList<Car>? = null
    private var adapter: CarAdapter? = null
    private var recyclerView: RecyclerView? = null
    private var jsonSerializer: JSONSerializer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fabNewCar = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        fabNewCar.setOnClickListener{
            val dialog = DialogNewCar()
            dialog.show(supportFragmentManager, "124")
        }
        jsonSerializer = JSONSerializer("Car Showroom",applicationContext)
        try
        {
            carList = jsonSerializer!!.load()
        }catch (e: Exception)
        {
            carList = ArrayList()
        }
        recyclerView = findViewById<View>(R.id.recyclerView)
                as RecyclerView
        adapter = CarAdapter(this,carList!!)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recyclerView!!.adapter = adapter

    }

    fun createNewCar(n: Car)
    {
        carList!!.add(n)
        adapter!!.notifyDataSetChanged()
    }
}