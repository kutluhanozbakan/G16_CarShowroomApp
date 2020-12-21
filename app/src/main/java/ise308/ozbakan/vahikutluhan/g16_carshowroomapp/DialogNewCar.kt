package ise308.ozbakan.vahikutluhan.g16_carshowroomapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import kotlin.text.toInt as toInt

class DialogNewCar: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)
        val inflater = activity!!.layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_new_car, null)
        val editCarbrand = dialogLayout.findViewById<EditText>(R.id.CarBrand)
        val editCarType = dialogLayout.findViewById<EditText>(R.id.CarType)
        val editCarCost = dialogLayout.findViewById<EditText>(R.id.CarCost)
        val editCarDate = dialogLayout.findViewById<EditText>(R.id.CarDate)
        val checkBoxRent = dialogLayout.findViewById<CheckBox>(R.id.checkBoxRent)
        val checkBoxSale = dialogLayout.findViewById<CheckBox>(R.id.checkBoxSale)
        val buttonOk = dialogLayout.findViewById<Button>(R.id.buttonDone)
        val buttonCancel = dialogLayout.findViewById<Button>(R.id.buttonCancel)

        builder.setView(dialogLayout)
            .setMessage("Add a new car")
        buttonCancel.setOnClickListener{
            dismiss()
        }
        buttonOk.setOnClickListener{
            val newCar = Car()
            newCar.brand = editCarbrand.text.toString()
            newCar.cartype = editCarType.text.toString()
            newCar.carprice = editCarCost.text.toString().toDouble()
            newCar.cardate = editCarDate.text.toString().toInt()
            newCar.rent = checkBoxRent.isChecked
            newCar.sale = checkBoxSale.isChecked

            val callingActivity = activity as MainActivity?
            callingActivity!!.createNewCar(newCar)

            dismiss()
        }

        return builder.create()
    }
}