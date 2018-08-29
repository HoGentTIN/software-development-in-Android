package d

class Superworker(name: String) {

    var name : String

    init {
        this.name = name
    }

    fun work(){
        println("$name is doing awesome superwork")
    }
}