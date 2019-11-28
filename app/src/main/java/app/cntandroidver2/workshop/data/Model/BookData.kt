package app.cntandroidver2.workshop.data.Model


import com.google.gson.annotations.SerializedName

data class BookData(
    @SerializedName("data")
    var `data`: List<Book?>?,
    @SerializedName("info")
    var info: String?,
    @SerializedName("status")
    var status: Int?
)