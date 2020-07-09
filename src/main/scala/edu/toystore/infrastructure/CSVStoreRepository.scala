package edu.toystore.infrastructure

import edu.toystore.Utils
import edu.toystore.domain.stores.{Store, StoreRepository}

class CSVStoreRepository extends StoreRepository {

    override def all(): List[Store] = {
        val path      = "/Users/dzavaleta/Documents/MDAS/Paradigmas/toystore/src/main/resources/input/stores.csv"
        val delimiter = ";"
        Utils.readFromCSV(path, delimiter)
             .map(row => Store(row.head, row(1), row(2)))
    }

}
