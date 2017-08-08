package com.hail.kppermission

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {
    private val TAG = "KPermission"
    /**
     * 创建KPermissions实例：
     */
    private var kPermission: KPermission = KPermission(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        /**
         * 第一个参数为需要请求权限的数组
         */
        kPermission.requestPermission(arrayOf(Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE), {
            /**
             * 第二个为是否全部请求成功的回调，
             */
            Log.i(TAG, "isAllow---$it")
        }, {
            /**
             *  第三个为请求每项权限时的回调。
             */
            Log.i(TAG, "permission---$it")
        })

        findViewById(R.id.button).setOnClickListener {
            kPermission.startPermissionSetting()
        }
    }

    /**
     * 实现onRequestPermissionsResult回调：
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        kPermission.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
