package com.example.ta3

class Users(var id: String, var nama: String, var email: String, var sekolah: String, var password: String, var user: String) {

    constructor() : this("", "", "", "","", "siswa") {

        fun toMap(): Map<String, Any?>{
            return mapOf(
                "nama" to nama,
                "email" to email,
                "sekolah" to sekolah
            )
        }

    }


}
