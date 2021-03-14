package com.cooking.merge

import android.content.ContentValues
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.cooking.merge.adapters.Permissions
import com.cooking.merge.bottom_fragments.SearchFragment
import com.cooking.merge.bottom_fragments.FavoritesFragment
import com.cooking.merge.bottom_fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val searchFragment = SearchFragment()
        val favoritesFragment = FavoritesFragment()

        setCurrentFragment(homeFragment)


        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.nav_home -> setCurrentFragment(homeFragment)
                R.id.nav_search -> {
                    setCurrentFragment(searchFragment)

                    //點選search時，和使用者要求權限
                    if(checkPermissionArray(Permissions.PERMISSIONS)){
                        Toast.makeText(applicationContext,"Camera Permissions Granted",Toast.LENGTH_LONG).show()
                    }else{
                        verifyPermissions(Permissions.PERMISSIONS)
                    }
                }
                R.id.nav_favorites -> setCurrentFragment(favoritesFragment)
            }
            true
        }

    }

    //系統跳出通知詢問使用者是否授權權限
    private fun verifyPermissions(permissions: Array<String>) {
        Log.d(ContentValues.TAG, "verifyPermissions : verifying permissions.")
        ActivityCompat.requestPermissions(
            this@MainActivity, permissions, VERIFY_PERMISSIONS_REQUEST
        )
    }

    //用來查看權限是否已授權，已授權為true，反之為false
    private fun checkPermissionArray(permissions: Array<String>): Boolean {
        Log.d(ContentValues.TAG, "checkPermissionsArray: checking permissions array.")
        for (i in permissions.indices) {
            val check = permissions[i]
            if (!checkPermissions(check)) {
                return false
            }
        }
        return true
    }

    //和使用者要求權限
    fun checkPermissions(permission: String): Boolean {
        Log.d(ContentValues.TAG, "checkPermissions: checking permissions $permission")
        val permissionRequest = ActivityCompat.checkSelfPermission(this, permission)
        return if (permissionRequest != PackageManager.PERMISSION_GRANTED) {
            Log.d(ContentValues.TAG, "checkPermissions: \n Permission wasn't granted for: $permission")
            false
        } else {
            Log.d(ContentValues.TAG, "checkPermissions: \n Permission was granted for: $permission")
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

    companion object {
        private const val VERIFY_PERMISSIONS_REQUEST = 1
    }
}