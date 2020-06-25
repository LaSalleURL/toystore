package edu.toystore.domain.stock

import edu.toystore.domain.catalog.ToyId
import edu.toystore.domain.stores.StoreId

final case class Stock(toyId: ToyId, storeId: StoreId, initialStock: Int, inventory: Int)

