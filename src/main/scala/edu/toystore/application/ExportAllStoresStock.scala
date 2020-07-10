package edu.toystore.application

import edu.toystore.domain.stock.{Stock, StockRepository}
import edu.toystore.domain.store_stock.{StoreStock, StoreStockRepository}
import edu.toystore.domain.stores.{Store, StoreRepository}

class ExportAllStoresStock(stockRepository: StockRepository,
    storeRepository: StoreRepository,
    storeStockRepository: StoreStockRepository) {


    def execute(): Unit = {
        val stockList: List[Stock] = stockRepository.all()
        val stores   : List[Store] = storeRepository.all()

        val storeStockList = stockList
            .map(stock => {
                val store = stores.find(_store => _store.id == stock.storeId)

                StoreStock(
                    stock.storeId.value.toString,
                    store.get.name,
                    store.get.address,
                    stock.toyId.value.toString,
                    stock.getRefillQuantity.toString
                )
            })
            .filter(storeStock => storeStock.quantity > 0)

        storeStockRepository.saveAll(storeStockList)
    }

}
