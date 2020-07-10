package edu.toystore.infrastructure

import edu.toystore.Utils
import edu.toystore.domain.imbalance.{Imbalance, ImbalanceRepository}

class CSVImbalanceRepository extends ImbalanceRepository {

    override def saveAll(imbalanceList: List[Imbalance]): Unit = {
        val imbalanceListWithHeaders = imbalanceList
            .map(
                item => List(
                    item.storeId.value.toString,
                    item.toyId.value.toString,
                    item.imbalance.toString
                )
            )
            .prepended(List("Store Id", "Toy Id", "Imbalance"))

        val exportPath = System.getProperty("user.dir") + "/target/imbalance.csv"
        Utils.exportToCSV(imbalanceListWithHeaders, exportPath)
    }
}
