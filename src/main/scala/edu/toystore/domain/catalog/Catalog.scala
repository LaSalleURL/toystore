package edu.toystore.domain.catalog

 case class Catalog(toys: List[Toy]) {


    def toyPrice(toyId: ToyId): ToyPrice = {
        toys.find(toy => toy.id.equals(toyId))
            .get
            .price
    }

}
