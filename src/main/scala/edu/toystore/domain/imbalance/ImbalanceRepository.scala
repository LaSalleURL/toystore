package edu.toystore.domain.imbalance

trait ImbalanceRepository {

    def saveAll(imbalanceList: List[Imbalance]): Unit

}
