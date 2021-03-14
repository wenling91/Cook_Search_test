package com.cooking.merge.food_fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.cooking.merge.R
import kotlinx.android.synthetic.main.fooditems_details.*


class FoodDetailsf : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fooditems_details)

        //////返回鈕//////
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        //////返回鈕//////

        //////set data//////
        IV_detailsimage.setImageResource(getIntent().getStringExtra("FOODIMAGE")?.toInt()!!)
        TV_detailsname.text = getIntent().getStringExtra("FOODNAME")
        TV_food_need.text = getIntent().getStringExtra("FOODINGREDIENT")
        TV_sauce.text = getIntent().getStringExtra("FOODSAUCE")
        BTN_link.setOnClickListener() {
            val open_web_page =
                Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("FOODLINK")))
            startActivity(open_web_page)
        }
        //////set data//////

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}



