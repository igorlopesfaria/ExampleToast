package br.com.exampletoast

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class ToastActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_toast)
        val simpleToastBTN: Button = findViewById(R.id.simpleToastBTN)
        val longTimeToastBTN: Button = findViewById(R.id.longTimeToastBTN)
        val genericMethodBTN: Button = findViewById(R.id.genericMethodToastBTN)
        val changePositionToastBTN: Button = findViewById(R.id.changePositionToastBTN)
        val customViewToast: Button = findViewById(R.id.customViewToastBTN)
        val callbackToast: Button = findViewById(R.id.callbackToastBTN)
        val backgroundAppToastBTN: Button = findViewById(R.id.backgroundAppToastBTN)
        val backgroundAppCustomToastBTN: Button = findViewById(R.id.backgroundAppCustomToastBTN)

        simpleToastBTN.setOnClickListener {
            val text = "Simple Toast with length short!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }

        longTimeToastBTN.setOnClickListener {
            val text = "Custom length, now is Long!"
            val duration = Toast.LENGTH_LONG

            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }

        genericMethodBTN.setOnClickListener {
            genericMethodToToast(
                context = applicationContext,
                text = "Using generic Method to create Toast!"
            )
        }

        changePositionToastBTN.setOnClickListener {
            val text = "Change position of Toast!"
            val duration = Toast.LENGTH_LONG

            val toast = Toast.makeText(applicationContext, text, duration)
            toast.setGravity(Gravity.TOP or Gravity.START, 0, 0)
            toast.show()

        }

        // SET VIEW IS DEPRECATED
        // Google has deprecated the setView(view: View) function and implies not to set customised view (layout) anymore in the future.
        customViewToast.setOnClickListener {

            val customView = LayoutInflater.from(applicationContext).inflate(R.layout.custom_view_toast, null)
            val text: TextView = customView.findViewById(R.id.text)
            text.text = "This is a custom toast"
            with (Toast(applicationContext)) {
                duration = Toast.LENGTH_LONG
                view = customView
                show()
            }
        }

        //API 30
        callbackToast.setOnClickListener {
            val callbackToast =
                Toast.makeText(applicationContext,"Simple toast message with callback", Toast.LENGTH_SHORT)
            callbackToast.addCallback(object: Toast.Callback() {
                override fun onToastShown() {
                    super.onToastShown()
                    Log.i("TOAST", "Toast is shown")
                }

                override fun onToastHidden() {
                    super.onToastHidden()
                    Log.i("TOAST", "Toast is Hidde")
                }
            })
            callbackToast.show()

        }

        backgroundAppToastBTN.setOnClickListener {
            Thread.sleep(3000)
            Toast.makeText(applicationContext,"Show simple Toast in background", Toast.LENGTH_SHORT).show()
        }

        backgroundAppCustomToastBTN.setOnClickListener {
            Thread.sleep(3000)
            val customView = LayoutInflater.from(applicationContext).inflate(R.layout.custom_view_toast, null)
            val text: TextView = customView.findViewById(R.id.text)
            text.text = "This is a custom toast in background"
            with (Toast(applicationContext)) {
                duration = Toast.LENGTH_LONG
                view = customView
                show()
            }
        }


    }

    private fun genericMethodToToast(context: Context, text:String, duration:Int= Toast.LENGTH_SHORT){
        Toast.makeText(context, text, duration).show()
    }
}