package edu.toystore.application

import edu.toystore.domain.stores.StoreId
import edu.toystore.domain.stores_income.{Income, StoreIncome, StoresIncome}

final class ExportAllStoresIncome() {
    def execute(): Unit = {
        //obtener los datos desde el csv
        //transformar los datos
        //exportar en csv
        // Calcular la facturación diaria de dichas tiendas (total facturado por tienda).
//        val allSales = saleRepository.all()
//        allSales.


        val storesIncome = StoresIncome(
            Seq(
                StoreIncome(StoreId(1), Income(234.23)),
                StoreIncome(StoreId(2), Income(654.99)),
                StoreIncome(StoreId(3), Income(103.45))
            )
        )

        println(storesIncome)
//        sales
//        storeId	toyId	quantity


//        result:
//        storeId	income
//        1	        91.47
//        2	        390.97


    }
}
