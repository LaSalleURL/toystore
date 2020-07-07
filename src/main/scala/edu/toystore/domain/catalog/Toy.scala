package edu.toystore.domain.catalog


object Toy {
    def apply(id: String, name: String, price: String): Toy = Toy(
        ToyId(id.toInt),
        ToyName(name),
        ToyPrice(price.toFloat)
    )
}

final case class Toy(id: ToyId, name: ToyName, price: ToyPrice)

//Calcular la facturación diaria de dichas tiendas (total facturado por tienda).
//Calcular la cantidad de stock a reponer en cada tienda al final de cada dia para que otro sistema pueda automatizar el reparto de juguetes a reponer cada mañana.
//Realizar un sencillo balance de inventario para detectar posibles descuadres.
