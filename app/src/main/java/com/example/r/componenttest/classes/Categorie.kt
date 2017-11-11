package com.example.r.componenttest.classes

import android.content.Context
import com.example.r.componenttest.database
import org.jetbrains.anko.db.*
import java.util.ArrayList

/**
 * Created by R on 08/11/2017.
 */
class Categorie(val id: Int, val nom: String, val image_path: String) {


    fun insert(categorie: Categorie, context: Context)
    {
        context.database.use {
            insert("CATEGORIE",
                    "NOM" to categorie.nom,
                    "IMAGE_PATH" to categorie.image_path)
        }
    }

    fun selectAll(context: Context): List<Categorie>
    {
        var categorieArray: List<Categorie> = ArrayList()
        println("Categorie.selectAll()")
        context.database.use {
            val rowParser = classParser<Categorie>()
            select("CATEGORIE", "ID","NOM","IMAGE_PATH")
                    .exec {
                        categorieArray = parseList(rowParser)
                    }

        }
        return categorieArray
    }

    fun select(id_categorie: Int, context: Context): Categorie
    {
        var categorie = Categorie(0, "", "")

        context.database.use {
            val rowParser = classParser<Categorie>()
            select("CATEGORIE", "ID","NOM","IMAGE_PATH")
                    .whereArgs("(ID = {id_categorie})",
                            "id_categorie" to id_categorie)
                    .exec {
                        categorie = parseSingle(rowParser)
                    }
        }
        return categorie
    }
    }

