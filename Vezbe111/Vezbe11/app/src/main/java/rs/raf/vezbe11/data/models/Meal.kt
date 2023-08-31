package rs.raf.vezbe11.data.models

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.PrimaryKey

data class Meal(
    @PrimaryKey
    val id: String,
    val name: String,
    val category: String?,
    val ingredient1: String?,
    val measure1: String?,
    val ingredient2: String?,
    val measure2: String?,
    val ingredient3: String?,
    val measure3: String?,
    val ingredient4: String?,
    val measure4: String?,
    val ingredient5: String?,
    val measure5: String?,
    val ingredient6: String?,
    val measure6: String?,
    val ingredient7: String?,
    val measure7: String?,
    val ingredient8: String?,
    val measure8: String?,
    val ingredient9: String?,
    val measure9: String?,
    val ingredient10: String?,
    val measure10: String?,
    val ingredient11: String?,
    val measure11: String?,
    val ingredient12: String?,
    val measure12: String?,
    val ingredient13: String?,
    val measure13: String?,
    val ingredient14: String?,
    val measure14: String?,
    val ingredient15: String?,
    val measure15: String?,
    val ingredient16: String?,
    val measure16: String?,
    val ingredient17: String?,
    val measure17: String?,
    val ingredient18: String?,
    val measure18: String?,
    val ingredient19: String?,
    val measure19: String?,
    val ingredient20: String?,
    val measure20: String?,
    val area: String?,
    val drinkAlternate: String?,
    val instructions: String?,
    val tags: String?,
    val youtube: String?,
    val source: String?,
    var imageSource: String?,
    val creativeCommonsConfirmed: String?,
    val dateModified: String?,
    var saved:Boolean,
    var cookingDate: String?,
    var mealType: String?,
    var strMealThumb: String?

) : Parcelable {

    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readBoolean(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(category)
        parcel.writeString(ingredient1)
        parcel.writeString(measure1)
        parcel.writeString(ingredient2)
        parcel.writeString(measure2)
        parcel.writeString(ingredient3)
        parcel.writeString(measure3)
        parcel.writeString(ingredient4)
        parcel.writeString(measure4)
        parcel.writeString(ingredient5)
        parcel.writeString(measure5)
        parcel.writeString(ingredient6)
        parcel.writeString(measure6)
        parcel.writeString(ingredient7)
        parcel.writeString(measure7)
        parcel.writeString(ingredient8)
        parcel.writeString(measure8)
        parcel.writeString(ingredient9)
        parcel.writeString(measure9)
        parcel.writeString(ingredient10)
        parcel.writeString(measure10)
        parcel.writeString(ingredient11)
        parcel.writeString(measure11)
        parcel.writeString(ingredient12)
        parcel.writeString(measure12)
        parcel.writeString(ingredient13)
        parcel.writeString(measure13)
        parcel.writeString(ingredient14)
        parcel.writeString(measure14)
        parcel.writeString(ingredient15)
        parcel.writeString(measure15)
        parcel.writeString(ingredient16)
        parcel.writeString(measure16)
        parcel.writeString(ingredient17)
        parcel.writeString(measure17)
        parcel.writeString(ingredient18)
        parcel.writeString(measure18)
        parcel.writeString(ingredient19)
        parcel.writeString(measure19)
        parcel.writeString(ingredient20)
        parcel.writeString(measure20)
        parcel.writeString(area)
        parcel.writeString(drinkAlternate)
        parcel.writeString(instructions)
        parcel.writeString(tags)
        parcel.writeString(youtube)
        parcel.writeString(source)
        parcel.writeString(imageSource)
        parcel.writeString(creativeCommonsConfirmed)
        parcel.writeString(dateModified)
        parcel.writeBoolean(saved)
        parcel.writeString(cookingDate)
        parcel.writeString(mealType)
        parcel.writeString(strMealThumb)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Meal> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): Meal {
            return Meal(parcel)
        }

        override fun newArray(size: Int): Array<Meal?> {
            return arrayOfNulls(size)
        }
    }
}