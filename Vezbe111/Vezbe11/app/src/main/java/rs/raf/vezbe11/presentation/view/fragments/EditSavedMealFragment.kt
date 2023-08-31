package rs.raf.vezbe11.presentation.view.fragments

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Parcelable
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val REQUEST_IMAGE_CAPTURE = 1
private const val REQUEST_CAMERA_PERMISSION = 2

class EditSavedMealFragment : Fragment() {
    private var param1: Parcelable? = null
    private var photo: Boolean = false
    private var param2: String? = null
    private var capturedImageBitmap: Bitmap? = null
    private lateinit var imageView: ImageView
    private lateinit var dateTextView: TextView
    private lateinit var categorySpinner: Spinner
    private var selectedDate: String = ""
    private var selectedMealType: String = ""
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable<Meal>(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_saved_meal_edit, container, false)
        val meal: Meal? = arguments?.getParcelable(ARG_PARAM1)

        imageView = view.findViewById(R.id.image_view1)
        dateTextView = view.findViewById(R.id.dateTextView)
        categorySpinner = view.findViewById(R.id.categorySpinner)
        val saveButton: Button = view.findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            saveMeal()
        }

        setupDatePicker()
        setupCategorySpinner()
        setupImageView()

        Picasso.get().load(meal?.imageSource).into(imageView)

        return view
    }

    private fun setupDatePicker() {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val currentDate = simpleDateFormat.format(calendar.time)
        dateTextView.text = currentDate

        dateTextView.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "$selectedDay.${selectedMonth + 1}.$selectedYear"
            dateTextView.text = selectedDate
            this.selectedDate = selectedDate
        }, year, month, day)

        datePicker.show()
    }

    private fun setupCategorySpinner() {
        val categories = arrayOf("Lunch", "Breakfast", "Snack", "Dinner")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedMealType = categories[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedMealType = ""
            }
        }
    }

    private fun setupImageView() {
        imageView.setOnClickListener {
            if (hasCameraPermission()) {
                openCamera()
            } else {
                requestCameraPermission()
            }
        }
    }

    private fun hasCameraPermission(): Boolean {
        val permission = Manifest.permission.CAMERA
        val result = ContextCompat.checkSelfPermission(requireContext(), permission)
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        val permission = Manifest.permission.CAMERA
        val requestCode = REQUEST_CAMERA_PERMISSION

        if (ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED) {
            // Permission already granted
            openCamera()
        } else {
            // Permission not granted, request it
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(permission), requestCode)
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Camera permission denied",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            capturedImageBitmap = data?.extras?.get("data") as Bitmap?
            imageView.setImageBitmap(capturedImageBitmap)

        }
    }

    private fun saveMeal() {
        val meal = param1 as Meal
        meal.saved=true;
        val file = File(context?.filesDir, "image.jpg")

        try {
            // Create an output stream
            val outputStream = FileOutputStream(file)

            // Compress the bitmap into a JPEG format and save it to the file
            capturedImageBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            photo=true
            // Close the output stream
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        meal.cookingDate=selectedDate
        meal.mealType=selectedMealType
            meal.imageSource=file.toURI().toString()

            meal.strMealThumb=file.toURI().toString()
            mainViewModel.setMealAsSaved(meal.id,file.toURI().toString(), selectedDate, selectedMealType)



        mainViewModel.addMeal(meal)
        Snackbar.make(
            requireView(),
            "Successfully saved meal",
            Snackbar.LENGTH_SHORT
        ).show()

        exitFragment()
    }

    private fun exitFragment() {
        requireActivity().supportFragmentManager.popBackStack()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Meal, param2: String): EditSavedMealFragment {
            val fragment = EditSavedMealFragment()
            val args = Bundle().apply {
                putParcelable(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
