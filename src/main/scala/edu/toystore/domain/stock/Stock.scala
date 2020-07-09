package edu.toystore.domain.stock

import edu.toystore.domain.catalog.ToyId
import edu.toystore.domain.stores.StoreId


object Stock {
    def apply(toyId: String, storeId: String, initialStock: String, inventory: String): Stock = Stock(
        ToyId(toyId.toInt),
        StoreId(storeId.toInt),
        initialStock.toInt,
        inventory.toInt
    )
}

final case class Stock(toyId: ToyId, storeId: StoreId, initialStock: Int, inventory: Int) {

    def getRefillQuantity: Int = initialStock - inventory

}

