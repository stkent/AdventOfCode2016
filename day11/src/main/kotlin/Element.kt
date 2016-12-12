enum class Element(val abbreviation: String) {
  Hydrogen("H"),
  Lithium("Li"),
  Plutonium("Pu"),
  Promethium("Pm"),
  Ruthenium("Ru"),
  Strontium("Sr"),
  Thulium("Tm");

  override fun toString(): String {
    return abbreviation
  }

}
