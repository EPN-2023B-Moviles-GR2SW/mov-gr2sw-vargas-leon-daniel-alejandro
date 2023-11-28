import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    println("Hello World!")


    //variable Inmutable, no se reasigna "="
    val inmutable = "Daniel";
    //Mutables
    var mutable: String = "Alejandro";
    mutable = "Daniel";


    //val>var
    //Duck Typing
    var ejemploVariable = "Daniel Vagas"
    val ejemploEdades: Int = 12
    ejemploVariable.trim()


    //Variables Primitivas
    val nombre: String = "Daniel"
    val sueldo: Double = 0.0
    val estadoCivil: Char = 'S'
    val  mayorEdad: Boolean = true
    //Clases Java
    val fechaNacimiento: Date = Date()

    //Switch
    val estadoCivilWhen = "C"
    when(estadoCivilWhen){
        ("C")->{
            println("Casado")
        }
        "S"->{
            println("Soltero")
        }
        else->{
            println("no sabemos")
        }
    }

    //Condicional
    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo = if (esSoltero) "Si" else "No"

    fun imprimirNombre(nombre:String): Unit{
        println("Nombre : ${nombre}")
    }

    //Uso de funciones
    println(calcularSueldo(10.00)) //Solo parametro requerido
    calcularSueldo(10.00, 15.00, 20.00) // Todos los paramtros
    //Paraametros nombrados
    calcularSueldo(10.00, bonoEspecial = 20.00)
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00)

    //Uso de la clase
    val sumaUno = Suma(1,1)
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)
    val sumaCuatro = Suma(null, null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    //Arreglos
    //Estatico
    val arregloestatico: Array<Int> = arrayOf<Int>(1,2,3,4,5,6,7,8,9,10)
    //Dinamico
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)

    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)



    //For each ->unit
    val respuestaForEach: Unit = arregloDinamico
        .forEach {valorActual:Int ->
            println("valor actual: ${valorActual}")

        }
    //it
    arregloDinamico.forEach{ println("Valor actual: ${it}")}
    arregloestatico
        .forEachIndexed { indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }

    // Val
    val respuestaMap: List<Double> = arregloDinamico
        .map { valorAcrual:Int ->
            return@map valorAcrual.toDouble() + 100.00
        }
    println(respuestaMap)
    val respueestaMapDos = arregloDinamico.map { it +15 }


    //Flecha
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val mayoresACinco: Boolean = valorActual > 5
            return@filter mayoresACinco
        }
    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }


    //Or And
    //OR ->ANY algunos cumplen?
    //AND -> All todos cumplen?

    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual: Int ->
            return@any (valorActual >5)
        }
    print(respuestaAny)

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual >5)
        }
    print(respuestaAll)


    //reduce

    val respuestaReduce: Int = arregloDinamico
        .reduce{
            acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual)
        }
    println(respuestaReduce)


}
fun calcularSueldo(
    sueldo: Double,
    tasa: Double = 12.00,
    bonoEspecial: Double? = null
): Double{
    if(bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) + bonoEspecial
    }
}

//Clases

abstract class NumerosJava{
    protected val numeroUno: Int
    private val  numeroDos: Int
    constructor(
        uno: Int,
        dos: Int
    ){
        this.numeroUno = uno
        this.numeroDos = dos
        println("inicializados")
    }
}

//clase kotlin
abstract class Numeros( // Constructor PRIMARIO
    // Ejemplo:
    // uno: Int, (Parametro (sin modificador de acceso))
    // private var uno: Int, // Propiedad Publica Clase numeros.uno
    // var uno: Int, // Propiedad de la clase (por defecto es PUBLIC)
    // public var uno: Int,
    protected val numeroUno: Int, // Propiedad de la clase protected numeros.numeroUno
    protected val numeroDos: Int, // Propiedad de la clase protected numeros.numeroDos
){
    // var cedula: string = "" (public es por defecto)
    // private valorCalculado: Int = 0 (private)
    init { // bloque constructor primario
        this.numeroUno; this.numeroDos; // this es opcional
        numeroUno; numeroDos; // sin el "this", es lo mismo
        println("Inicializando")
    }
}

class Suma( // Constructor Primario Suma
    unoParametro: Int, // Parametro
    dosParametro: Int, // Parametro
): Numeros(unoParametro, dosParametro){ // Extendiendo y mandando los parametros (super)

    init{ // Bloque codigo constructor primario
        this.numeroUno
        this.numeroDos
    }

    constructor( // Segundo constructor
        uno: Int?, // Parametros
        dos: Int // Parametros
    ):this (
        if(uno == null) 0 else uno,
        dos
    )
    constructor( // Tercer constructor
        uno: Int, // Parametros
        dos: Int? // Parametros
    ):this(
        uno,
        if(dos == null) 0 else dos,
    )

    constructor(//  cuarto constructor
        uno: Int?,
        dos: Int?
    ) : this(  // llamada constructor primario
        if (uno == null) 0 else uno,
        if (dos == null) 0 else uno
    )

    public fun sumar(): Int {
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }
    // Atributos y Metodos "Compartidos"
    companion object {
        val pi = 3.14
        fun elevarAlCuadrado(num: Int): Int {
            return num * num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma:Int){
            historialSumas.add(valorNuevaSuma)
        }
    }
}



// void -> Unit
fun imprimirNombre(nombre: String): Unit{
    // "Nombre : " + nombre
    println("Nombre : ${nombre}") // template strings
}



