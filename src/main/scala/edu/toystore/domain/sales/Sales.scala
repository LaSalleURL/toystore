package edu.toystore.domain.sales


/*
  NO!. Data es solo eso, data. Estás creando un wrapper para generar un tipo innecesario,
  escondiendo algo muy valioso: Esto es una estructura de datos tipo lista.
  Además, ni siguiera le añades comportamiento. Lo único que te va a aportar esto, es un nivel de complejidad extra y dificultad para componer cada vez que quieras sacar la lista del wrapper.
*/
case class Sales(items: List[Sale])


