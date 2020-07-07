package edu.toystore.application

import edu.toystore.Utils
import edu.toystore.domain.catalog.{Catalog, ToyRepository}
import edu.toystore.domain.sales.{SaleRepository, Sales}

final class ExportAllStoresIncome(salesRepository: SaleRepository, toyRepository: ToyRepository) {


    def execute(): Unit = {
        val sales  : Sales   = salesRepository.all()
        val catalog: Catalog = toyRepository.all()

        def getSalesWithTotalPrice(sales: Sales, catalog: Catalog) = {
            val result = sales
                .items
                .map(sale => (sale.storeId.value, catalog.toyPrice(sale.toyId).value * sale.quantity))

            val withTotalPrice = result
                .groupBy(_._1)
                .mapValues(x => x.map(_._2).sum)
                .toList
                .map(item => List(item._1.toString, item._2.toString))

            withTotalPrice
        }

        val salesWithTotalPrice = getSalesWithTotalPrice(sales, catalog)
        val exportPath          = System.getProperty("user.dir") + "/income.csv"
        println("Exporting results to path: " + exportPath)
        Utils.exportToCSV(salesWithTotalPrice, exportPath)
    }
}
