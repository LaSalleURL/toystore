package edu.toystore.domain.store_sales

import edu.toystore.domain.stores.StoreId
import edu.toystore.domain.stores_income.Income


object StoreSales {
    def apply(storeId: String, income: String): StoreSales = new StoreSales(
        StoreId(storeId.toInt), Income(income.toDouble)
    )
}

case class StoreSales(storeId  : StoreId, income: Income)
