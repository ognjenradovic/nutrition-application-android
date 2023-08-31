package rs.raf.vezbe11.presentation.view.fragments

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.Category
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.databinding.FragmentListBinding
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.recycler.adapter.MealAdapter
import rs.raf.vezbe11.presentation.view.states.MealsState
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel
import timber.log.Timber
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ListMealFragment : Fragment(R.layout.fragment_list_meals) {
    private var param1: Parcelable? = null
    private var param2: String? = null
    private var page: Int=1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable<Meal>(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    // Koristimo by sharedViewModel jer sada view modele instanciramo kroz koin
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()

    private var _binding: FragmentListBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: MealAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the desired layout file based on the layout resource ID
        val rootView = inflater.inflate(R.layout.fragment_list_meals, container, false)
        val next=rootView.findViewById<Button>(R.id.nextPage1);
        val previous=rootView.findViewById<Button>(R.id.previousPage1);


       next.setOnClickListener {
            loadNextPage()
        }
        previous.setOnClickListener {
            previousPage()
        }

        // Initialize the _binding variable using DataBindingUtil
        _binding = FragmentListBinding.bind(rootView)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun loadNextPage() {
        page++
        mainViewModel.fetchAllMealsByCategory((param1 as Category).name,page);
    }
    private fun previousPage() {
        page--
        mainViewModel.fetchAllMealsByCategory((param1 as Category).name,page);
    }

    private fun init() {
        adapter = MealAdapter { meal: Meal ->
            openMealDetailsFragment(meal)
        }
        initUi()
        initObservers()
        binding.recyclerView.adapter = adapter
    }



    private fun openMealDetailsFragment(meal: Meal) {
        val fragment = FragmentMealDetails.newInstance(meal, "jelo")
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, fragment)
            .addToBackStack(null)
            .commit()
    }


    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }

    private fun initListeners() {
        binding.searchEditText.doAfterTextChanged { searchText ->
            val filter = searchText.toString();
            mainViewModel.getMealsByCategoryAndName((param1 as Category).name,filter,page);
        }
    }

    private fun initObservers() {
        mainViewModel.mealsState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })
        // Pravimo subscription kad observablu koji je vezan za getAll iz baze
        // Na svaku promenu tabele, obserrvable ce emitovati na onNext sve elemente
        // koji zadovoljavaju query
        if(param1!=null){
            mainViewModel.fetchAllMealsByCategory((param1 as Category).name,page);
        }

        // Pokrecemo operaciju dovlacenja podataka sa servera, kada podaci stignu,
        // bice sacuvani u bazi, tada ce se triggerovati observable na koji smo se pretplatili
        // preko metode getAllMeals()

    }

    private fun renderState(state: MealsState) {
        when (state) {
            is MealsState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.meals.meals)
            }
            is MealsState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is MealsState.DataFetched -> {
                showLoadingState(false)
                // /* /*Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show())*/)*/
            }
            is MealsState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        if (loading) {
            binding.loadingProgressBar.visibility = View.VISIBLE
            binding.searchEditText.isEnabled = false

            binding.searchEditText.postDelayed({
                binding.loadingProgressBar.visibility = View.GONE
                binding.searchEditText.isEnabled = true

            }, 2000)
        } else {
            binding.loadingProgressBar.visibility = View.GONE
            binding.searchEditText.isEnabled = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListMealFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Category, param2: String): ListMealFragment {
            val fragment = ListMealFragment()
            val args = Bundle().apply {
                putParcelable(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
            fragment.arguments = args
            return fragment
        }

    }

}