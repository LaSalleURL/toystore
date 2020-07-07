
import scala.concurrent.{ExecutionContext, Future}


//Opcion verbosa
final class SandwichMaker(private val fridge: Fridge, private val fryer: Fryer)(implicit ec: ExecutionContext) {
    def make(): Future[Sandwich] = {
        val breadOptionFuture = fridge.takeBread()
        val cheeseOptionFuture = fridge.takeCheese()
        val hamOptionFuture = fridge.takeHam()
        val eggOptionFuture = fridge.takeEgg()
        val baconOptionFuture = fridge.takeBacon()

        breadOptionFuture.flatMap { breadOption =>
            cheeseOptionFuture.flatMap { cheeseOption =>
                hamOptionFuture.flatMap { hamOption =>
                    eggOptionFuture.flatMap { eggOption =>
                        baconOptionFuture.map { baconOption =>
                            Sandwich(Seq(breadOption, cheeseOption, hamOption, eggOption, baconOption))
                        }
                    }
                }
            }
        }
    }
}

//Opcion mejorada
final class SandwichMakerWithFor(private val fridge: Fridge, private val fryer: Fryer)(implicit
 ec: ExecutionContext) {

    def make(): Future[Sandwich] =
        for {
            breadOption  <- fridge.takeBread()
            cheeseOption <- fridge.takeCheese()
            hamOption    <- fridge.takeHam()
            eggOption    <- fridge.takeEgg()
            baconOption  <- fridge.takeBacon()
        } yield Sandwich(Seq(breadOption, cheeseOption, hamOption, eggOption, baconOption))


}


case class Fridge() {
    def takeBacon(): Future[Option[Ingredient]] = ???

    def takeEgg(): Future[Option[Ingredient]]  = ???

    def takeHam(): Future[Option[Ingredient]]  = ???

    def takeBread(): Future[Option[Ingredient]]  = ???

    def takeCheese(): Future[Option[Ingredient]]  = ???

}

case class Fryer()

case class Sandwich(value: Seq[Option[Ingredient]])

case class Ingredient()


import java.text.SimpleDateFormat
import java.util.Calendar

def log(prefix: String)(message: String): Unit = println(prefix + ": " + message)

def logWithDatetime(message: String): Unit =
    log("[DEBUG] " + new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(Calendar.getInstance.getTime))(message)


//println("hola")
//println()


logWithDatetime("en método auxiliar")
logWithDatetime("entro en condicion")
logWithDatetime("construct class")
logWithDatetime("en método auxiliar")







