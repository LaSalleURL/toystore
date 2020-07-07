package edu.toystore.domain.sales

import edu.toystore.domain.catalog.ToyId
import edu.toystore.domain.stores.StoreId


object Sale {
    def apply(storeId: String, toyId: String, quantity: String): Sale = Sale(
        StoreId(storeId.toInt),
        ToyId(toyId.toInt),
        quantity.toInt
    )
}


final case class Sale(storeId: StoreId, toyId: ToyId, quantity: Int)
