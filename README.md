# Tienda de Juguetes

Nos han encargado realizar una aplicación para la gestión de un conjunto de tiendas de juguetes. En esta primera versión nos han propuesto 3 requisitos concretos.

1. Calcular la facturación diaria de dichas tiendas (total facturado por tienda).
2. Calcular la cantidad de stock a reponer en cada tienda al final de cada dia para que otro sistema pueda automatizar el reparto de juguetes a reponer cada mañana.
3. Realizar un sencillo balance de inventario para detectar posibles descuadres.

Para ello, cada día nos proporcionarán la información actualizada en forma de ficheros de formato CSV, y con ellos, nuestra aplicación debería ser capaz de realizar los cálculos mencionados anteriormente y exportar los resultados también a ficheros CSV.

A continuación se expone el detalle del formato de cada uno de esos ficheros.

### Inputs

Los ficheros de input son aquellos que nos proporcionarán diariamente, y con ellos, nuestra aplicación podrá hacer la carga de datos necesarios para hacer los cálculos.

#### stores.csv

|storeId|storeName|storeAddress|
|:-------|:---------|:------------|
|1|Dreams land|Constable Trees 47|
|2|Eurekids|Woodcote Gait 12| 
|3|Player one|Perry Oak 1|
|4|Fair play|Sunny Side 402|
|5|Play path|Fosters Road 134|

Este es un **EJEMPLO** del fichero que contiene el conjunto de tiendas gestionadas por nuestro cliente. 

El formato del campo identificador `storeId`será del tipo entero estrictamente positivo, mientras que los otros dos de tipo texto.

Los identificadores no tienen porque ser consecutivos ni venir ordenados.

#### catalog.csv

|toyId|toyName|toyPrice|
|:----|:------|:-------|
|1|Nintendo Switch|329.99|
|2|Baby yoda funko pop|30.49|
|3|Lego Harry Potter|70|
|4|Teddy Minion|25.5|
|5|Monopoly Fortnite|19.88|
|6|Polly Pocket Farm|53.21|
|7|Peppa pig chair|44.99|
|8|Razor power core|117.11|
|9|Slime factory|34.89|

Este es un **EJEMPLO** del fichero que contiene el catálogo de juguetes que cualquiera de las tiendas podría tener disponible. 
Eso significa que cualquiera de las tiendas podría tener en su stock todos estos productos, o solo un subconjunto de ellos, pero jamás un producto que no esté en este fichero. 

El formato del campo identificador `toyId` será del tipo entero estrictamente positivo, `toyPrice` tendrá una precisión máxima de 2 decimales y `toyName` será del tipo texto. 
Lo que sí que hemos detectado, es que a veces el campo toyPrice podría venir notificado con un 0 o un número negativo. 
En ese caso se consideraría un error y nuestro programa tendría que notificar que no puede realizar la carga de datos o realizar las acciones involucradas con el precio de un producto.

Los identificadores no tienen porque ser consecutivos ni venir ordenados.

#### stock.csv

|toyId|storeId|initialStock|inventory|
|:----|:------|:-------|:------------|
|1|1|10|8|
|1|2|12|12|
|1|3|15|0|
|1|4|10|3|
|1|5|15|10|
|2|2|35|30|
|2|3|30|19|
|2|4|25|12|
|3|1|15|10|
|3|2|20|15|
|...|...|...|...|


Este es un **EJEMPLO** del fichero que contiene una estimación del stock inicial de cada juguete en cada tienda (`initialStock`), así como la cantidad real de stock restante en las tiendas que los empleados de las jugueterías notifican al finalizar el día (`inventory`). 
Los 2 son del tipo entero postivo, aunqué en este caso, pueden ser 0. 

Como podéis deducir, `storeId` y `toyId` hacen referencia a los identificadores de `store.csv` y `catalog.csv`, por lo tanto tendrán el mismo tipo.
Asimismo, se acepta la suposición de que no podrán aparecer referencias inexistentes. 

Como también es previsible, el identificador de cada fila será la combinación de `toyId` y `storeId`, y se puede presuponer que no habrá filas duplicadas.

Lo que **NO** podemos presuponer es que todas las combinaciones de tienda/producto vengan específicadas en este fichero.
Que una combinación concreta no aparezca indica que ese juguete no se vende en esa tienda, pero también pueden aparecer con valores `0`.

