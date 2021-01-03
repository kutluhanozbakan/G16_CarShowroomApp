package ise308.ozbakan.vahikutluhan.g16_carshowroomapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CarAdapter(private val mainActivity: MainActivity, private val carList: List<Car>) : RecyclerView.Adapter<CarAdapter.ListItemHolder>() {
    inner class ListItemHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        internal var brand = view.findViewById<View>(R.id.TextView_carBrand) as TextView
        internal var cartype = view.findViewById<View>(R.id.textView_carType) as TextView
        internal var carcost = view.findViewById<View>(R.id.textView_carCost) as TextView
        internal var cardate = view.findViewById<View>(R.id.textView_carDate) as TextView
        internal var carstatus = view.findViewById<View>(R.id.TextView_carStatus) as TextView

        init { //make it clickable
            view.isClickable = true
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
           // mainActivity.showCar(adapterPosition)
            val intentToCarPager = Intent(itemView!!.context,CarPagerActivity::class.java)
            itemView.context.startActivity(intentToCarPager)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarAdapter.ListItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listcars, parent, false)
        return ListItemHolder(itemView)
    }

    override fun getItemCount(): Int {
        if (carList != null) {
            return carList.size
        }
        return -1
    }

    override fun onBindViewHolder(holder: CarAdapter.ListItemHolder, position: Int) {
        val cars = carList[position]
        holder.brand.text = cars.brand
        holder.cartype.text = cars.cartype
        holder.carcost.text = cars.carprice.toString()
        holder.cardate.text = cars.cardate.toString()

        when {
            cars.rent -> holder.carstatus.text = "rent"
            cars.sale -> holder.carstatus.text = "sale"
        }

    }
}