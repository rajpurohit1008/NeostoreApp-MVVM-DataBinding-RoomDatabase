package com.rajpurohit.neostoreapp.viewactivity

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rajpurohit.neostoreapp.R
import com.rajpurohit.neostoreapp.databinding.ActivityEditprofileBinding
import com.rajpurohit.neostoreapp.model.LoginModel
import com.rajpurohit.neostoreapp.network.ApiService
import com.rajpurohit.neostoreapp.viewmodel.EditProfileViewModel
import com.rajpurohit.neostoreapp.viewmodel.MyAccountViewModel
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class EditprofileActivity : AppCompatActivity() {
    lateinit var editProfileBinding:ActivityEditprofileBinding
    lateinit var editdob: String
   // lateinit var circleImage: CircleImageView
    lateinit var userImage: String
    val PICK_IMAGE = 1
    lateinit var bitmap:Bitmap
    var imageUrl:Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         editProfileBinding = DataBindingUtil.setContentView(this,R.layout.activity_editprofile)
       /* val fname: EditText = findViewById(R.id.editprofilefirstname)
        val lname: EditText = findViewById(R.id.editprofilelastname)
        val email: EditText = findViewById(R.id.editprofileemail)
        val phone: EditText = findViewById(R.id.editprofilephonenumber)
        val dob: EditText = findViewById(R.id.editprofiledob)
        val sumitbtn: Button = findViewById(R.id.editprofileeditprofilebtn)*/
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
      //  circleImage = findViewById(R.id.editprofileimage)
        //ViewModel
        val viewModelEditproflie  = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(EditProfileViewModel::class.java)
          viewModelEditproflie.getEditProfile().observe(this, androidx.lifecycle.Observer {
              if (it != null) {
                  Toast.makeText(this, "${it.userMsg}", Toast.LENGTH_SHORT).show()
                  intent = Intent(this@EditprofileActivity, MyaccountActivity::class.java)
                  intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                  startActivity(intent)
              }
          })



        val fname = editProfileBinding.editprofilefirstname.text
        val lname = editProfileBinding.editprofilelastname.text
        val email = editProfileBinding.editprofileemail.text
        val phone =editProfileBinding.editprofilephonenumber.text
       // val dob = editProfileBinding.editprofiledob.text

        editProfileBinding.editprofileimage.setOnClickListener {
          val  intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(Intent.createChooser(intent, "select picture"), PICK_IMAGE)
        }

        editProfileBinding.editprofiledob.setOnClickListener() {
            val now = Calendar.getInstance()
            val curyear = now.get(Calendar.YEAR)
            val curmonth = now.get(Calendar.MONDAY)
            val curdayOfMonth = now.get(Calendar.DAY_OF_MONTH)
            val datePicker = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        editdob = "$dayOfMonth-$month-$year"
                        editProfileBinding.editprofiledob.setText(editdob)
                    },
                    curyear,
                    curmonth,
                    curdayOfMonth
            )
            datePicker.show()

        }

        editProfileBinding.editprofileeditprofilebtn.setOnClickListener() {
            if (fname.isEmpty() ||lname.isEmpty() ||email.isEmpty() ||
                    phone.isEmpty() ||  editProfileBinding.editprofiledob.text.isEmpty() ||
                    !fname.matches(Regex("[a-zA-Z ]+")) ||
                    !lname.matches(Regex("[a-zA-Z ]+")) ||
                    !email.matches(Regex(emailPattern))
            ) {
                if (fname.isEmpty()) {
                   editProfileBinding.editprofilefirstname.requestFocus()
                    editProfileBinding.editprofilefirstname.setError("Please Enter First Name")
                } else if (!fname.matches(Regex("[a-zA-Z ]+"))) {
                   editProfileBinding.editprofilefirstname.requestFocus()
                    editProfileBinding.editprofilefirstname.setError("Please Enter Alphabets")
                }
                if (lname.isEmpty()) {
                   editProfileBinding.editprofilelastname.requestFocus()
                    editProfileBinding.editprofilelastname.setError("Please Enter Last Name")
                } else if (!lname.matches(Regex("[a-zA-Z ]+"))) {
                   editProfileBinding.editprofilelastname.requestFocus()
                   editProfileBinding.editprofilelastname.setError("Please Enter Alphabets")
                }
                if (phone.isEmpty()) {
                   editProfileBinding.editprofilephonenumber.requestFocus()
                    editProfileBinding.editprofilephonenumber.setError("Please Enter Phone Number")
                } else if (phone.length < 10) {
                    editProfileBinding.editprofilephonenumber.requestFocus()
                    editProfileBinding.editprofilephonenumber.setError("Enter Valid Phone Number")
                }
                if (email.isEmpty()) {
                   editProfileBinding.editprofileemail.requestFocus()
                    editProfileBinding.editprofileemail.setError("Please Enter Email Id")
                } else if (!email.matches(Regex(emailPattern))) {
                   editProfileBinding.editprofileemail.requestFocus()
                   editProfileBinding.editprofileemail.setError("Please Enter Valid Email Id")
                }
                if (editProfileBinding.editprofiledob.text.isEmpty()) {
                   editProfileBinding.editprofiledob.requestFocus()
                    editProfileBinding.editprofiledob.setError("enter Birthday")
                }
            } else {
               /* val editfname = fname.text.toString()
                val editlname = lname.text.toString()
                val editemail = email.text.toString()
                val editphoneno = phone.text.toString()*/
                val getsharedToken = getSharedPreferences("My_Token", MODE_PRIVATE)
                val editprofileToken = getsharedToken.getString("Access_Token", "not Fount")
                viewModelEditproflie.makeApiCall(editprofileToken.toString(),fname.toString(),lname.toString(),email.toString(),editdob, phone.toString())
               /* Toast.makeText(this, "$userImage", Toast.LENGTH_SHORT).show()
                val callEditprofile = ApiService.registerUserInfo().editProfile(editprofileToken, fname.toString(), lname.toString(), email.toString(), editdob,"null", phone.toString())
                callEditprofile?.enqueue(object : Callback<LoginModel?> {
                    override fun onResponse(call: Call<LoginModel?>, response: Response<LoginModel?>) {
                        Toast.makeText(this@EditprofileActivity, "${response.body()?.userMsg}", Toast.LENGTH_SHORT).show()
                        if (response.isSuccessful){
                            intent = Intent(this@EditprofileActivity,MyaccountActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }
                    }

                    override fun onFailure(call: Call<LoginModel?>, t: Throwable) {
                        Toast.makeText(this@EditprofileActivity, "EditProfile $t", Toast.LENGTH_SHORT).show()
                    }
                })*/
            }
        }
    }

    fun backeditprofile(view: View) {
        onBackPressed()
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, imagedata: Intent?) {
        super.onActivityResult(requestCode, resultCode, imagedata)
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageUrl = imagedata?.data
            bitmap= MediaStore.Images.Media.getBitmap(contentResolver,imageUrl)
            userImage = editProfileBinding.editprofileimage.setImageBitmap(bitmap).toString()



        }
    }
}