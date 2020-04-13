package info.chorimeb.mobileledgerapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import info.chorimeb.mobileledgerapp.data.model.User

@Dao
interface UserDao {

    //login
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)

    //resume
    @Query("select * from Users where email = :email")
    fun getUser(email: String): LiveData<User>

    //logout
    @Query("delete from Users where email = :email")
    fun remove(email: String)

}