Por último, tenemos que tener en cuenta que no siempre el campo `initialStock` tiene que ser más grande o igual que el campo `inventory`.
Existen diversos motivos por los cuales esto puede suceder (devoluciones, fallos en los recuentos de inventario, mermas, etc...).

Tampoco podemos asegurar ningún orden.

#### sales.csv

|storeId|toyId|quantity|
|:----|:------|:-------|
|1|2|2|
|1|3|1|
|1|2|3|
|2|4|4|
|3|2|1|
|3|9|1|
|1|2|2|
|5|3|3|
|1|6|2|
|4|8|1|
|4|1|1|
|3|4|2|
|3|6|3|
|3|9|4|
|...|...|...|

Este es un **EJEMPLO** del fichero que contiene las líneas de venta registradas por los TVP de las jugueterías.

No es dificil deducir, que este será el fichero más pesado.

Cada una de las filas es una línea de venta de un ticket y el campo `quantity` especifica la cantidad. 
Podemos presuponer que dicha cantidad no va a ser `0` pero no tiene porque ser positiva (por ejemplo en las devoluciones).

Como podéis deducir, `storeId` y `toyId` hacen referencia a los identificadores de `store.csv` y `catalog.csv`, por lo tanto tendrán el mismo tipo.
Asimismo, se acepta la suposición de que no podrán aparecer referencias inexistentes. No obstante, en este caso las filas duplicadas son de esperar.

Al igual que con el fichero de stocks, que no aparezca una combinación concreta de tienda/juguete es algo normal.

Tampoco podemos asegurar ningún orden.

### Menú

Para poder ejecutar las diferentes acciones, la aplicación mostrará un menú accionable con las diferentes acciones disponbles. Dicho menú debería tener más o menos este aspecto (pudiendo añadir, reordenar o renombrar un punto si se considera necesario):

```bash
$> sbt run
Select your choice:
    1. Stores income
    2. Stores stock
    3. Stores balance
    4. Exit
```

Teniendo en cuenta este ejemplo, los puntos `1`,`2` y `3` deberían generar ciertos ficheros de output en el directorio `target` del proyecto.

La aplicación no debería salir nunca de forma brusca, siempre y cuando los ficheros de input cumplan las presuposiciones especificadas anteriormente y solamente debería salir al marcar la acción `4`.
Por lo tanto, después de la ejecución de cualquiera de los puntos, debería volver a mostrar el menú, pudiendo seleccionar otra vez cualquiera de las acciones disponibles.

Si alguna cualquiera de estas acciones falla debido a un error esperado, un simple mensaje que explique el motivo del error antes de la siguiente aparición del menú es suficiente.

Los errores en el input de selección son frecuentes y por lo tanto se tienen que tratar.


### Outputs

A continuación se detallan los ficheros output esperados que va a procesar nuestra aplicación en cada una de sus acciones.

#### income.csv (Stores income)

Como resultado se espera un listado del total de facturación bruta por tienda. A modo de ejemplo, teniendo en cuenta los inputs de `catalog.csv` y `sales.csv`:

|toyId|toyName|toyPrice|            
|:----|:------|:-------|           
|1|Nintendo Switch|329.99|
|2|Baby yoda funko pop|30.49|
|...|...|...|

|storeId|toyId|quantity|
|:----|:------|:-------|
|1|2|2|
|1|2|1|
|2|1|1|
|2|2|2|

Se espera como resultado:

|storeId|income|
|:----|:------|
|1|91.47|
|2|390.97|

- El resultado estará ordenado ascendentemente por `storeId`.
- Nótese que no todas las tiendas tienen porque que aparecer en este listado si por cualquier motivo no tienen ventas.
- El resultado tendrá una precisión máxima de 2 decimales.

#### refill.csv (Stores stock)

Como resultado se espera un listado de la cantidad de juguetes que se deben reponer en cada tienda antes de su apertura en función
de la previsión de stock y el recuento de inventario manual realizado al final del día. A modo de ejemplo, teniendo en cuenta los inputs de `stock.csv` y `stores.csv`:

|toyId|storeId|initialStock|inventory|
|:-------|:---------|:------------|:-----|
|1|1|10|12|
|1|2|12|3|
|2|1|15|6|
|2|2|10|2|

