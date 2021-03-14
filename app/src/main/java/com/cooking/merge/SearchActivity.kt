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

        //設定Search變數
        val searchView = findViewById<SearchView>(R.id.search)

        //List（熱門搜尋）變數
        val searchList = findViewById<ListView>(R.id.List)

        //熱門搜尋初始array
        val searchItems = arrayOf("燕麥","優格","水果","草莓果醬","雞蛋",
            "洋蔥","培根","即食大燕麥片","高湯塊","青蔥","肉片"
        )

        //設定一個新的adapter供熱門搜尋的List使用
        val adapter : ArrayAdapter<String> = ArrayAdapter(
            this, android.R.layout.simple_list_item_1,searchItems
        )

        searchList.adapter = adapter

        //做一個textListener，參數query為輸入字串
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            //當使用者輸入（提交）一text
            override fun onQueryTextSubmit(query: String?): Boolean {

                searchView.clearFocus()         //轉移searchView的焦點，測試後可有可無

                //若輸入字串與初始array中物件有匹配
                if (searchItems.contains(query)){
                    adapter.filter.filter(query)    //adapter過濾出輸入字串並只顯示對應的item
                }
                else{
                    //若輸入字串沒有與array匹配，系統跳出訊息
                    Toast.makeText(applicationContext,"Item not found", Toast.LENGTH_LONG).show()
                }

                return false
            }

            //當使用者修改輸入字串
            override fun onQueryTextChange(newText: String?): Boolean {

                //重置adapter並重新檢查輸入字串
                adapter.filter.filter(newText)
                return false
            }


        })
    }


}