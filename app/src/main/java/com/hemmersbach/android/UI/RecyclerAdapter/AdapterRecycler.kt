package com.hemmersbach.android.UI.RecyclerAdapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.hemmersbach.android.BR
import com.hemmersbach.android.Extention.inflate
import com.hemmersbach.android.Jokes
import com.hemmersbach.android.R
import com.hemmersbach.android.databinding.ListItemBinding
import kotlinx.android.synthetic.main.list_item.view.*

typealias JokeCallBack = (Jokes, Jokes) -> Unit

class RemoteRecycler(private val typeIcon: TypeIcon) :
    RecyclerView.Adapter<RemoteRecycler.ViewHolder>() {
    private var jokesData: MutableList<Jokes> = mutableListOf()
    var addToDataBase: JokeCallBack? = null
    var removeFromDatabase: JokeCallBack? = null

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): RemoteRecycler.ViewHolder {
        val view: ListItemBinding = DataBindingUtil.inflate(
            viewGroup.inflate(),
            R.layout.list_item,
            viewGroup,
            false
        )

        return ViewHolder(view, typeOfLambda(), getIcon(viewGroup.context))
    }

    private fun typeOfLambda(): JokeCallBack? {
        return when (typeIcon) {
            TypeIcon.LOCAL -> removeFromDatabase
            TypeIcon.REMOTE -> addToDataBase
        }
    }

    private fun getIcon(context: Context): Drawable {
        return when (typeIcon) {
            TypeIcon.LOCAL -> ContextCompat.getDrawable(
                context,
                R.drawable.ic_indeterminate_check_box_black_24dp
            )!!
            TypeIcon.REMOTE -> ContextCompat.getDrawable(
                context,
                R.drawable.ic_add_circle_black_24dp
            )!!
        }
    }

    override fun getItemCount(): Int = jokesData.size

    override fun onBindViewHolder(viewGroup: ViewHolder, position: Int) {
        viewGroup.bindView(jokesData[position])
    }

    fun swapData(jokeModel: MutableList<Jokes>) {
        jokesData.clear()
        jokesData = jokeModel
        notifyDataSetChanged()
    }

    fun removeJoke(joke: Jokes) {
        val index = jokesData.indexOf(joke)
        jokesData.remove(joke)
        notifyItemRemoved(index)
    }

    class ViewHolder(
        private val view: ViewDataBinding,
        private val actionOnDatabase: JokeCallBack?,
        private val drawable: Drawable
    ) :
        RecyclerView.ViewHolder(view.root) {
        fun bindView(joke: Jokes) {

            view.setVariable(BR.jokeModel, joke)
            view.setVariable(BR.image, drawable)
            view.executePendingBindings()
            view.root.addButton.setOnClickListener { actionOnDatabase(joke) }
        }

        private fun actionOnDatabase(value: Jokes) {
            val rating = view.root.ratingBar.rating
            val newJoke = createNewJoke(value, rating)
            actionOnDatabase?.invoke(newJoke, value)
        }

        private fun createNewJoke(joke: Jokes, rating: Float) =
            Jokes(joke.id, joke.categories, joke.joke, rating)
    }
}

enum class TypeIcon {
    LOCAL,
    REMOTE
}