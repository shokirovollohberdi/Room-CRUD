package uz.shokirov.roominkotlin.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.shokirov.roominkotlin.ListFragment
import uz.shokirov.roominkotlin.databinding.ItemRvBinding
import uz.shokirov.roominkotlin.model.User

class UserAdapter(/*var list: ArrayList<User>*/var userList: List<User>,var onClick: OnClick) :
    RecyclerView.Adapter<UserAdapter.Vh>() {


    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        @SuppressLint("ResourceAsColor", "SetTextI18n")
        fun onBind(user: User, position: Int) {
            itemRv.name.text = user.firstName+" "+user.lastName
            itemRv.age.text = user.age.toString()
            itemRv.id.text = user.id.toString()
            itemRv.root.setOnClickListener {
                onClick.click(user)
            }
            itemRv.imageDelete.setOnClickListener {
                onClick.delete(user)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(userList[position], position)
    }

    override fun getItemCount(): Int = userList.size

}interface OnClick{
    fun click(user: User)
    fun delete(user:User)
}
