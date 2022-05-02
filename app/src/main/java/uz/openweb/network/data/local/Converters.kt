package uz.openweb.network.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.openweb.network.data.entities.Flag

class Converters {
    @TypeConverter
    fun toFlag(value: String): Flag = Flag(value)

    @TypeConverter
    fun fromFlag(flag: Flag): String = flag.png


    @TypeConverter
    fun fromStingsList(list: List<String>?): String {
        return if (list != null)
            Gson().toJson(list)
        else "empty"
    }

    @TypeConverter
    fun toStringsList(value: String): List<String> {
        return if (value != "empty")
            Gson().fromJson(value, object : TypeToken<List<String>>() {}.type)
        else listOf<String>()
    }

}