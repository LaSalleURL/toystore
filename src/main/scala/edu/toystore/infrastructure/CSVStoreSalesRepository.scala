package edu.toystore.infrastructure

import edu.toystore.Utils
import edu.toystore.domain.store_sales.{StoreSales, StoreSalesRepository}

class CSVStoreSalesRepository extends StoreSalesRepository {

    override def saveAll(storeSales: List[StoreSales]): Unit = {
        val exportPath = System.getProperty("user.dir") + "/target/income.csv"

        Utils.exportToCSV(
            storeSales
                .map(item => List(item.storeId.value.toString, item.income.value.toString))
                .prepended(List("Store Id", "Income")),
            exportPath
        )
    }
}
