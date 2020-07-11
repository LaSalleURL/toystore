package edu.toystore.domain.catalog

 case class Catalog(toys: List[Toy]) {

    /*
      Ineficiente. En el caso peor, recorres cada vez la lista de juguetes para cada venta.
      Esta búsqueda se puede hacer en tiempo constante usando otra estructura tipo mapa.
    */
    def toyPrice(toyId: ToyId): ToyPrice = {
        toys.find(toy => toy.id.equals(toyId))
            .get
            .price
    }

}
