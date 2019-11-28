package app.cntandroidver2.workshop.ui.tab4

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import app.cntandroidver2.workshop.R
import kotlinx.android.synthetic.main.fragment_tab4.*


class Tab4Fragment : Fragment() {


    private lateinit var viewModel: Tab4ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab4, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(Tab4ViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tab4WebView.addJavascriptInterface(AndroidToJS(activity!!), "test")
        tab4WebView.getSettings().setJavaScriptEnabled(true)
        tab4WebView.loadUrl(BASE_URL)

    }

    class AndroidToJS(private var mContext: Context) {

        @JavascriptInterface
        fun hello(message: String?) {
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
            Log.d(TAG, "AndroidToJS callback = " + message.toString())
        }

    }

    companion object {

        const val BASE_URL = "http://34.82.250.142/temp/testjs.html"
        private val TAG = "Tab4Fragment"

        fun newInstance(): Fragment {
            return Tab4Fragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }

}
