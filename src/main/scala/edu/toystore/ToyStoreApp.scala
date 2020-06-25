package edu.toystore

import edu.toystore.application.ExportAllStoresIncome

object ToyStoreApp extends App {
    println("Hello Toy Store!!")


    val storesIncome = new ExportAllStoresIncome()
    storesIncome.execute()


//    def getType(someValue: Any): String = someValue match {
//        case n: Int    => s"Int found! $n"
//        case s: String => s"String found! $s"
//        case n: Float  => s"Float found! $n"
//        case _         => "Invalid type!"
//    }
//
//    val name : String = "Diego"
//    val typeN: String = getType(name)
//    print(s"Type is: $typeN")
}
