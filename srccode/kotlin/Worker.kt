package d

class Worker(name: String) {

    var name : String

    init {
        this.name = name;
    }

    fun work(){
        println("$name is working hard!")
    }
}