package edu.toystore

import edu.toystore.domain.catalog.{Catalog, Toy}
import edu.toystore.domain.sales.Sale

/* ??? Esto parece no estar acabado */
object ToyStoreFunctional extends App {

    def readFromCSV(path: String, delimiter: String): List[List[String]] = {
        val bufferedSource = io.Source.fromFile(path)

        val mappedRows = bufferedSource
            .getLines
            .drop(1)
            .map(line => {
                line.split(delimiter)
                    .map(_.trim)
                    .toList

            }).toList

        bufferedSource.close
        mappedRows
    }

//    def exportToCSV(list, path: String, delimiter: String): Unit = {
//        //@TODO save in csv
//    }

    def getSales: List[Sale] = {
        val path      = "/Users/dzavaleta/Documents/MDAS/Paradigmas/toystore/src/main/resources/input/sales.csv"
        val delimiter = ";"
        readFromCSV(path, delimiter).map(row => Sale(row.head, row(1), row(2)))
    }


    def getCatalog: Catalog = {
        val path       = "/Users/dzavaleta/Documents/MDAS/Paradigmas/toystore/src/main/resources/input/catalog.csv"
        val delimiter  = ";"
        val listValues = readFromCSV(path, delimiter).map(row => Toy(row.head, row(1), row(2)))
        Catalog(listValues)
    }

    def getSalesWithTotalPrice(sales: List[Sale], catalog: Catalog) = {
        val result = sales
            .map(sale => (sale.storeId.value, catalog.toyPrice(sale.toyId).value * sale.quantity))

        val withTotalPrice = result
            .groupBy(_._1)
            .mapValues(x => x.map(_._2).sum)
            .toList

        withTotalPrice
//        println("groupped by store ID")
//        print(groupped)
//        println("\nmapped for sum prices")
//        println(mapped)
//        identity()
    }

//    List((1,1840.2), (2,1193.7799), (3,594.24), (4,349.87))


    val sales = getSales
//    println(sales)

    val catalog = getCatalog
//    println(catalog)

    val salesWithTotalPrice = getSalesWithTotalPrice(sales, catalog)
    println(salesWithTotalPrice)


}



