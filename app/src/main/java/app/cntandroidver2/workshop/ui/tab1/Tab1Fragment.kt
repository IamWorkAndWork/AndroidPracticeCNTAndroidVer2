package app.cntandroidver2.workshop.ui.tab1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import app.cntandroidver2.workshop.R
import app.cntandroidver2.workshop.data.DataRepository
import app.cntandroidver2.workshop.data.Model.Book
import app.cntandroidver2.workshop.data.Network.ApiModule
import app.cntandroidver2.workshop.data.Network.BookDataSource
import kotlinx.android.synthetic.main.fragment_tab1.*

class Tab1Fragment : Fragment() {

    val TAG = "Tab1Fragment"
    private lateinit var tab1ViewModel: Tab1ViewModel

    private lateinit var apiModule: ApiModule
    private lateinit var bookDataSource: BookDataSource
    private lateinit var repository: DataRepository
    private lateinit var factory: ViewModelProvider.Factory

    private lateinit var adapter: Tab1Adapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        apiModule = ApiModule()
        bookDataSource = BookDataSource(apiModule)
        repository = DataRepository(bookDataSource)

        factory = Tab1ViewModel.Factory(repository)

        tab1ViewModel =
            ViewModelProviders.of(this, factory).get(Tab1ViewModel::class.java)

//        Log.d(TAG, "onCreateView")

        val root = inflater.inflate(R.layout.fragment_tab1, container, false)

        return root
    }

    override fun onResume() {
        super.onResume()

        loadDataFromService()


//        Log.d(TAG, "onResume")

    }

    private fun loadDataFromService() {

        tab1ProgressBar.visibility = View.VISIBLE
        tab1TxtLoadingErrorStatus.visibility = View.GONE
        tab1SwipeRefresh.isRefreshing = true

        //using RX
//        tab1ViewModel.fetchBookFromServer()

        //using Coroutine
//        tab1ViewModel.fetchBookFromServerCoroutine()

        //using Retrofit Async
        tab1ViewModel.fetchBookAsync()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        initWidget()

        initListener()

    }

    private fun initListener() {

        tab1SwipeRefresh.setOnRefreshListener {

            loadDataFromService()

        }

    }

    private fun initWidget() {

        adapter = Tab1Adapter(object : Tab1Adapter.onTab1Adapter {
            override fun onClickBook(book: Book) {
                Toast.makeText(activity, "Click at : " + book.name, Toast.LENGTH_SHORT).show()
            }

            override fun onCLickCategory(book: Book) {
                Toast.makeText(
                    activity,
                    "Click onCLickCategory : " + book.cName,
                    Toast.LENGTH_SHORT
                ).show()

            }

            override fun onClickAuthor(book: Book) {
                Toast.makeText(activity, "Click onClickAuthor : " + book.author, Toast.LENGTH_SHORT)
                    .show()

            }
        })

        val lm = LinearLayoutManager(activity)
        val divider = DividerItemDecoration(activity, lm.orientation)

        tab1RecView.apply {
            adapter = this@Tab1Fragment.adapter
            layoutManager = lm
            setHasFixedSize(true)
//            addItemDecoration(divider)
        }

    }

    private fun observeViewModel() {

        tab1ViewModel.bookData.observe(this, Observer {
            it?.let { bookData ->
                if (bookData.info == "success") {
                    adapter.updateItems(bookData.data)
                } else {
                    tab1TxtLoadingErrorStatus.visibility = View.VISIBLE
                }
            }
        })


        tab1ViewModel.bookStatus.observe(this, Observer {
            tab1ProgressBar.visibility = View.GONE
            tab1SwipeRefresh.isRefreshing = false
            when (it) {
                true -> {
                    tab1TxtLoadingErrorStatus.visibility = View.GONE
                }
                false -> {
                    tab1TxtLoadingErrorStatus.visibility = View.VISIBLE
                }
            }
        })
    }

    companion object {
        fun newInstance(): Fragment {
            return Tab1Fragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }


}