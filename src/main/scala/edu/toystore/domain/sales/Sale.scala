package edu.toystore.domain.sales

import edu.toystore.domain.catalog.ToyId
import edu.toystore.domain.stores.StoreId

final case class Sale(storeId: StoreId, toyId: ToyId, quantity: Int)
