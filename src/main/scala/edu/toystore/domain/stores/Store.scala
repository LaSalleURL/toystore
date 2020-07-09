package edu.toystore.domain.stores


object Store {
    def apply(id: String, name: String, address: String): Store = new Store(
        StoreId(id.toInt),
        name,
        address
    )
}


case class Store(id: StoreId, name: String, address: String)
