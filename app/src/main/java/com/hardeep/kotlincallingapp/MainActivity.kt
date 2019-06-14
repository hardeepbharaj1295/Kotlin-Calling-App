package com.hardeep.kotlincallingapp

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    var number : EditText ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        number = findViewById(R.id.editText)
    }

    fun data(v:View)
    {
        calling()
    }

    fun calling()
    {
        val num = number!!.text.toString()

        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:"+num)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(arrayOf(Manifest.permission.CALL_PHONE),1)
                return
            }
        }
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode)
        {
            1->{
                if (grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    calling()
                }
                else
                {
                    AlertDialog.Builder(this)
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setTitle("Alert!")
                        .setMessage("Please give permission ")
                        .setPositiveButton("ok", DialogInterface.OnClickListener { dialog, which ->
                            calling()
                        })
                        .setNegativeButton("Cancel",null)
                        .show()
                }
            }
            else ->{

            }
        }
    }
}
