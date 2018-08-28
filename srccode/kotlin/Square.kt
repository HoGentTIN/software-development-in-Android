package l

import o.Rectangle

/**
 * This class represents a square. In mathematics, a Square is a Rectangle. Indeed it is a
 * specialization of a rectangle. The "is a" makes you want to model this with inheritance.
 * However if in code you made Square derive from Rectangle, then a Square should be
 * usable anywhere you expect a Rectangle. This makes for some strange behavior.
 */
class Square : Rectangle() {

    /**
     * We override the property height from rectangle
     */
    override var height: Int
        get() = super.height
        set(value) { height = value}

    /**
     * We override the property width from rectangle.
     */
    override var width: Int
        get() = super.width
        set(value) {width  = value}
}