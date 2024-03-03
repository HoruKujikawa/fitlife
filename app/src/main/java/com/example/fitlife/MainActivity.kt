package com.example.fitlife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("hola")
        val url = "jdbc:mysql://localhost:3306/fitlife"
        val user = "root"
        val password = ""

         try {
            DriverManager.getConnection(url, user, password)
             println("entre satisfactoriamente")
        } catch (e: SQLException) {
             println("Error al intentar establecer la conexión:")
             e.printStackTrace()
         }
        data class Usuario(val id: Int, val nombre: String, val edad: Int)
        fun getAllUsers(connection: Connection): List<Usuario> {
            val sql = "SELECT * FROM usuario"
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery(sql)

            val users = ArrayList<Usuario>()

            while (resultSet.next()) {
                val id = resultSet.getInt("id")
                val nombre = resultSet.getString("nombre")
                val edad = resultSet.getInt("edad")

                val usuario = Usuario(id, nombre, edad)
                users.add(usuario)
            }

            return users
        }
    }

}