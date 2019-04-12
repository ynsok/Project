package com.hemmersbach.android.Convert

import com.hemmersbach.android.Jokes
import com.hemmersbach.android.JokesModel

object ConvertDataType {

    fun convertJokesType(jokesModel: JokesModel): MutableList<Jokes> {
        val newList: MutableList<Jokes> = mutableListOf()
        val listOfJokes = jokesModel.value

        for (i in listOfJokes.indices) {
            var category = ""
            if (!listOfJokes[i].categories.isEmpty()) category = listOfJokes[i].categories[0]
            newList.add(
                Jokes(
                    id = listOfJokes[i].id,
                    categories = category,
                    joke = listOfJokes[i].joke,
                    rating = listOfJokes[i].rating
                )
            )
        }
        return newList
    }
}