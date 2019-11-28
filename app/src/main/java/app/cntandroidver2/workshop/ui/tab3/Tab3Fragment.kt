package app.cntandroidver2.workshop.ui.tab3

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import app.cntandroidver2.workshop.R
import app.cntandroidver2.workshop.utils.SharedPrefUtil
import app.cntandroidver2.workshop.utils.Singleton
import kotlinx.android.synthetic.main.fragment_tab3.*


class Tab3Fragment : Fragment() {

    private lateinit var tab3ViewModel: Tab3ViewModel
    lateinit var handler: Handler


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        tab3ViewModel =
            ViewModelProviders.of(this).get(Tab3ViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tab3, container, false)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        handler = Handler() { message ->

            val bundle: Bundle = message.data
            var messageData: String? = bundle.getString(KEY_MESSAGE)
            Toast.makeText(this@Tab3Fragment.activity!!, messageData, Toast.LENGTH_SHORT)
                .show()

            true
        }

        initListener()


    }

    private fun initListener() {

        tab3BtSaveDatas.setOnClickListener {
            SharedPrefUtil(activity!!).name = "Zhangsan"

            Toast.makeText(activity!!, getString(R.string.save_name_success), Toast.LENGTH_SHORT)
                .show()

            //with setter
//            SharedPrefUtil(activity!!).setString(SharedPrefUtil.KEY_NAME, "Zhangsan")
        }

        tab3BtReadDatas.setOnClickListener {

            val formatName =
                String.format(getString(R.string.name_show), SharedPrefUtil(activity!!).name)

            tab3TxtName.text = formatName

            //with getter
//            tab3TxtName.text = SharedPrefUtil(activity!!).getString(SharedPrefUtil.KEY_NAME)
        }

        tab3BtSingleton.setOnClickListener {
            Singleton.say(activity!!)
        }

        tab3BtStartThread.setOnClickListener {
            callThread()
        }


    }

    var mMessageSender: Runnable = Runnable {
        val msg = handler.obtainMessage()
        val bundle = Bundle()
        bundle.putString(KEY_MESSAGE, "Thread is running")
        msg?.setData(bundle)
        handler.sendMessage(msg)
    }

    private fun callThread() {

        try {

//            Thread.sleep(2000)
//            Thread(mMessageSender).start();

            handler.postDelayed(mMessageSender, 2000L)


        } catch (e: Exception) {

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(mMessageSender)
    }

    companion object {

        const val KEY_MESSAGE = "message"

        fun newInstance(): Fragment {
            return Tab3Fragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }
}