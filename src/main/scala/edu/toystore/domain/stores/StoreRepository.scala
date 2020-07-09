package edu.toystore.domain.stores

trait StoreRepository {

    def all(): List[Store]
}
