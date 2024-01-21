
import model.Biblioteca
import model.CRUD.Manager
import model.Libro

fun main(args: Array<String>) {

    //Creación de varios objetos
    val libro: Libro = Libro("Oceano", "Enciclopedia", true, 2,23.50)
    val libro2: Libro = Libro("Miguel de cervantes", "Don Quijote", false, 1,0.0)
    val libro3: Libro = Libro("Esteban", "El martillo,", true, 1,10.0)
    val bibliotecaPrincipal = Biblioteca(ArrayList(), "Libritos el Master", "Zaruma", "Puruha", true)
    val bibliotecaSucursal = Biblioteca(ArrayList(), "Libritos el Capo", "Palama Real", "Cristobal de palacios", false)
    val bibliotecaCrud = Manager()
    //Guardado de Libros en las bibliotecas
    bibliotecaCrud.guardarLibro(bibliotecaPrincipal,libro)
    bibliotecaCrud.guardarLibro(bibliotecaPrincipal,libro2)
    bibliotecaCrud.guardarLibro(bibliotecaPrincipal,libro3)

    bibliotecaCrud.guardarLibro(bibliotecaSucursal,libro2)

    //Guardado de los objetos en un archivo

    bibliotecaCrud.guardarBiblioteca(bibliotecaPrincipal)
    bibliotecaCrud.guardarBiblioteca(bibliotecaSucursal)
    bibliotecaCrud.guardarBibliotecasEnArchivo() // Me permite guardar todas las bibliotecas definidas en un archivo
    //Obtencion de los datos guardados en un Json
    val bibliotecas = bibliotecaCrud.obtenerBibliotecasDesdeArchivo()
    println(" **Las bibliotecas obtenias del archivo son: ${bibliotecas}")
    //Actualizar una biblioteca
    println(" **La biblioteca antes de actualizar es: ${bibliotecaCrud.getBibliotecaByNombre(bibliotecaSucursal.nombre)}")
    val bibliotecaActual = bibliotecaSucursal
    bibliotecaCrud.actualizarLibros(bibliotecaSucursal,libro2, libro3) // Informacion actualizada
    bibliotecaCrud.actualizarBibliotecaByNombre(bibliotecaActual, bibliotecaSucursal)

    println(" **Las bibliotecas actualizada del archivo son: ${bibliotecaCrud.getBibliotecaByNombre(bibliotecaSucursal.nombre)}")
    // Eliminacion de una biblioteca
    println(" **Las bibliotecas antes de la eliminacion del archivo son: ${bibliotecaCrud.obtenerBibliotecasDesdeArchivo()}")
    bibliotecaCrud.eliminarBiblioteca(bibliotecaSucursal.nombre)
    println(" **Las bibliotecas despues de la eliminacion del archivo son: ${bibliotecaCrud.obtenerBibliotecasDesdeArchivo()}")


}