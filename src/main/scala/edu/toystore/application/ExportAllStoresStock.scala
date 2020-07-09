package edu.toystore.application

import edu.toystore.Utils
import edu.toystore.domain.stock.{Stock, StockRepository}
import edu.toystore.domain.stores.{Store, StoreRepository}

class ExportAllStoresStock(stockRepository: StockRepository, storeRepository: StoreRepository) {


    def execute(): Unit = {
        val stockList: List[Stock] = stockRepository.all()
        val stores   : List[Store] = storeRepository.all()

        val mappedStockWithHeaders = stockList
            .map(stock => {
                val store    = stores.find(_store => _store.id == stock.storeId)

                List(
                    stock.storeId.value.toString,
                    store.get.name,
                    store.get.address,
                    stock.toyId.value.toString,
                    stock.getRefillQuantity.toString
                )
            })
            .prepended(List("Store Id", "Store Name", "Store Address", "Toy Id", "Quantity"))

        val exportPath = System.getProperty("user.dir") + "/refill.csv"
        Utils.exportToCSV(mappedStockWithHeaders, exportPath)
    }

}
