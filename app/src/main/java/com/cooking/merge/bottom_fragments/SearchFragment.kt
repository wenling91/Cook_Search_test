package com.cooking.merge.bottom_fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.cooking.merge.MainActivity
import com.cooking.merge.R
import com.cooking.merge.SearchActivity
import com.cooking.merge.adapters.Permissions.CAMERA_PERMISSIONS


class  SearchFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        //Text Search 點擊事件
        val textSearch_btn = view.findViewById<View>(R.id.search_button) as Button
        textSearch_btn.setOnClickListener {

            //使用Intent進入SearchActivity
            val intent = Intent(context, SearchActivity::class.java)
            startActivity(intent)
        }

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