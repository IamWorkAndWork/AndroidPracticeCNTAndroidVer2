package app.cntandroidver2.workshop.data.Model


import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("Author")
    var author: String?,
    @SerializedName("CName")
    var cName: String?,
    @SerializedName("Desc")
    var desc: String?,
    @SerializedName("Id")
    var id: Int?,
    @SerializedName("Img")
    var img: String?,
    @SerializedName("Name")
    var name: String?,
    @SerializedName("Score")
    var score: Double?
)