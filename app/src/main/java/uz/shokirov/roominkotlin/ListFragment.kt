package uz.shokirov.roominkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.shokirov.roominkotlin.adapter.OnClick
import uz.shokirov.roominkotlin.adapter.UserAdapter
import uz.shokirov.roominkotlin.databinding.FragmentListBinding
import uz.shokirov.roominkotlin.model.User
import uz.shokirov.roominkotlin.viewmodel.UserViewModel


class ListFragment : Fragment() {
    lateinit var binding: FragmentListBinding
    private lateinit var mUserViewModel: UserViewModel

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)





        mUserViewModel = ViewModelProvider(activity!!).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            val adapter = UserAdapter(user,object :OnClick{
                override fun click(user: User) {
                    findNavController().navigate(R.id.updateFragment, bundleOf("user" to user))
                }

                @SuppressLint("NotifyDataSetChanged")
                override fun delete(user: User) {
                    mUserViewModel.userDelete(user)
                    binding.rv.adapter?.notifyDataSetChanged()
                }

            })
            binding.rv.adapter = adapter
        })

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.addFragment)
        }
        binding.btnDelete.setOnClickListener {
            mUserViewModel.deleteAllUsers()
        }

        return binding.root
    }

}