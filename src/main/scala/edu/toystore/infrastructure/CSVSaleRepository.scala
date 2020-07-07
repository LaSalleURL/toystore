package edu.toystore.infrastructure

import edu.toystore.Utils
import edu.toystore.domain.sales.{Sale, SaleRepository, Sales}

class CSVSaleRepository extends SaleRepository {



    override def all(): Sales = {
        val path      = "/Users/dzavaleta/Documents/MDAS/Paradigmas/toystore/src/main/resources/input/sales.csv"
        val delimiter = ";"
        Sales(Utils.readFromCSV(path, delimiter).map(row => Sale(row.head, row(1), row(2))))
    }

}
