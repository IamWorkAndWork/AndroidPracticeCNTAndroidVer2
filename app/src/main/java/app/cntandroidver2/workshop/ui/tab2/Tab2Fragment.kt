package app.cntandroidver2.workshop.ui.tab2

import android.app.Activity
import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import app.cntandroidver2.workshop.R
import app.cntandroidver2.workshop.activity.ActivityB
import app.cntandroidver2.workshop.service.MessageService
import kotlinx.android.synthetic.main.fragment_tab2.*


class Tab2Fragment : Fragment(), ServiceConnection {

    private var mIsBound: Boolean = false
    private var service: MessageService.MyBinder? = null

    private lateinit var tab2ViewModel: Tab2ViewModel
//    private var messageServiceReceiver: MessageReciver? = null

    private val ACTIVITY_B_REQUEST_CODE = 100


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tab2ViewModel =
            ViewModelProviders.of(this).get(Tab2ViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tab2, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        registerMessageReceiver()

        initListener()
    }

//    private fun registerMessageReceiver() {
//
//        messageServiceReceiver = MessageReciver()
//        val intentFilter = IntentFilter()
//        intentFilter.addAction(MessageService.MESSAGE_INFO)
//        activity?.registerReceiver(messageServiceReceiver, intentFilter)
//    }

    override fun onStop() {
        super.onStop()

//        activity?.unregisterReceiver(messageServiceReceiver)
    }

    private fun initListener() {

        tab2BtActivityForResult.setOnClickListener {

            startActivityB()

        }

        tab2BtStartService.setOnClickListener {

            startService()

        }

        tab2BtBindService.setOnClickListener {

            bindService()

        }

    }

    private fun startActivityB() {

        val intent = Intent(activity, ActivityB::class.java)
        intent.putExtra(KEY_MESSAGE, tab2ViewModel.textIamJack.value)
        startActivityForResult(intent, ACTIVITY_B_REQUEST_CODE)

    }

    private fun bindService() {

        val intent = Intent(activity!!, MessageService::class.java)
        activity?.bindService(intent, this, Context.BIND_AUTO_CREATE)
        mIsBound = true
    }

    override fun onPause() {
        super.onPause()

        service?.let {
            if (mIsBound) {
                mIsBound = false
                activity?.unbindService(this);
            }
        }

        val myService = Intent(activity, MessageService::class.java)
        activity?.stopService(myService)
    }

    private fun startService() {

//        val cbIntent = Intent()
//        cbIntent.setClass(activity!!, MessageService::class.java)
//        cbIntent.putExtra("message", "hello")
//        activity?.startService(cbIntent)


        activity?.startService(Intent(activity, MessageService::class.java))

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ACTIVITY_B_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                data?.let {

                    val messageReceive = it.getStringExtra(KEY_MESSAGE)
                    Toast.makeText(activity, messageReceive, Toast.LENGTH_SHORT).show()

                }


            }
        }
    }

    private class MessageReciver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val cbinfo = intent.getStringExtra("cashback")
            Log.e("print", "onReceive from service : " + cbinfo)
        }
    }


    override fun onServiceDisconnected(name: ComponentName?) {
        service = null
    }

    override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
        val binder = binder as? MessageService.MyBinder
        service = binder?.service
    }

    companion object {

        const val KEY_MESSAGE = "message"

        fun newInstance(): Fragment {
            return Tab2Fragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }

}