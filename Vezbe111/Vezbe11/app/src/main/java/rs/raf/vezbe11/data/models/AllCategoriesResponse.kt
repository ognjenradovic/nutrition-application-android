package rs.raf.vezbe11.data.models
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AllCategoriesResponse(
    val categories: List<Category>
) {
    @JsonClass(generateAdapter = true)
    data class Category(
        val idCategory: String,
        val strCategory: String,
        val strCategoryThumb: String,
        val strCategoryDescription: String
    )
}