package com.example.r.componenttest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.r.componenttest.classes.Categorie
import com.example.r.componenttest.classes.Genre
import com.example.r.componenttest.classes.Sous_Categorie
import com.example.r.componenttest.classes.Type

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("APP LAUNCH")

        /*INIT*/
        val categorie = Categorie(0, "", "")
        val type = Type(0, "")

        /*INSERT USAGE*/

        val Genre = Genre(0, "GenreName")
        Genre.insert(Genre, this)
        println("INSERT DONE")
        println()
        /*SELECT ALL USAGE*/
        println("SELECT * FROM SOUS CATEGORIE")
        val sous_categorie = Sous_Categorie(0, "", "", type, categorie)
        var Sous_CategorieArray: List<Sous_Categorie>
        Sous_CategorieArray = sous_categorie.selectAll(this)
        for( item in Sous_CategorieArray)
            println(item.categorie.nom + " - " + item.nom + " - " + item.type.nom)

        println()
        println("SELECT * FROM CATEGORIE WHERE ID = ?")
        /*SELECT BY ID USAGE*/
        println("CategorieName by id : " + categorie.select(1,this).nom)


    }
}
