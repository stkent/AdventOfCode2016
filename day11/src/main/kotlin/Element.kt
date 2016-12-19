enum class Element(val abbreviation: String) {
  Cobalt("Co"),
  Curium("Cm"),
  Dilithium("Li2"),
  Elerium("El"),
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
