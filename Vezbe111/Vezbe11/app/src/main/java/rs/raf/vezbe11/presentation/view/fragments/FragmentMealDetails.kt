package rs.raf.vezbe11.presentation.view.fragments


import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.states.MealDetailsState
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentMealDetails : Fragment() {
    private var param1: Parcelable? = null
    private var param2: String? = null



    private lateinit var nameTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var ingredientsTable: TableLayout
    private lateinit var imageView: ImageView
    private lateinit var areaTextView: TextView
    private lateinit var drinkAlternateTextView: TextView
    private lateinit var instructionsTextView: TextView
    private lateinit var tagsTextView: TextView
    private lateinit var youtubeTextView: TextView
    private lateinit var sourceTextView: TextView
    private lateinit var creativeCommonsConfirmedTextView: TextView
    private lateinit var dateModifiedTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var mealType: TextView
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable<Meal>(ARG_PARAM1) ?: throw IllegalArgumentException("Missing Meal argument")
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meal_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameTextView = view.findViewById(R.id.nameTextView)
        descriptionTextView = view.findViewById(R.id.descriptionTextView)
        ingredientsTable = view.findViewById(R.id.ingredientsTable)
        imageView = view.findViewById(R.id.image_view1)
        areaTextView = view.findViewById(R.id.areaTextView)
        drinkAlternateTextView = view.findViewById(R.id.drinkAlternateTextView)
        instructionsTextView = view.findViewById(R.id.instructionsTextView)
        tagsTextView = view.findViewById(R.id.tagsTextView)
        youtubeTextView = view.findViewById(R.id.youtubeTextView)
        sourceTextView = view.findViewById(R.id.sourceTextView)
        creativeCommonsConfirmedTextView = view.findViewById(R.id.creativeCommonsConfirmedTextView)
        dateModifiedTextView = view.findViewById(R.id.dateModifiedTextView)
        dateTextView = view.findViewById(R.id.dateTextView)
        mealType = view.findViewById(R.id.mealTypeTextView)
        saveButton = view.findViewById(R.id.saveButton)
        saveButton.setOnClickListener { openMealDetailsFragment() }
        initObservers();

    }

    private fun initObservers() {
        mainViewModel.mealDetailsState.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })
        mainViewModel.fetchMealDetails((param1 as Meal).id)
    }

    private fun renderUi(meal: Meal) {
        areaTextView.text = meal.area ?: "Not available"
        drinkAlternateTextView.text = meal.drinkAlternate ?: "Not available"
        instructionsTextView.text = meal.instructions ?: "Not available"
        tagsTextView.text = meal.tags ?: "Not available"
        youtubeTextView.text = meal.youtube ?: "Not available"
        sourceTextView.text = meal.source ?: "Not available"
        creativeCommonsConfirmedTextView.text = meal.creativeCommonsConfirmed ?: "Not available"
        dateModifiedTextView.text = meal.dateModified ?: "Not available"
        dateTextView.text = meal.cookingDate ?: "Not available"
        mealType.text = meal.mealType ?: "Not available"
        nameTextView.text = meal.name ?: "Not available"
        descriptionTextView.text = meal.category ?: "Not available"
        Picasso.get().load(meal.imageSource).into(imageView)
        addIngredientsToTable(meal, ingredientsTable)
    }

    private fun addIngredientsToTable(meal: Meal?, tableLayout: TableLayout) {
        tableLayout.removeAllViews()
        val ingredients = arrayOf(
            meal?.ingredient1, meal?.ingredient2, meal?.ingredient3, meal?.ingredient4,
            meal?.ingredient5, meal?.ingredient6, meal?.ingredient7, meal?.ingredient8,
            meal?.ingredient9, meal?.ingredient10, meal?.ingredient11, meal?.ingredient12,
            meal?.ingredient13, meal?.ingredient14, meal?.ingredient15, meal?.ingredient16,
            meal?.ingredient17, meal?.ingredient18, meal?.ingredient19, meal?.ingredient20
        )

        val measures = arrayOf(
            meal?.measure1, meal?.measure2, meal?.measure3, meal?.measure4,
            meal?.measure5, meal?.measure6, meal?.measure7, meal?.measure8,
            meal?.measure9, meal?.measure10, meal?.measure11, meal?.measure12,
            meal?.measure13, meal?.measure14, meal?.measure15, meal?.measure16,
            meal?.measure17, meal?.measure18, meal?.measure19, meal?.measure20
        )

        for (i in ingredients.indices) {
            val ingredient = ingredients[i]
            val measure = measures[i]

            if (ingredient != null && measure != null) {
                val row = TableRow(requireContext())

                val ingredientTextView = TextView(requireContext())
                ingredientTextView.text = ingredient
                ingredientTextView.layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
                row.addView(ingredientTextView)

                val measureTextView = TextView(requireContext())
                measureTextView.text = measure
                measureTextView.layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
                row.addView(measureTextView)

                tableLayout.addView(row)
            }
        }
    }



    private fun renderState(state: MealDetailsState) {
        when (state) {
            is MealDetailsState.Success -> {
                showLoadingState(false)
                renderUi(state.meals.meals[0])
            }
            is MealDetailsState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is MealDetailsState.DataFetched -> {
                showLoadingState(false)
               // renderUi(state.meals.meals[0])
                // Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is MealDetailsState.Loading -> {
                showLoadingState(true)
            }
        }
    }

            private fun showLoadingState(loading: Boolean) {
                // Implement the logic to show or hide loading state
            }

            private fun openMealDetailsFragment() {
                val fragment = EditSavedMealFragment.newInstance(param1 as Meal, "jelo")
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(android.R.id.content, fragment)
                    .addToBackStack(null)
                    .commit()
            }

            companion object {
                private const val ARG_PARAM1 = "param1"
                private const val ARG_PARAM2 = "param2"

                @JvmStatic
                fun newInstance(param1: Meal, param2: String): FragmentMealDetails {
                    return FragmentMealDetails().apply {
                        arguments = Bundle().apply {
                            putParcelable(ARG_PARAM1, param1)
                            putString(ARG_PARAM2, param2)
                        }
                    }

        }
        }


}



