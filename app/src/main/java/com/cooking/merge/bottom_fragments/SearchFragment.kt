package com.cooking.merge.bottom_fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.cooking.merge.MainActivity
import com.cooking.merge.R
import com.cooking.merge.SearchActivity
import com.cooking.merge.adapters.Permissions.CAMERA_PERMISSIONS
import kotlinx.android.synthetic.main.fragment_search.*


class  SearchFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        //Open Camera 點擊事件
        val Launch_Camera_btn = view.findViewById<View>(R.id.Launch_Camera_btn) as Button
        Launch_Camera_btn.setOnClickListener {

            //若MainActivity中要求的相機權限已經授權
            if ((activity as MainActivity).checkPermissions(CAMERA_PERMISSIONS[0])) {

                //進入Camera
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)

            } else {
                //再次要求權限
                val intent = Intent(activity, SearchActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

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

                return false
            }

            //當使用者修改輸入字串
            override fun onQueryTextChange(newText: String?): Boolean {

                //重置adapter並重新檢查輸入字串
                adapter.filter.filter(newText)
                return false
            }

        })

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        //若已經完成拍照並確認照片後
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            //設定一變數作為照片的變數
            //val takenImage = data?.extras?.get("data") as Bitmap
            //pictureView.setImageBitmap(takenImage)
        }
        else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object {
        private const val CAMERA_REQUEST_CODE = 1114
    }

}