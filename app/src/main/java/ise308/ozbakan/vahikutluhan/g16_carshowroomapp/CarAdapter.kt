package ise308.ozbakan.vahikutluhan.g16_carshowroomapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView

class CarAdapter(private val mainActivity: MainActivity, private val carList: List<Car>): RecyclerView.Adapter<CarAdapter.ListItemHolder>() {
    inner class ListItemHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener
    {
        internal var brand = view.findViewById<View>(R.id.TextView_carbrand) as TextView
        internal var cartype = view.findViewById<View>(R.id.textView_cartype) as TextView
        internal var carcost = view.findViewById<View>(R.id.textView_carcost) as TextView
        internal var cardate = view.findViewById<View>(R.id.textView_cardate) as TextView
        internal var carstatus = view.findViewById<View>(R.id.TextView_carstatus) as TextView

        override fun onClick(v: View?) {
            //Adding Showing Cars (Adapter position)
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarAdapter.ListItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listcars, parent , false)
        return ListItemHolder(itemView)
    }

    override fun getItemCount(): Int {
        if (carList!=null)
        {
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

        when{
            cars.rent -> holder.carstatus.text = "rent"
            cars.sale -> holder.carstatus.text = "sale"
        }

    }
}