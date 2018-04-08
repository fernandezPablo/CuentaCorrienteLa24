package Presenter

import Service.FileManager
import Service.SqliteManager
import View.MainView

fun main(args: Array<String>){
    SqliteManager.instance.createDb(FileManager.instance.readTextFile("CurrentAccountsLa24Tables.db.sql"))
    val mainView = MainView()
}