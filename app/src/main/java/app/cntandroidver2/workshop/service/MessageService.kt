package app.cntandroidver2.workshop.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast
import app.cntandroidver2.workshop.R


class MessageService : Service() {

    private val mBinder: IBinder = MyBinder()

    private var handler: Handler? = null
    private var runnable: Runnable? = null


    override fun onDestroy() {
        super.onDestroy()

        handler?.let {
            it.removeCallbacks(runnable)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        handler = Handler(Looper.getMainLooper())

        runnable = Runnable {

            Toast.makeText(
                applicationContext,
                getString(R.string.start_service_success),
                Toast.LENGTH_SHORT
            ).show()

        }
        handler?.post(runnable)


        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {

        handler = Handler(Looper.getMainLooper())

        runnable = Runnable {

            Toast.makeText(
                applicationContext,
                getString(R.string.bind_service_success),
                Toast.LENGTH_SHORT
            ).show()

        }
        handler?.post(runnable)

        return mBinder
    }

    class MyBinder : Binder() {
        val service: MyBinder
            get() = this
    }


}