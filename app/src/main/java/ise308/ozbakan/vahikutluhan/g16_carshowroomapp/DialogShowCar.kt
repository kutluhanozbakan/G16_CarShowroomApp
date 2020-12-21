package ise308.ozbakan.vahikutluhan.g16_carshowroomapp

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


class DialogShowCar : DialogFragment() {

    private var car: Car? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(this.activity!!)
        val inflater = activity!!.layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_show_car, null)

        val textViewCarBrand = dialogLayout.findViewById<TextView>(R.id.CarBrand)
        val textViewCarType = dialogLayout.findViewById<TextView>(R.id.CarType)
        val textViewCarCost = dialogLayout.findViewById<TextView>(R.id.CarCost)
        val textViewCarDate = dialogLayout.findViewById<TextView>(R.id.CarDate)
        val textViewSale = dialogLayout.findViewById<TextView>(R.id.textView_Sale)
        val textViewRent = dialogLayout.findViewById<TextView>(R.id.textView_Rent)
        val buttonDelete = dialogLayout.findViewById<Button>(R.id.buttonDelete)
        val buttonEdit = dialogLayout.findViewById<Button>(R.id.buttonEdit)
        textViewCarBrand.text = car!!.brand

        textViewCarType.text =
                car!!.cartype


        textViewCarCost.text = car!!.carprice.toString()
        textViewCarDate.text = car!!.cardate.toString()

        if (!car!!.sale) {
            textViewSale.visibility = View.GONE
        }
        if (!car!!.rent) {
            textViewRent.visibility = View.GONE
        }


        buttonEdit.setOnClickListener {
            //operations for editing, might be more complicated though.
        }
        builder.setView(dialogLayout)
                .setMessage("Your Car")
        return builder.create()




    }


    fun sendCarSelected(carSelected: Car) {

        car = carSelected
    }
}
