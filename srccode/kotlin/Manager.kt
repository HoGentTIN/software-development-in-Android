package d

/**
 * Manager class. Manages only 1 worker for simplicity reasons.
 */
class Manager(var worker : Worker) {

    init {
        this.worker = worker
    }

    /**
     * Manages its worker
     */
    fun manage(){
        worker.work()
    }
}
