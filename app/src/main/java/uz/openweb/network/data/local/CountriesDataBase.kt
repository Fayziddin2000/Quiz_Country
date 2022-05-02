package uz.openweb.network.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uz.openweb.network.data.entities.Country

@Database(entities = [Country::class], version = 4)
@TypeConverters(Converters::class)

abstract class CountriesDataBase : RoomDatabase() {

    abstract fun countriesDao(): CountriesDao

    companion object {
        @Volatile
        private var instance: CountriesDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): CountriesDataBase = instance ?: synchronized(LOCK) {
            instance ?: createDataBase(context).also { instance = it }
        }

        private fun createDataBase(context: Context): CountriesDataBase = Room.databaseBuilder(
            context.applicationContext,
            CountriesDataBase::class.java,
            "countries-database")
            .fallbackToDestructiveMigration()
            .build()

    }

}