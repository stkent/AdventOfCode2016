class Generator(element: Element) : Component {

  override val element: Element

  init {
    this.element = element
  }

  // Generated implementations

  override fun toString(): String {
    return "${element}G"
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other?.javaClass != javaClass) return false

    other as Generator

    if (element != other.element) return false

    return true
  }

  override fun hashCode(): Int {
    return element.hashCode()
  }

}
