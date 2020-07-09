package edu.toystore.application

import edu.toystore.Utils
import edu.toystore.domain.catalog.{Catalog, ToyRepository}
import edu.toystore.domain.sales.{SaleRepository, Sales}

final class ExportAllStoresIncome(salesRepository: SaleRepository, toyRepository: ToyRepository) {


    def execute(): Unit = {
        val sales  : Sales   = salesRepository.all()
        val catalog: Catalog = toyRepository.all()

        val salesWithTotalPriceWithHeaders = getSalesWithTotalPrice(sales, catalog)
            .prepended(List("Store Id", "Income"))

        val exportPath = System.getProperty("user.dir") + "/income.csv"
        println("Exporting results to path: " + exportPath)
        Utils.exportToCSV(salesWithTotalPriceWithHeaders, exportPath)
    }

    private def getSalesWithTotalPrice(sales    : Sales, catalog: Catalog): List[List[String]] = {
        val result = sales.items
                          .map(sale => (sale.storeId.value, catalog.toyPrice(sale.toyId).value * sale.quantity))

        result.groupBy(_._1)
              .view.mapValues(x => x.map(_._2).sum)
              .toList
              .map(item => List(item._1.toString, item._2.toString))

        //TODO: implementar order by STORE ID
    }
}
