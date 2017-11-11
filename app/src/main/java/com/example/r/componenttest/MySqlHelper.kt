package com.example.r.componenttest

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

/**
 * Created by R on 08/11/2017.
 */
class MySqlHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "mydb",null, 9) {

    companion object {
        private var instance: MySqlHelper? = null
//name, factory, version
        @Synchronized
        fun getInstance(ctx: Context): MySqlHelper {
            if (instance == null) {
                instance = MySqlHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    private val CREATE_TABLE_CATEGORIE = "CREATE TABLE CATEGORIE" +
            "(" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "NOM TEXT NOT NULL," +
            "IMAGE_PATH TEXT NOT NULL" +
            ");"

    private val INSERT_TABLE_CATEGORIE = "INSERT INTO CATEGORIE (NOM, IMAGE_PATH) VALUES " +
            "('Nintendo', 'nintendo')," +
            "('Sega', 'sega')," +
            "('Sony', 'sony')," +
            "('Microsoft', 'microsoft')," +
            "('PC', 'pc');"
    private val CREATE_TABLE_TYPE = "CREATE TABLE TYPE" +
            "(" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "NOM TEXT NOT NULL" +
            ");"

    private val INSERT_TABLE_TYPE = "INSERT INTO TYPE (NOM) VALUES" +
    "('Portable')," +
    "('Console')," +
    "('Unknown');"

    private val CREATE_TABLE_SOUS_CATEGORIE = "CREATE TABLE SOUS_CATEGORIE" +
            "(" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "NOM INTEGER NOT NULL," +
            "IMAGE_PATH TEXT NOT NULL," +
            "ID_TYPE INTEGER NOT NULL," +
            "ID_CATEGORIE," +
            "FOREIGN KEY(ID_CATEGORIE) REFERENCES CATEGORIE(ID)," +
            "FOREIGN KEY(ID_TYPE) REFERENCES TYPE(ID)" +
            ");"

     private val INSERT_TABLE_SOUS_CATEGORIE = "INSERT INTO SOUS_CATEGORIE (NOM, IMAGE_PATH, ID_TYPE, ID_CATEGORIE) VALUES" +
     "('Game Boy & Color','game_boy_color',1,1)," +
     "('Game Boy Advance','game_boy_advance',1,1)," +
     "('DS','ds',1,1)," +
     "('3DS','3ds',1,1)," +
     "('Nes','nes',2,1)," +
     "('Super Nintendo','super_nintendo',2,1)," +
     "('64','64',2,1)," +
     "('Gamecube','gamecube',2,1)," +
     "('Wii','wii',2,1)," +
     "('Wii U','wii_u',2,1)," +

     "('Game Gear','game_gear',1,2)," +
     "('Master System','master_system',2,2)," +
     "('Mega Drive','mega_drive',2,2)," +
     "('Mega CD','mega_cd',2,2)," +
     "('32X','32x',2,2)," +
     "('Saturne','saturne',2,2)," +
     "('Dreamcast','dreamcast',2,2)," +

     "('Psp','psp',1,3)," +
     "('Vita','vita',1,3)," +
     "('Playstation 1','ps1',2,3)," +
     "('Playstation 2','ps2',2,3)," +
     "('Playstation 3','ps3',2,3)," +
     "('Playstation 4','ps4',2,3)," +

     "('Xbox','xbox',2,4)," +
     "('Xbox 360','xbox_360',2,4)," +
     "('Xbox One','xbox_one',2,4)," +

     "('1980-89','1980_89',3,5)," +
     "('1990-99','1990_99',3,5)," +
     "('2000-09','2000_09',3,5)," +
     "('2010-19','2010_19',3,5);"

    private val CREATE_TABLE_GENRE = "CREATE TABLE GENRE" +
            "(" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "NOM TEXT NOT NULL" +
            ");"

    private val INSERT_TABLE_GENRE = "INSERT INTO GENRE (NOM) VALUES" +
    "('Action')," +
    "('Fantastique')," +
    "('Simulation');"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_CATEGORIE)
        db.execSQL(INSERT_TABLE_CATEGORIE)
        db.execSQL(CREATE_TABLE_TYPE)
        db.execSQL(INSERT_TABLE_TYPE)
        db.execSQL(CREATE_TABLE_SOUS_CATEGORIE)
        db.execSQL(INSERT_TABLE_SOUS_CATEGORIE)
        db.execSQL(CREATE_TABLE_GENRE)
        db.execSQL(INSERT_TABLE_GENRE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + "GAME")
        db.execSQL("DROP TABLE IF EXISTS " + "GENRE")
        db.execSQL("DROP TABLE IF EXISTS " + "SOUS_CATEGORIE")
        db.execSQL("DROP TABLE IF EXISTS " + "TYPE")
        db.execSQL("DROP TABLE IF EXISTS " + "CATEGORIE")
        onCreate(db)

    }


}

// Access property for Context
val Context.database: MySqlHelper
    get() = MySqlHelper.getInstance(applicationContext)

