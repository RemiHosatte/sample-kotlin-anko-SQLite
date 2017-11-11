package com.example.r.componenttest.classes

import android.content.Context
import com.example.r.componenttest.database
import org.jetbrains.anko.db.*
import java.util.ArrayList

/**
 * Created by R on 10/11/2017.
 */
class Sous_Categorie(var id: Int, var nom: String, var image_path: String, var type: Type, var categorie: Categorie) {
    /*SQL VARIABLES*/
    private val TABLE_NAME = "SOUS_CATEGORIE"
    private val COL_ID = "ID"
    private val COL_NOM = "NOM"
    private val COL_IMAGE_PATH = "IMAGE_PATH"
    private val COL_TYPE = "ID_TYPE"
    private val COL_CATEGORIE = "ID_CATEGORIE"

    /*INSERT SOUS_CATEGORIE*/
    fun insert(sous_Categorie: Sous_Categorie, context: Context) {
        context.database.use {
            insert(TABLE_NAME,
                    COL_NOM to sous_Categorie.nom,
                    COL_IMAGE_PATH to sous_Categorie.image_path,
                    COL_TYPE to sous_Categorie.type.id,
                    COL_CATEGORIE to sous_Categorie.categorie.id)
        }
    }

    /*SELECT SOUS_CATEGORIE*/
    fun select(id: Int, context: Context): Sous_Categorie {
        var sous_categorie = Sous_Categorie(0, "", "", type, categorie)
        context.database.use {

            select(TABLE_NAME, COL_ID, COL_NOM, COL_IMAGE_PATH, COL_TYPE, COL_CATEGORIE)
                    .whereArgs("(" + COL_ID + " = {id_sous_categorie})",
                            "id_sous_categorie" to id)
                    .exec {
                        parseSingle(
                                object : RowParser<Sous_Categorie> {
                                    override fun parseRow(columns: Array<Any?>): Sous_Categorie {

                                        val _id = columns[0] as Long
                                        val _nom = columns[1] as String
                                        val _image_path = columns[2] as String

                                        val _type_id = columns[3] as Long
                                        type = type.select(_type_id.toInt(), context)

                                        val _categorie_id = columns[4] as Long
                                        categorie = categorie.select(_categorie_id.toInt(), context)
                                        sous_categorie = Sous_Categorie(_id.toInt(), _nom, _image_path, type, categorie)
                                        return sous_categorie
                                    }
                                })
                    }
        }
        return sous_categorie
    }

    /*SELECT ALL SOUS_CATEGORIE*/
    fun selectAll(context: Context): ArrayList<Sous_Categorie> {
        val Sous_CategorieArray = ArrayList<Sous_Categorie>()
        context.database.use {
            select(TABLE_NAME, COL_ID, COL_NOM, COL_IMAGE_PATH, COL_TYPE, COL_CATEGORIE)
                    .exec {
                        parseList(
                                object : RowParser<ArrayList<Sous_Categorie>> {
                                    override fun parseRow(columns: Array<Any?>): ArrayList<Sous_Categorie> {
                                        val _id = columns[0] as Long
                                        val _nom = columns[1]
                                        val _image_path = columns[2]

                                        val _type_id = columns[3] as Long
                                        type = type.select(_type_id.toInt(), context)

                                        val _categorie_id = columns[4] as Long
                                        categorie = categorie.select(_categorie_id.toInt(), context)

                                        var sous_categorie = Sous_Categorie(_id.toInt(), _nom.toString(), _image_path.toString(), type, categorie)
                                        Sous_CategorieArray.add(sous_categorie)

                                        return Sous_CategorieArray
                                    }
                                })
                    }
        }
        return Sous_CategorieArray
    }
}

