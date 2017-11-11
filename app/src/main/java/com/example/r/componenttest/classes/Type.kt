package com.example.r.componenttest.classes

import android.content.Context
import com.example.r.componenttest.database
import org.jetbrains.anko.db.*
import java.util.ArrayList

/**
 * Created by R on 10/11/2017.
 */
class Type(val id: Int, val nom: String)
{
    /*SQL VARIABLES*/
    private val TABLE_NAME = "TYPE"
    private val COL_ID = "ID"
    private val COL_NOM = "NOM"

    /*INSERT NEW TYPE*/
    fun insert(type: Type, context: Context)
    {
        context.database.use {
            insert(TABLE_NAME,
                    COL_NOM to type.nom)
        }
    }

    /*SELECT ALL TYPE*/
    fun select(id: Int, context: Context): Type
    {

        var type = Type(0, "")
        context.database.use {
            val rowParser = classParser<Type>()
            select(TABLE_NAME, COL_ID,COL_NOM)
                    .whereArgs("(" +COL_ID+ " = {id_type})",
                            "id_type" to id)
                    .exec {
                        type = parseSingle(rowParser)
                    }
        }
        return type
    }

    /*SELECT TYPE BY ID*/
    fun selectAll(context: Context): List<Type>
    {

        var typeArray: List<Type> = ArrayList()
        context.database.use {
            val rowParser = classParser<Type>()
            select(TABLE_NAME, COL_ID, COL_NOM)
                    .exec {
                        typeArray = parseList(rowParser)
                    }

        }
        return typeArray
    }
}