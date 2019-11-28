package app.cntandroidver2.workshop.ui.tab1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.cntandroidver2.workshop.R
import app.cntandroidver2.workshop.data.Model.Book
import app.cntandroidver2.workshop.utils.loadImageWithGlide
import kotlinx.android.synthetic.main.adapter_tab1_item.view.*

class Tab1Adapter(private var listener: onTab1Adapter) :
    RecyclerView.Adapter<Tab1Adapter.BookViewHolder>() {

    private var items: List<Book?>? = arrayListOf()

    interface onTab1Adapter {
        fun onClickBook(book: Book)
        fun onCLickCategory(book: Book)
        fun onClickAuthor(book: Book)
    }

    fun updateItems(items: List<Book?>?) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_tab1_item, parent, false)
        return BookViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items?.size!!
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindItem(position)
    }

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {

            itemView.setOnClickListener {
                items?.get(adapterPosition)?.let { listener.onClickBook(it) }
            }

            itemView.adapterTab1BtAuthor.setOnClickListener {
                items?.get(adapterPosition)?.let { listener.onClickAuthor(it) }
            }

            itemView.adapterTab1BtCategory.setOnClickListener {
                items?.get(adapterPosition)?.let { listener.onCLickCategory(it) }
            }

        }

        fun bindItem(position: Int) {

            val book = items?.get(position)

            itemView.adapterTab1ImgIcon.loadImageWithGlide(book?.img)
            itemView.adapterTab1TxtTitle.text = book?.name
            itemView.adapterTab1TxtDetail.text = book?.desc

            itemView.adapterTab1BtAuthor.text = book?.author
            itemView.adapterTab1BtCategory.text = book?.cName

            itemView.adapterTab1TxtScore.text = book?.score.toString()

        }

    }
}

