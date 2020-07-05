package com.example.s2

class ApiEndPoint {
    companion object {

        private val SERVER = "https://bostoken.000webhostapp.com/"
        val CREATE = SERVER+"create.php"
        val READ = SERVER+"read.php"
        val DELETE = SERVER+"delete.php"
        val UPDATE = SERVER+"update.php"

    }
}