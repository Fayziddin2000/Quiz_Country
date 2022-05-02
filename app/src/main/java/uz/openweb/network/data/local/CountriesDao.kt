package uz.openweb.network.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.openweb.network.data.entities.Country

@Dao
interface CountriesDao {
    @Query("SELECT * FROM country")
    fun getCountries(): List<Country>

    @Query("DELETE FROM country")
    fun deleteData()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(countries : List<Country>)
}