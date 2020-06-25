package edu.toystore.domain.catalog


object Toy {
    def apply(id: Int, name: String, price: Float): Toy = Toy(
        ToyId(id),
        ToyName(name),
        ToyPrice(price)
    )
}

final case class Toy(id: ToyId, name: ToyName, price: ToyPrice)

//Calcular la facturación diaria de dichas tiendas (total facturado por tienda).
//Calcular la cantidad de stock a reponer en cada tienda al final de cada dia para que otro sistema pueda automatizar el reparto de juguetes a reponer cada mañana.
//Realizar un sencillo balance de inventario para detectar posibles descuadres.
