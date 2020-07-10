package edu.toystore

import edu.toystore.application.{ExportAllStoresIncome, ExportAllStoresStock}
import edu.toystore.infrastructure.{CSVSaleRepository, CSVStockRepository, CSVStoreRepository, CSVStoreSalesRepository, CSVToyRepository}

object ToyStoreApp extends App {
    println("Hello Toy Store!!")

//    println("======= ALL STORES INCOME ========")
//    val storesIncome = new ExportAllStoresIncome(new CSVSaleRepository, new CSVToyRepository, new CSVStoreSalesRepository)
//    storesIncome.execute()


    println("======= REFILL ========")
    val storesStock = new ExportAllStoresStock(new CSVStockRepository, new CSVStoreRepository)
    storesStock.execute()
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
