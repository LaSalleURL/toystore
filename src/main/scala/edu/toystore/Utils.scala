package edu.toystore

import java.io.{File, PrintWriter}

object Utils {

    /*
            No has abstraído con polimorfismo paramétrico.
            Por ello, estás haciendo una pasada entera extra por todas las listas para mapearlas a su case class.
            Esto es muy ineficiente, sobre todo para csv muy grandes como el de sales.
            El contrato de esta funcion bien abstraída, debería ser algo así como:
    */

//    def readFromCSV[A](fileName: String, delimiter: String, rows: List[A], parseRow: Array[String] => A): List[List[String]] = ???

    def readFromCSV(fileName: String, delimiter: String): List[List[String]] = {
        val bufferedSource = io.Source.fromResource(fileName)

        val mappedRows = bufferedSource
            .getLines
            .drop(1)
            .map(line => {
                line.split(delimiter)
                    .map(_.trim)
                    .toList

            }).toList

        bufferedSource.close
        mappedRows
    }

    def exportToCSV(list: List[List[String]], path: String, delimiter: String = ";"): Unit = {
        val writer = new PrintWriter(new File(path))

        list.foreach(item => {
            writer.write(item.mkString(s"$delimiter") + "\n")
        })

        writer.close()
    }

}
