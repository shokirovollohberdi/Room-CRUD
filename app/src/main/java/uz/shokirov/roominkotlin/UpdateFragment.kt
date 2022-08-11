package uz.shokirov.roominkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import uz.shokirov.roominkotlin.databinding.FragmentUpdateBinding
import uz.shokirov.roominkotlin.model.User
import uz.shokirov.roominkotlin.viewmodel.UserViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UpdateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateFragment : Fragment() {
    lateinit var binding:FragmentUpdateBinding
    lateinit var user: User
    private lateinit var mUserViewModel: UserViewModel

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(layoutInflater)

        user = arguments?.getSerializable("user") as User
        mUserViewModel = ViewModelProvider(activity!!).get(UserViewModel::class.java)


        binding.edtFirstName.setText(user.firstName)
        binding.edtLastName.setText(user.lastName)
        binding.edtage.setText(user.age.toString())


        binding.btnSave.setOnClickListener {
            updateItem()
        }

        return binding.root
    }

    private fun updateItem() {
        var firstName = binding.edtFirstName.text.toString()
        var lastName = binding.edtLastName.text.toString()
        var age = binding.edtage.text.toString().toInt()

        if (inputCheck(firstName, lastName, age)) {
            val userUpdate = User(user.id, firstName, lastName, age)

            mUserViewModel.userUpdate(userUpdate)
            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(), "Please check datas", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Int): Boolean {
        return !(firstName.isEmpty() && lastName.isEmpty() && age.toString().isEmpty())
    }

}