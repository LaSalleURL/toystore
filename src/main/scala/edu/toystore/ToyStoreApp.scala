package edu.toystore

import edu.toystore.application.{ExportAllStoresBalance, ExportAllStoresIncome, ExportAllStoresStock}
import edu.toystore.infrastructure.{
    CSVImbalanceRepository, CSVSaleRepository, CSVStockRepository,
    CSVStoreRepository, CSVStoreSalesRepository, CSVStoreStockRepository, CSVToyRepository
}

import scala.annotation.tailrec

/*
    No has dedicado demasiado tiempo a la asignatura durante su transcurso y esto se nota en la práctica.
    No vas mal encaminado, pero te habría sido de gran utilidad trabajar (al menos las horas que le hemos dedicado a la práctica)
    para recibir los inputs de cosas simples en las que has fallado. Tu nota habría sido otra con diferencia.
*/
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
                /* Output erróneo, el resultado no sale ordenado y con un número erróneo de decimales. No se hace error handling de precio <= 0 */
                val storesIncome = new ExportAllStoresIncome(
                    new CSVSaleRepository, new CSVToyRepository, new CSVStoreSalesRepository
                )
                storesIncome.execute()
                println("Exported successfully.. \n")
                printMenu()

            case "2" =>
                /* Output erróneo, el resultado no sale ordenado. */
                val storesStock = new ExportAllStoresStock(
                    new CSVStockRepository, new CSVStoreRepository, new CSVStoreStockRepository
                )
                storesStock.execute()
                println("Exported successfully.. \n")
                printMenu()

            case "3" =>
                /* Output completamente erroneo */
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
