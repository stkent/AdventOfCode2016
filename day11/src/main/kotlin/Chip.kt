class Chip(element: Element) : Component {

  override val element: Element

  val complementaryGenerator: Generator by lazy {
    Generator(element)
  }

  init {
    this.element = element
  }

  // Generated implementations

  override fun toString(): String {
    return "${element}C"
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other?.javaClass != javaClass) return false

    other as Chip

    if (element != other.element) return false

    return true
  }

  override fun hashCode(): Int {
    return element.hashCode()
  }

}
