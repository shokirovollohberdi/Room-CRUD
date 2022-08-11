package uz.shokirov.roominkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import uz.shokirov.roominkotlin.databinding.FragmentAddBinding
import uz.shokirov.roominkotlin.model.User
import uz.shokirov.roominkotlin.viewmodel.UserViewModel


class addFragment : Fragment() {
    lateinit var binding: FragmentAddBinding
    private lateinit var mUserViewModel: UserViewModel
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater)

     //   mUserViewModel = ViewModelProvider(activity).get(UserViewModel::class.java)

        mUserViewModel = ViewModelProvider(activity!!).get(UserViewModel::class.java)

        binding.btnSave.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    private fun insertDataToDatabase() {
        var firstName = binding.edtFirstName.text.toString()
        var lastName = binding.edtLastName.text.toString()
        var age = binding.edtage.text.toString().toInt()

        if (inputCheck(firstName, lastName, age)) {
            val user = User(0, firstName, lastName, age)
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(), "Please check datas", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Int): Boolean {
        return !(firstName.isEmpty() && lastName.isEmpty() && age.toString().isEmpty())
    }

}