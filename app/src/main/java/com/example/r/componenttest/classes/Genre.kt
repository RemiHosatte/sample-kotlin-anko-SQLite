package com.example.r.componenttest.classes

import android.content.Context
import com.example.r.componenttest.database
import org.jetbrains.anko.db.*
import java.util.ArrayList

/**
 * Created by R on 11/11/2017.
 */
class Genre (val id: Int, val nom: String)
{
    /*SQL VARIABLES*/
    private val TABLE_NAME = "GENRE"
    private val COL_ID = "ID"
    private val COL_NOM = "NOM"

    /*INSERT NEW GENRE*/
    fun insert(type: Genre, context: Context)
    {
        context.database.use {
            insert(TABLE_NAME,
                    COL_NOM to type.nom)
        }
    }

    /*SELECT ALL GENRE*/
    fun select(id: Int, context: Context): Genre
    {

        var genre = Genre(0, "")
        context.database.use {
            val rowParser = classParser<Genre>()
            select(TABLE_NAME, COL_ID,COL_NOM)
                    .whereArgs("(" +COL_ID+ " = {id_genre})",
                            "id_genre" to id)
                    .exec {
                        genre = parseSingle(rowParser)
                    }
        }
        return genre
    }

    /*SELECT GENRE BY ID*/
    fun selectAll(context: Context): List<Genre>
    {

        var genreArray: List<Genre> = ArrayList()
        context.database.use {
            val rowParser = classParser<Genre>()
            select(TABLE_NAME, COL_ID, COL_NOM)
                    .exec {
                        genreArray = parseList(rowParser)
                    }

        }
        return genreArray
    }
}