|storeId|storeName|storeAddress|
|:-------|:---------|:------------|
|1|Dreams land|Constable Trees 47|
|2|Eurekids|Woodcote Gait 12| 
|...|...|...|

Se espera como resultado:

|storeId|storeName|storeAddress|toyId|quantity|
|:-------|:---------|:------------|:--------|:----------|
|1|Dreams land|Constable Trees 47|2|9|
|2|Eurekids|Woodcote Gait 12|1|9|
|2|Eurekids|Woodcote Gait 12|2|8|

- Las filas con el resultado de `quantity <= 0` son irrelevantes y **NO** deben salir.
- Nótese que por algún motivo la juguetería `Dreams land` ha notificado que al final del día dispone de más juguetes con Id `1` de los que teóricamente podía tener inicialmente en stock. 
Esto es normal y algunos de los motivos se han explicado previamente en la explicación del fichero `stock.csv`. 
Para esta versión, estas inconsistencias se subsanan manualmente y por lo tanto están fuera del alcance del sistema.
- El resultado estará ordenado ascendentemente por `storeId`, `toyId`.
- En este punto todavía no se hace ninguna comprovación con las ventas. 
La información notificada por las tiendas es suficiente para gestionar la logística de reparto y es suficiente para una primera versión.

#### imbalance.csv (Stores balance)

Como resultado se espera un listado que indique los posibles descuadres de stock como primer paso para detectar los posibles errores de los anteriores outputs y poder tomar medidas correctivas (que en esta primera versión serán manuales).
A modo de ejemplo, teniendo en cuenta los inputs de `stock.csv` y `sales.csv`:


|toyId|storeId|initialStock|inventory|
|:-------|:---------|:------------|:-----|
|1|1|10|10|
|1|2|12|1|
|2|1|15|6|
|2|2|10|2|

|storeId|toyId|quantity|
|:----|:------|:-------|
|1|1|1|
|1|1|1|
|1|2|3|
|1|2|6|
|2|1|4|
|2|1|6|
|2|2|8|

Se espera como resultado:

|storeId|toyId|imbalance|
|:----|:------|:-------|
|1|1|2|
|2|1|-1|

- Las filas con el resultado `imbalance = 0` no son descuadres, por lo tanto son irrelevantes y **NO** deben salir.
- El resultado estará ordenado ascendentemente por `storeId`, `toyId`.

# Consideraciones técnicas

- La práctica se realizará con el lenguaje de programación Scala intentando utilizar al máximo su paradigma funcional.
- La entrega consistirá en el código fuente y **opcionalmente** un documento donde, si se cree conveniente, se explicarán las decisiones de diseño y las asunciones/consideraciones que se consideren importantes para que el corrector pueda tenerlas en cuenta al valorar la solución.
- Se puede hacer uso de librerías de terceros siempre y cuando estas sean previamente acordadas con el docente de la asignatura.
- Es una aplicación stateless. Por lo tanto, el resultado de los outputs únicamente dependerá de los ficheros de input y será determinístico. 
Durante la ejecución, la aplicación puede guardar ciertos estados si por requisitos del diseño se cree conveniente, pero estos estados no prevalecerán entre ejecuciones.
- La ejecución del programa asumirá que los ficheros de input se encuentran en la ubicación relativa al proyecto `src/main/resources/input`.
- Los ficheros de output se depositarán en la ubicación relativa al proyecto `target`.
- Se penalizará especialmente el uso de mutabilidad, nulls, estructuras de control tipo `while`, el uso de reflexión y el lanzamiento de excepciones.
- Se valorará positivamente la reutilización de código mediante el uso de abstracciones adecuadas.
- Se valorará positivamente el aislamiento de side effects. Manteniendo “puras” el máximo número de zonas posibles.
- Se valorará, aunque en menos grado, la eficiencia (uso de memoria y/o CPU) pero siempre poniendo en primer lugar el uso de código funcional.

# Usage

- Instalar [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- Instalar [sbt](https://www.scala-sbt.org/1.x/docs/Setup.html)
- Clonar este repositorio
- Para ejecutar la aplicación, dentro de la carpeta raíz del proyecto ejecutar: 

````bash
$> sbt run
```` 


