package edu.toystore.infrastructure

import edu.toystore.Utils
import edu.toystore.domain.catalog.{Catalog, Toy, ToyRepository}

class CSVToyRepository extends ToyRepository {

    override def all(): Catalog = {
        /* Esto solo funciona en tu máquina */
        val path       = "/Users/dzavaleta/Documents/MDAS/Paradigmas/toystore/src/main/resources/input/catalog.csv"
        val delimiter  = ";"
        val listValues = Utils.readFromCSV("input/catalog.csv", delimiter).map(row => Toy(row.head, row(1), row(2)))
        Catalog(listValues)
    }

}
