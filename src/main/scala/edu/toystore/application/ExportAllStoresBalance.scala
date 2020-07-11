package edu.toystore.application

import edu.toystore.domain.imbalance.{Imbalance, ImbalanceRepository}
import edu.toystore.domain.sales.SaleRepository
import edu.toystore.domain.stock.StockRepository

class ExportAllStoresBalance(stockRepository: StockRepository,
    saleRepository                          : SaleRepository,
    imbalanceRepository                     : ImbalanceRepository) {

    /*
      Ninguna de tus funciones de tus casos de uso son puras. En todas estás haciendo side-effects para leer y escribir en los csv.
      Por lo tanto, prácticamente toda tu lógica de negocio está mezclada con side-effects. No es muy grave, ya que tu diseño te permite apartarlos con relativa facilidad
      en otras funciones pero no cumple el propósito de la práctica.
      Este caso de uso no lo has entendido o no está acabado y directamente no hace lo que pide el enunciado. Seguramente ya lo sabrás si has comprobado el output.
    */
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
