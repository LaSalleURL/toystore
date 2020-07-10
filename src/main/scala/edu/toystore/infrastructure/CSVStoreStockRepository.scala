package edu.toystore.infrastructure

import edu.toystore.Utils
import edu.toystore.domain.store_stock.{StoreStock, StoreStockRepository}

class CSVStoreStockRepository extends StoreStockRepository {

    override def saveAll(storeStockList: List[StoreStock]): Unit = {

        val storeStockListWithHeaders = storeStockList
            .map(
                item => List(
                    item.storeId.value.toString,
                    item.storeName,
                    item.storeAddess,
                    item.toyId.value.toString,
                    item.quantity.toString
                )
            )
            .prepended(List("Store Id", "Store Name", "Store Address", "Toy Id", "Quantity"))

        val exportPath = System.getProperty("user.dir") + "/refill.csv"
        Utils.exportToCSV(storeStockListWithHeaders, exportPath)
    }
}
