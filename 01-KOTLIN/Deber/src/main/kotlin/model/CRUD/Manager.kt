package model.CRUD

import com.google.gson.Gson
import model.Biblioteca
import model.Libro
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException

class Manager {

    var bibliotecas: ArrayList<Biblioteca> = ArrayList()

    //Logica relacionada con Las Bibliotecas
    fun guardarBibliotecasEnArchivo() {
        try {
            // Convertir el objeto Biblioteca a JSON
            val gson = Gson()
            val json = gson.toJson(bibliotecas)

            // Guardar el JSON en un archivo dentro del directorio "files"
            val directorioPorDefecto = "files"
            val archivoPorDefecto = "data.json"

            val rutaCompleta = "$directorioPorDefecto/$archivoPorDefecto"

            // Crear el directorio si no existe
            File(directorioPorDefecto).mkdirs()

            FileWriter(rutaCompleta).use { writer ->
                writer.write(json)
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    fun obtenerBibliotecasDesdeArchivo(): List<Biblioteca>? {
        try {
            // ruta de archivos
            val directorioPorDefecto = "files"
            val archivoPorDefecto = "data.json"

            val rutaCompleta = "$directorioPorDefecto/$archivoPorDefecto"
            // Leer el contenido del archivo
            val contenidoArchivo = FileReader(rutaCompleta).use { it.readText() }

            // Convertir el JSON a una lista de objetos Biblioteca
            val gson = Gson()
            bibliotecas = ArrayList(gson.fromJson(contenidoArchivo, Array<Biblioteca>::class.java).toList())
            return bibliotecas

        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }

    fun guardarBiblioteca(biblioteca:Biblioteca){
        bibliotecas.add(biblioteca)
    }
    fun bibliotecaPorNombre(nombre: String): Biblioteca? {
        for (biblioteca in bibliotecas){
            if (biblioteca.nombre == nombre)
                return  biblioteca
        }
        return null
    }


    fun getBibliotecaByNombre(nombre: String?): Biblioteca? {
        var bibliotecaByNombre:Biblioteca? = null;
        for (biblioteca in bibliotecas){
            if (biblioteca.nombre.equals(nombre))
                bibliotecaByNombre = biblioteca
        }
        return bibliotecaByNombre

    }
    fun actualizarBibliotecaByNombre(bibliotecaActual: Biblioteca, bibliotecaNueva:Biblioteca){

        eliminarBiblioteca(bibliotecaActual.nombre)
        bibliotecas.add(bibliotecaNueva)

        guardarBibliotecasEnArchivo()
    }
    fun eliminarBiblioteca(nombre: String?){
        bibliotecas.removeIf{ it.nombre == nombre }

        guardarBibliotecasEnArchivo()
    }

    //Logica relacionada con los libros
    fun guardarLibro( biblioteca: Biblioteca,libro: Libro){
        biblioteca.libros.add(libro);
        println("Los datos guardados son ${libro}")
    }
    fun eliminarLibro(biblioteca: Biblioteca,libro: Libro){
        biblioteca.libros.remove(libro)
    }
    fun actualizarLibros(biblioteca: Biblioteca,libroActual: Libro, libroNueevo: Libro){
        biblioteca.libros.remove(libroActual)
        biblioteca.libros.add(libroNueevo)
    }
    fun obtenerLibros(biblioteca: Biblioteca,): List<Libro>{
        return  biblioteca.libros
    }
    fun obtenerPorAutor(biblioteca: Biblioteca,autor:String): List<Libro>{
        val librosPorAutor: java.util.ArrayList<Libro> = java.util.ArrayList()
        for (libro in  biblioteca.libros ){
            if (libro.getAutor() == autor){
                librosPorAutor.add(libro)
            }
        }
        return librosPorAutor
    }
    fun obtenerPorTitulo(biblioteca: Biblioteca,titulo:String): List<Libro>{
        val librosPorTitulo: java.util.ArrayList<Libro> = java.util.ArrayList()
        for (libro in  biblioteca.libros ){
            if (libro.getTitulo() == titulo){
                librosPorTitulo.add(libro)
            }
        }
        return librosPorTitulo
    }





}
