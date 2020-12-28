package ise308.ozbakan.vahikutluhan.g16_carshowroomapp

import android.content.Context
import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONTokener
import java.io.*
import java.lang.StringBuilder

class JSONSerializer(private val filename : String, private val context: Context) {
    var cars = ArrayList<Car>()

    @Throws(IOException::class, JSONException::class)
    fun save(carList: List<Car>) {
        val jsonArray = JSONArray()
        for (car in carList) {
            jsonArray.put(car.convertTOJSON())
        }
        var writer: Writer? = null
        try {
            val outFile = context.openFileOutput(filename, Context.MODE_PRIVATE)
            writer = OutputStreamWriter(outFile)
            writer.write(jsonArray.toString())
        } finally {
            if (writer != null) {
                writer.close()
            }
        }

    }
    //this function is load the current json folder and show the data.
    @Throws(IOException::class, JSONException::class)
    fun load(): ArrayList<Car> {
        val carList = ArrayList<Car>()
        var reader: BufferedReader? = null

        try {
            val inFile = context.openFileInput(filename)
            reader = BufferedReader(InputStreamReader(inFile))
            val jsonString = StringBuilder()

            for (line in reader.readLine()) {
                jsonString.append(line)
            }

            val jsonArray = JSONTokener(jsonString.toString())
                    .nextValue() as JSONArray
            for (i in 0 until jsonArray.length()) {
                carList.add(Car(jsonArray.getJSONObject(i)))
            }

        } catch (e: FileNotFoundException) {
            //we will ignore this one, since it happens
            // when we start fresh. Yo
        } finally {
            reader!!.close()
        }
        cars = carList
        return carList
    }

    fun delete(car: Car){
        load()
        for (i in 0 until cars.size){
            if (car.carprice == cars[i].carprice){
                cars.removeAt(i)
            }
        }
        Log.e("Size",cars.size.toString())
        save(cars)
    }
}