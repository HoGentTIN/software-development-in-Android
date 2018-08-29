package i

/**
 * The Component interface. We can use these components on different flavours of devices.
 */
interface Component {

    /**
     * Function to address a mouseOver event
     */
    fun mouseover(event: String)

    /**
     * Addressing a touch event
     */
    fun touch(event: String)

    /**
     * Adressing a swipe event
     */
    fun swipe(event: String)

    /**
     * Validate that the layout of the component is ok
     */
    fun validate()

}