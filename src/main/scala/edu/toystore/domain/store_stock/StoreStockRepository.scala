package edu.toystore.domain.store_stock

trait StoreStockRepository {

    def saveAll(storeStockList: List[StoreStock]): Unit

}
