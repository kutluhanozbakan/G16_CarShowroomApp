package ise308.ozbakan.vahikutluhan.g16_carshowroomapp

import org.json.JSONException
import org.json.JSONObject

private val JSON_CARBRAND = "brand"
private val JSON_CARTYPE = "cartype"
private val JSON_CARPRICE = "carprice"
private val JSON_CARDATE = "cardate"
private val JSON_RENT = "rent"
private val JSON_SALE = "sale"
class Car
{
    constructor(){}
    var brand: String? = null
    var cartype: String? = null
    var carprice: Double? = null
    var cardate: Int? = null
    var rent: Boolean = false
    var sale: Boolean = false

    @Throws(JSONException:: class)
    constructor(jsonObject: JSONObject)
    {
        brand = jsonObject.getString(JSON_CARBRAND)
        cartype = jsonObject.getString(JSON_CARTYPE)
        carprice = jsonObject.getDouble(JSON_CARPRICE)
        cardate = jsonObject.getInt(JSON_CARDATE)
        rent = jsonObject.getBoolean(JSON_RENT)
        sale = jsonObject.getBoolean(JSON_SALE)
    }
    @Throws(JSONException::class)
    fun convertTOJSON(): JSONObject
    {
        val jsonObject = JSONObject()
        jsonObject.put(JSON_CARBRAND, brand)
        jsonObject.put(JSON_CARTYPE, cartype)
        jsonObject.put(JSON_CARPRICE, carprice)
        jsonObject.put(JSON_CARDATE,cardate)
        jsonObject.put(JSON_RENT, rent)
        jsonObject.put(JSON_SALE, sale)
        return jsonObject
    }

}