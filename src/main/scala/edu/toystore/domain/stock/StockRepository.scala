package edu.toystore.domain.stock

trait StockRepository {

    def all(): List[Stock]

}
