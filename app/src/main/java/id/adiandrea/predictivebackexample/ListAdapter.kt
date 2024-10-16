package id.adiandrea.predictivebackexample

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.adiandrea.predictivebackexample.databinding.ItemListBinding

class ListAdapter(val data: MutableList<Pokemon>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.name.text = data[position].name
            Glide.with(itemView.context)
                .load(data[position].imageURL)
                .into(binding.image)

            itemView.setOnClickListener {
                itemView.context.startActivity(
                    Intent(
                        itemView.context,
                        DetailActivity::class.java
                    )
                        .putExtra("name", data[position].name)
                        .putExtra("image", data[position].imageURL)
                )
            }
        }
    }

}