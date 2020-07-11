package edu.toystore.infrastructure

import edu.toystore.Utils
import edu.toystore.domain.stock.{Stock, StockRepository}

class CSVStockRepository extends StockRepository {

    override def all(): List[Stock] = {
        /* Esto solo funciona en tu máquina */
        val path      = "/Users/dzavaleta/Documents/MDAS/Paradigmas/toystore/src/main/resources/input/stock.csv"
        val delimiter = ";"
        Utils.readFromCSV("input/stock.csv", delimiter)
             .map(row => Stock(row.head, row(1), row(2), row(3)))
    }
}
