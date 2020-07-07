package edu.toystore

object PlayGround extends App {

    final case class ::[A](head: () => A, tail: () => MyStream[A]) extends MyStream[A]

    sealed trait MyStream[+A] {
        def map[B](f: A => B): MyStream[B] = this match {
            case Empty  => Empty
            case h :: t => ::(() => f(h()), () => t().map(f))
        }

        def take(num: Int): MyStream[A] = this match {
            case Empty         => Empty
            case _ if num == 0 => Empty
            case h :: t        => ::(() => h(), () => t().take(num - 1))
        }

        def toList: List[A] = this match {
            case Empty  => Nil
            case h :: t => h() :: t().toList
        }

        def foreach(f: A => Unit): Unit = this match {
            case Empty    => ()
            case ::(h, t) =>
                f(h())
                t().foreach(f)
        }
    }

    case object Empty extends MyStream[Nothing]


    object MyStream {
        def continually[A](expr: () => A): MyStream[A] = ::(expr, () => continually(expr))
    }


    MyStream.continually(() => scala.util.Random.nextInt())
            .map(_ + 1)
            .map("[[  " + _.toString + "  ]]")
            .foreach(println)
//                                 .take(10)
//                                 .toList


}
