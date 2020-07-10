package edu.toystore.domain.store_sales

trait StoreSalesRepository {

    def saveAll(storeBilling: List[StoreSales]): Unit

}
