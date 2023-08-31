package rs.raf.vezbe11.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    @PrimaryKey
    @ColumnInfo(name = "idCategory")
    val id: String,
    @ColumnInfo(name = "strCategory")
    val name: String,
    @ColumnInfo(name = "strCategoryThumb")
    val imageURL: String,
    @ColumnInfo(name = "strCategoryDescription")
    val description: String
) : Parcelable