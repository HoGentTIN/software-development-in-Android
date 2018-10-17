package be.equality.dualpane.domain

import java.io.Serializable

class Comic(val imageResId: Int = 0, val name: String = "", val description: String ="", val url: String =" ",
            var text: String = "") : Serializable

