package com.cooking.merge


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast


class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val searchView = findViewById<SearchView>(R.id.search)
        val searchList = findViewById<ListView>(R.id.List)

        val searchItems = arrayOf("燕麥","優格","水果","草莓果醬","雞蛋",
            "洋蔥","培根","即食大燕麥片","高湯塊","青蔥","肉片"
        )

        val adapter : ArrayAdapter<String> = ArrayAdapter(
            this, android.R.layout.simple_list_item_1,searchItems
        )

        searchList.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                if (searchItems.contains(query)){
                    adapter.filter.filter(query)
                }
                else{
                    Toast.makeText(applicationContext,"Item not found", Toast.LENGTH_LONG).show()
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }


        })
    }


}