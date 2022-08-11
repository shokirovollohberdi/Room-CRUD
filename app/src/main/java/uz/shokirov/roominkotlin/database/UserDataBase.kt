package uz.shokirov.roominkotlin.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import uz.shokirov.roominkotlin.dao.UserDao
import uz.shokirov.roominkotlin.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDataBase() : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDataBase? = null

        fun getDatabase(context: Context):UserDataBase{
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDataBase::class.java,
                    "user_database"
                ).build()

                INSTANCE = instance


                return instance
            }

        }
    }
}