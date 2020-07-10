package edu.toystore

import edu.toystore.application.{ExportAllStoresBalance, ExportAllStoresIncome, ExportAllStoresStock}
import edu.toystore.infrastructure.{
    CSVImbalanceRepository, CSVSaleRepository, CSVStockRepository,
    CSVStoreRepository, CSVStoreSalesRepository, CSVStoreStockRepository, CSVToyRepository
}

import scala.annotation.tailrec

object ToyStoreApp extends App {

    @tailrec
    final def printMenu(): Unit = {
        println("Select your choice:")
        println("1. Stores income")
        println("2. Stores stock")
        println("3. Stores balance")
        println("4. Exit")

        val cmd = scala.io.StdIn.readLine()
        cmd match {
            case "1" =>
                val storesIncome = new ExportAllStoresIncome(
                    new CSVSaleRepository, new CSVToyRepository, new CSVStoreSalesRepository
                )
                storesIncome.execute()
                println("Exported successfully.. \n")
                printMenu()

            case "2" =>
                val storesStock = new ExportAllStoresStock(
                    new CSVStockRepository, new CSVStoreRepository, new CSVStoreStockRepository
                )
                storesStock.execute()
                println("Exported successfully.. \n")
                printMenu()

            case "3" =>
                val imbalance = new ExportAllStoresBalance(
                    new CSVStockRepository, new CSVSaleRepository, new CSVImbalanceRepository
                )
                imbalance.execute()
                println("Exported successfully.. \n")
                printMenu()

            case "4" => ()
            case _   => printMenu()
        }
    }

    printMenu()

}
