package com.rajpurohit.neostoreapp.viewactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.rajpurohit.neostoreapp.R
import com.rajpurohit.neostoreapp.databinding.ActivityRegisterBinding
import com.rajpurohit.neostoreapp.model.LoginModel
import com.rajpurohit.neostoreapp.network.ApiService
import com.rajpurohit.neostoreapp.rmdatabase.User
import com.rajpurohit.neostoreapp.rmdatabase.UserDataBase
import com.rajpurohit.neostoreapp.viewmodel.LoginViewmodel
import com.rajpurohit.neostoreapp.viewmodel.RegisterViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    lateinit var bindingRegister:ActivityRegisterBinding
    lateinit var gendr:String
   /* lateinit var firstname: EditText
    lateinit var lastname: EditText
    lateinit var emailz: EditText
    lateinit var password: EditText
    lateinit var cnfrmpass: EditText
    lateinit var phoneno: EditText
    lateinit var registerbtn: Button
    lateinit var maler: RadioButton
    lateinit var femaler: RadioButton
    lateinit var radgrp: RadioGroup
    lateinit var invtext: TextView
    lateinit var gendr:String*/
    val emailpattern:String="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      /*  setContentView(R.layout.activity_register)
      firstname=findViewById(R.id.firstname)
        lastname=findViewById(R.id.lastname)
        emailz=findViewById(R.id.email)
        password=findViewById(R.id.password)
        cnfrmpass=findViewById(R.id.confrmpasswrd)
        phoneno=findViewById(R.id.phonenumber)
        registerbtn=findViewById(R.id.button)
        maler=findViewById(R.id.male)
        femaler=findViewById(R.id.female)
        radgrp=findViewById(R.id.radioGroup)
        invtext=findViewById(R.id.inv)*/

        //DataBinding
        bindingRegister = androidx.databinding.DataBindingUtil.setContentView(this,R.layout.activity_register)
        val firstName=  bindingRegister.firstname.text
        val lastName=  bindingRegister.lastname.text
        val Email=  bindingRegister.email.text
        val phoneNo=  bindingRegister.phonenumber.text
        val passWord=  bindingRegister.password.text
        val confirmPass=  bindingRegister.confrmpasswrd.text

            //ViewModel
     val viewModelRegister =ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(RegisterViewModel::class.java)
        viewModelRegister.getregisterLiveData().observe(this,{
            if (it != null){
            intent = Intent(this@RegisterActivity,LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            }
        })



      bindingRegister.registerbutton.setOnClickListener {
            val selectgendr:Int=bindingRegister.radioGroup.checkedRadioButtonId
            class Radgen : CompoundButton.OnCheckedChangeListener{
                override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                    if(bindingRegister.male.isChecked)
                    {
                        bindingRegister.inv.visibility= View.INVISIBLE
                        gendr=bindingRegister.male.text.toString().substring(0, 1)

                    }
                    else if(bindingRegister.female.isChecked)
                    {
                        bindingRegister.inv.visibility= View.INVISIBLE
                        gendr=bindingRegister.female.text.toString().substring(0, 1)

                    }
                }
            }

            if(firstName.isEmpty()||lastName.isEmpty()||
                    Email.isEmpty()||passWord.isEmpty()||
                    confirmPass.isEmpty()||phoneNo.isEmpty()||
                !Pattern.matches("[a-zA-Z ]+", firstName)||
                !Pattern.matches("[a-zA-Z ]+", lastName)||
                    phoneNo.length<10||!Pattern.matches(emailpattern, Email)||
                    passWord.length in 1..5 ||confirmPass.length in 1..5)
            {

                if(firstName.isEmpty())
                {
                    bindingRegister.firstname.requestFocus()
                    bindingRegister.firstname.error="please Enter First Name"
                }
                else if(!Pattern.matches("[a-zA-Z ]+", firstName))
                        {
                           bindingRegister.firstname.requestFocus()
                            bindingRegister.firstname.error="Please Enter Alphabets"
                        }

                    if(lastName.isEmpty())
                {
                   bindingRegister.lastname.requestFocus()
                    bindingRegister.lastname.error="Please Enter LastName"
                }
                else if(!Pattern.matches("[a-zA-Z ]+", lastName))
                {
                   bindingRegister.lastname.requestFocus()
                   bindingRegister.lastname.error="Please Enter Alphabets"
                }

              if(selectgendr==-1)
                {
                   bindingRegister.inv.visibility= View.VISIBLE
                    bindingRegister.male.setOnCheckedChangeListener(Radgen())
                    bindingRegister.female.setOnCheckedChangeListener(Radgen())
                }

                if(phoneNo.isEmpty())
                {
                  bindingRegister.phonenumber.requestFocus()
                  bindingRegister.phonenumber.error="Please Enter Phone Number"

                }
                else if(phoneNo.length<10)
                {
                   bindingRegister.phonenumber.requestFocus()
                   bindingRegister.phonenumber.error="Enter Valid Phone Number"
                }

                if(Email.isEmpty())
                {
                   bindingRegister.email.requestFocus()
                   bindingRegister.email.error="Please Enter Email ID"
                }
                else if(!Pattern.matches(emailpattern, Email))
                {
                    bindingRegister.email.requestFocus()
                    bindingRegister.email.error="Please Enter Valid Email ID"
                }

                if(passWord.isEmpty())
                {
                   bindingRegister.password.requestFocus()
                   bindingRegister.password.error="Enter Password"
                }

                if(confirmPass.isEmpty())
                {
                   bindingRegister.confrmpasswrd.requestFocus()
                    bindingRegister.confrmpasswrd.error="Enter Confirm Password"
                }

                if(passWord.length in 1..5 )
                {
                   bindingRegister.password.requestFocus()
                   bindingRegister.password.error="Password Should be Greater than 6 "

                }
                if(confirmPass.length in 1..5)
                {
                    bindingRegister.confrmpasswrd.requestFocus()
                    bindingRegister.confrmpasswrd.error="Password Should be Greater than 6 "
                }

            }
//         else if(!confirmPass.equals(passWord))
//            {
//                bindingRegister.confrmpasswrd.requestFocus()
//                bindingRegister.confrmpasswrd.error="Please Enter Same Password as Above"
//            }
            else
            {/* val fname:String=firstname.text.toString()
                val lname:String=lastname.text.toString()
                val eml:String=emailz.text.toString()
                val pass:String=password.text.toString()
                val cpass:String=cnfrmpass.text.toString()
                val phoneon =phoneno.text.toString()*/
                if(bindingRegister.male.isChecked)
                {
                    gendr=bindingRegister.male.text.toString().substring(0,1)
                }
                else if(bindingRegister.female.isChecked)
                {
                    gendr=bindingRegister.female.text.toString().substring(0,1)
                }

                //ViewModel
                viewModelRegister.registerUser(this,firstName.toString(),lastName.toString(),Email.toString(),passWord.toString(),confirmPass.toString(),gendr,phoneNo.toString())
            /* val registerData = ApiService.registerUserInfo().registerUserData(firstName.toString(),lastName.toString(),Email.toString(),passWord.toString(),confirmPass.toString(),gendr,phoneNo.toString())
               Toast.makeText(this, "$firstName $lastName $Email $passWord $confirmPass $gendr $phoneNo ", Toast.LENGTH_SHORT).show()
                registerData?.enqueue(object : Callback<LoginModel?> {
                    override fun onResponse(call: Call<LoginModel?>, response: Response<LoginModel?>) {
                       // Toast.makeText(applicationContext, "successfull but not 200", Toast.LENGTH_SHORT).show()

                            Toast.makeText(applicationContext, "${response.body()?.userMsg}", Toast.LENGTH_SHORT).show()
                        if (response.isSuccessful){
                            intent = Intent(this@RegisterActivity,LoginActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }

                    }

                    override fun onFailure(call: Call<LoginModel?>, t: Throwable) {
                        Toast.makeText(applicationContext, "something wrong $t", Toast.LENGTH_SHORT).show()
                    }
                })
                GlobalScope.launch {
                  // db.insert(User(fname,lname,eml,pass,phoneon.toInt(),gendr))
                    //regisviewmodel.insertuser(User(fname,lname,eml,pass,phoneon.toInt(),gendr))
               }*/

            }


        }


    }
}