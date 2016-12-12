data class Chip(val element: Element) : Component {

  val complementaryGenerator: Generator by lazy {
    Generator(element)
  }

  override fun toString(): String {
    return "${element}C"
  }

}
