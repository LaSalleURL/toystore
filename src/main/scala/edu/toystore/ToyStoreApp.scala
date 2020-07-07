package edu.toystore

import edu.toystore.application.ExportAllStoresIncome
import edu.toystore.infrastructure.{CSVSaleRepository, CSVToyRepository}

object ToyStoreApp extends App {
    println("Hello Toy Store!!")

    println("======= ALL STORES INCOME ========")
    val storesIncome = new ExportAllStoresIncome(new CSVSaleRepository(), new CSVToyRepository())
    storesIncome.execute()


//    println("======= ALL STORES INCOME ========")
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
