package o

class AreaCalculator{

    fun calculateArea(shapes : MutableCollection<Rectangle>) : Unit {
        var area = 0
        for (rectangle in shapes){
            area += rectangle.height * rectangle.width
        }
        println("The calculated area is : $area")
    }

}