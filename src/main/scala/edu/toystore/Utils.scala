package edu.toystore

import java.io.{File, PrintWriter}

object Utils {


    def readFromCSV(path: String, delimiter: String): List[List[String]] = {
        val bufferedSource = io.Source.fromFile(path)

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
