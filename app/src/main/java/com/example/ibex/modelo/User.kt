package com.example.ibex.modelo

data class User(
    var data: Data
)
{
    data class Data(
        var id:Int,
        var nombre:String,
        var apellidos:String,
        var email:String,
        var password:String,
        var avatar:String,
        var tipo: Tipo,
        var perfil: Perfil,
        var estatus: Estatus,
        var fechaInsert:Float)
    {
        data class Tipo(
            var id:Int,
            var descripcion:String,
        )
        data class Perfil(
            var id:Int,
            var descripcion:String,
        )
        data class Estatus(
            var id:Int,
            var descripcion:String,
        )
    }


}
