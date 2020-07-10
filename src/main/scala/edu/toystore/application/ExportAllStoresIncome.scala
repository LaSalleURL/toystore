package edu.toystore.application

import edu.toystore.domain.catalog.{Catalog, ToyRepository}
import edu.toystore.domain.sales.{SaleRepository, Sales}
import edu.toystore.domain.store_sales.{StoreSales, StoreSalesRepository}

final class ExportAllStoresIncome(salesRepository: SaleRepository, toyRepository: ToyRepository, storeBillingRepository: StoreSalesRepository) {


    def execute(): Unit = {
        val sales  : Sales   = salesRepository.all()
        val catalog: Catalog = toyRepository.all()

        val storeSales = sales.items
             .map(sale => {
                 val totalPrice = catalog.toyPrice(sale.toyId).value * sale.quantity
                 (sale.storeId, totalPrice)
             })
            .groupBy(_._1)
            .view.mapValues(v => v.map(_._2).sum) //sum all prices
            .toList
            .map(item => StoreSales(item._1.value.toString, item._2.toString))

        storeBillingRepository.saveAll(storeSales)
    }

}
