package edu.toystore.domain.stores_income

import edu.toystore.domain.catalog.Catalog
import edu.toystore.domain.sales.Sales

object StoresIncome {

//    def fromSales(sales: Sales, catalog: Catalog): StoresIncome = {
//        sales.items.map(sale => StoreIncome(sale.storeId, sale.))
//
//        StoresIncome()
//    }

}

case class StoresIncome(items: Seq[StoreIncome])
