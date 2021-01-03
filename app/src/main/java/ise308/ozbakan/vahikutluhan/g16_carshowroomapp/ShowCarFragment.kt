package ise308.ozbakan.vahikutluhan.g16_carshowroomapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2

class ShowCarFragment : Fragment() {
    private lateinit var btnDelete: Button
    private lateinit var btnEdit: Button
    private var jsonSerializer: JSONSerializer? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        jsonSerializer = JSONSerializer("Car Showroom", requireActivity().applicationContext)
        Log.e("car", deletedCar.brand.toString())
        btnDelete.setOnClickListener {

            val dialogBuilder = AlertDialog.Builder(view.context)
            dialogBuilder.setTitle("Are You Sure?")
            dialogBuilder.setPositiveButton("Delete") { _: DialogInterface, _: Int ->
                deletedCar?.let { car ->
                    jsonSerializer!!.delete(car)
                }

                val callingActivity = activity as MainActivity?
                callingActivity!!.deleteCar()
            }
            dialogBuilder.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->
                Toast.makeText(this.context, "Not Deleted!", Toast.LENGTH_LONG).show()
            }
            val b = dialogBuilder.create()
            b.show()


        }
        btnEdit.setOnClickListener{

            val showDialog = EditFragment()
            showDialog.show(activity!!.supportFragmentManager, " ")

        }


    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.car_frame, container, false)
        val tvBrand = view.findViewById<TextView>(R.id.CarBrand)
        val tvType = view.findViewById<TextView>(R.id.CarType)
        val tvPrice = view.findViewById<TextView>(R.id.CarCost)
        val tvDate = view.findViewById<TextView>(R.id.CarDate)
        val tvRent = view.findViewById<TextView>(R.id.textView_Rent)
        val tvSale = view.findViewById<TextView>(R.id.textView_Sale)
        btnDelete = view.findViewById<Button>(R.id.buttonDelete)
        btnEdit = view.findViewById<Button>(R.id.buttonEdit)

        //assign value by bundle
        tvBrand.text = arguments!!.getString("Brand")
        tvType.text = arguments!!.getString("Type")

        tvPrice.text = arguments!!.getDouble("Price").toString()
        tvDate.text = arguments!!.getInt("Date").toString()

        if (!arguments!!.getBoolean("Rent")) {
            tvRent.visibility = View.GONE
        }
        if (!arguments!!.getBoolean("Sale")) {
            tvSale.visibility = View.GONE
        }

        return view
    }

    companion object {
        private lateinit var deletedCar: Car

        fun newInstance(car: Car): ShowCarFragment {
            val fragment = ShowCarFragment()
            val bundle = Bundle(1)
            bundle.putString("Brand", car.brand)
            bundle.putString("Type", car.cartype)
            car.carprice?.let { bundle.putDouble("Price", it) }
            car.cardate?.let { bundle.putInt("Date", it) }
            bundle.putBoolean("Sale", car.sale)
            bundle.putBoolean("Rent", car.rent)
            fragment.arguments = bundle
            deletedCar = car
            return fragment

        }
    }
}