package uz.openweb.network.data.entities

import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.INTEGER
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import uz.openweb.network.data.local.Converters

@Entity
data class Country(
    @PrimaryKey
    val name: String,
    @TypeConverters(Converters::class)
    val flags: Flag,
    val capital: String?,
    val callingCodes: List<String?>,
    val borders: List<String?>,
    val population: Int,
    val area: Double

    // Embedded, Ignore,

)

data class Flag(val png: String)