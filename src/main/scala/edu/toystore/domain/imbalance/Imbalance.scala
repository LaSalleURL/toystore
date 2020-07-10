package edu.toystore.domain.imbalance

import edu.toystore.domain.catalog.ToyId
import edu.toystore.domain.stores.StoreId

object Imbalance {
    def apply(storeId: String, toyId: String, imbalance: String): Imbalance = new Imbalance(
        StoreId(storeId.toInt), ToyId(toyId.toInt), imbalance.toInt
    )
}

case class Imbalance(storeId: StoreId, toyId: ToyId, imbalance: Int)
