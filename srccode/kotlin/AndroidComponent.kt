package i

class AndroidComponent : Component {


    /**
     * We stick to making things explicit in Kotlin.
     * And unlike Java, Kotlin requires explicit annotations
     * for overridable members (we call them open) and for overrides
     */
    override fun mouseover(event: String) {
        throw UnsupportedOperationException("mouse click not supported by android devices")

    }

    override fun touch(event: String) {
        println("Touch Event Fired")

    }

    override fun swipe(event: String) {
        println("Swipe Event Fired")

    }

    override fun validate() {
        println("All UI i valid")

    }

}