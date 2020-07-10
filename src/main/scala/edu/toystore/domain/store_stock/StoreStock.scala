package edu.toystore.domain.store_stock

import edu.toystore.domain.catalog.ToyId
import edu.toystore.domain.stores.StoreId

object StoreStock {
    def apply(
        storeId         : String,
        storeName       : String,
        storeAddess     : String,
        toyId           : String,
        quantity        : String
    ): StoreStock = new StoreStock(
        StoreId(storeId.toInt),
        storeName,
        storeAddess,
        ToyId(toyId.toInt),
        quantity.toInt
    )
}

case class StoreStock(storeId: StoreId, storeName: String, storeAddess: String, toyId: ToyId, quantity: Int)
