package com.rajpurohit.neostoreapp.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rajpurohit.neostoreapp.model.LoginModel
import com.rajpurohit.neostoreapp.network.ApiService
import com.rajpurohit.neostoreapp.rmdatabase.User
import com.rajpurohit.neostoreapp.rmdatabase.UserDao
import com.rajpurohit.neostoreapp.rmdatabase.UserDataBase
import com.rajpurohit.neostoreapp.viewactivity.HomeActivity
import com.rajpurohit.neostoreapp.viewactivity.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewmodel(application: Application) :AndroidViewModel(application) {
    private var db = UserDataBase.getDatabase(application).getUserDao()
    fun getuserlist(): User = db.getAllUser()
    suspend fun insertuser(user: User) {
        db.insert(user)
    }

  private  var loginLiveData : MutableLiveData<LoginModel> = MutableLiveData()
    /*init {
        loginLiveData = MutableLiveData()
    }*/
    fun getLoginLivedata(): MutableLiveData<LoginModel> = loginLiveData


    fun loginUser( context: Context,userEmail: String,userPassword:String) {
      // Toast.makeText(getApplication(), "$userEmail $userPassword ", Toast.LENGTH_SHORT).show()

            val loginData = ApiService.registerUserInfo().loginUserData(userEmail, userPassword)
            loginData.enqueue(object : Callback<LoginModel> {
                override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {


                   if (response.isSuccessful) {
                       loginLiveData.value = response.body()
                       Toast.makeText(getApplication(), "${response.body()?.userMsg}", Toast.LENGTH_SHORT).show()
                     /* val   intent = Intent(getApplication(), HomeActivity::class.java)
                        intent.putExtra("Access_TokenLogin", response.body()!!.data?.accessToken)
                       context.startActivity(intent)*/
                    }else {
                       Log.d("annu", "response fail")
                       try {
                           Log.d("annu", "try block")
                           val jObjError = JSONObject(response.errorBody()!!.string())
                           Log.e("annu", jObjError.getString("user_msg"))
                           Toast.makeText(context, jObjError.getString("user_msg"), Toast.LENGTH_SHORT)
                               .show()

                       } catch (e: Exception) {
                           Log.d("annu", "catch block")
                           Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                       }
                   }
                }

                override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                   Toast.makeText(getApplication(), "something Wrong in login $t", Toast.LENGTH_SHORT).show()
                }
            })

    }
}