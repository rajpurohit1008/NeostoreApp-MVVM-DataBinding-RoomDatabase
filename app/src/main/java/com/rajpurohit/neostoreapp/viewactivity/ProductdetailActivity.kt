package com.rajpurohit.neostoreapp.viewactivity

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rajpurohit.neostoreapp.R
import com.rajpurohit.neostoreapp.adapter.ProductImageAdapter
import com.rajpurohit.neostoreapp.model.Data
import com.rajpurohit.neostoreapp.model.LoginModel
import com.rajpurohit.neostoreapp.model.ProductImage
import com.rajpurohit.neostoreapp.model.ProductModel
import com.rajpurohit.neostoreapp.model.mycartmodel.Mycartcurd
import com.rajpurohit.neostoreapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductdetailActivity : AppCompatActivity() {
    lateinit var productDatalist : Data
    lateinit var firstImage:ImageView
    lateinit var productList:List<ProductImage>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productdetail)
        val tooltitle:TextView = findViewById(R.id.tooltitle)
        val productName:TextView = findViewById(R.id.product_name)
        val categoryname:TextView = findViewById(R.id.product_category)
        val productProducer:TextView = findViewById(R.id.product_producer)
        val ratingbar:RatingBar = findViewById(R.id.product_ratingbar)
        val productPrice:TextView = findViewById(R.id.product_price1)
        val productDescription :TextView = findViewById(R.id.product_desc)
        val productRecycleView :RecyclerView = findViewById(R.id.recyclerimage)
        firstImage = findViewById(R.id.firstimage)
        productRecycleView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
       // val productId = Intent().getStringExtra("product_id")
        val productId = intent.getStringExtra("product_id")

        val productItem = ApiService.getTableProductList().getProductDetail(productId)
        productItem.enqueue(object : Callback<ProductModel> {
            override fun onResponse(call: Call<ProductModel>, response: Response<ProductModel>) {

                if (response.isSuccessful) {
                    productDatalist = response.body()?.data!!
                    tooltitle.text = productDatalist.name
                    productName.text = productDatalist.name
                    categoryname.text ="Category-"+ productDatalist.producer
                    productProducer.text = productDatalist.producer
                    ratingbar.rating = productDatalist.rating!!.toFloat()
                    productPrice.text = "Rs " + productDatalist.cost
                    productDescription.text = productDatalist.description
                    productList = productDatalist.productImages
                    Glide.with(applicationContext).load(productList[0].image).centerCrop().into(firstImage)
                    productRecycleView.adapter = ProductImageAdapter(applicationContext,productList)
                }
            }

            override fun onFailure(call: Call<ProductModel>, t: Throwable) {
                Toast.makeText(applicationContext, "product detail fail", Toast.LENGTH_LONG).show()
            }
        })

    }

    fun backproduct(view: View) {
        onBackPressed()
    }

    fun shareOnclick(view: View) {
        intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        startActivity(intent)
    }

    fun buynowonclick(view: View) {

       val buynowDialog = Dialog(this)
        buynowDialog.setContentView(R.layout.productquantity_dialog)
        val quantitytitle :TextView = buynowDialog.findViewById(R.id.quantitytitle)
        val quantityImage :ImageView = buynowDialog.findViewById(R.id.quantity_image)
        val quantityET :EditText = buynowDialog.findViewById(R.id.quantity_ET)
        val quantitybutton :Button = buynowDialog.findViewById(R.id.quantity_button)

        val getsharedToken = getSharedPreferences("My_Token", MODE_PRIVATE)
        val AccessToken2  = getsharedToken.getString("Access_Token","not Fount")
        //Toast.makeText(this, "$AccessToken2", Toast.LENGTH_SHORT).show()

        val productId = intent.getStringExtra("product_id")
       // val quantityValue  = java.lang.String.valueOf(quantityET.text.toString())
        val quantityValue  = quantityET.text.toString()

        buynowDialog.show()
        quantitytitle.text = productDatalist.name
       Glide.with(this).load(productDatalist.productImages[0].image).fitCenter().into(quantityImage)
        quantitybutton.setOnClickListener() {
            Toast.makeText(this, "$quantityValue", Toast.LENGTH_SHORT).show()
           val calladdcart = ApiService.myCartCURD().addToMycart(AccessToken2, productId?.toInt(), 1)
            calladdcart?.enqueue(object : Callback<Mycartcurd?> {
                override fun onResponse(call: Call<Mycartcurd?>, response: Response<Mycartcurd?>) {
                    Toast.makeText(this@ProductdetailActivity, "${response.body()?.userMsg}", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Mycartcurd?>, t: Throwable) {
                    Toast.makeText(this@ProductdetailActivity, "Something wrong to Add Cart $t", Toast.LENGTH_SHORT).show()
                }
            })
            buynowDialog.dismiss()
        }

    }

    fun ratingOnclick(view: View) {

        val ratenowDialog =Dialog(this)
        ratenowDialog.setContentView(R.layout.productrating_dialog)
        val ratingtitle : TextView = ratenowDialog.findViewById(R.id.rating_title)
        val ratingImage : ImageView = ratenowDialog.findViewById(R.id.rating_image)
        val ratingbar :RatingBar = ratenowDialog.findViewById(R.id.rating_ratebar)
        val ratingbarbutton :Button = ratenowDialog.findViewById(R.id.ratingbar_button)
        ratenowDialog.show()
        val productId = intent.getStringExtra("product_id")
        val ratebarValue = ratingbar.rating
        ratingtitle.text = productDatalist.name
        Glide.with(this).load(productDatalist.productImages[0].image).centerCrop().into(ratingImage)
        ratingbarbutton.setOnClickListener() {
            val callrating = ApiService.getTableProductList().addRatingbar(productId?.toInt(), ratebarValue?.toInt())
            callrating.enqueue(object : Callback<LoginModel?> {
                override fun onResponse(call: Call<LoginModel?>, response: Response<LoginModel?>) {
                    Toast.makeText(this@ProductdetailActivity, "${response.body()?.userMsg}", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<LoginModel?>, t: Throwable) {
                    Toast.makeText(this@ProductdetailActivity, "Something wrong in Rating $t", Toast.LENGTH_SHORT).show()
                }
            })
            ratenowDialog.dismiss()
        }
    }
}