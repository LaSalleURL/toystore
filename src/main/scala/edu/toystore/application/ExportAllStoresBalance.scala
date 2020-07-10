package edu.toystore.application

import edu.toystore.domain.imbalance.{Imbalance, ImbalanceRepository}
import edu.toystore.domain.sales.SaleRepository
import edu.toystore.domain.stock.StockRepository

class ExportAllStoresBalance(stockRepository: StockRepository,
    saleRepository                          : SaleRepository,
    imbalanceRepository                     : ImbalanceRepository) {

    def execute(): Unit = {
        val stockList = stockRepository.all()
        val sales     = saleRepository.all()

        val imbalanceList = stockList
            .map(stock => {
                val filteredItems = sales.items.filter(sale => sale.storeId == stock.storeId &&
                                                               sale.toyId == stock.toyId)
                val totalQuantity = filteredItems.map(item => item.quantity).sum
                val imbalance     = stock.inventory - totalQuantity

                Imbalance(stock.storeId, stock.toyId, imbalance)
            })
            .filter(imbalance => imbalance.imbalance != 0)
            .sortBy(imbalance => (imbalance.storeId.value, imbalance.toyId.value))

        imbalanceRepository.saveAll(imbalanceList)
    }

}
