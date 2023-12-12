object SpaceAge {
  def onEarth(age: Double): Double = ageInPlanetYears(age, 1)

  def onVenus(age: Double): Double = ageInPlanetYears(age, 0.61519726)

  def onMercury(age: Double): Double = ageInPlanetYears(age, 0.2408467)

  def onMars(age: Double): Double = ageInPlanetYears(age, 1.8808158)

  def onJupiter(age: Double): Double = ageInPlanetYears(age, 11.862615)

  def onSaturn(age: Double): Double = ageInPlanetYears(age, 29.447498)

  def onUranus(age: Double): Double = ageInPlanetYears(age, 84.016846)

  def onNeptune(age: Double): Double = ageInPlanetYears(age, 164.79132)

  private def ageInPlanetYears(ageInSec: Double, planetOrbitInEarthYears: Double): Double = {
    var ageInPlanetYears: Double = ageInSec / (31557600 * planetOrbitInEarthYears)
    f"$ageInPlanetYears%.2f".toFloat
  }
}




// Instructions
// Given an age in seconds, calculate how old someone would be on:

// Mercury: orbital period 0.2408467 Earth years
// Venus: orbital period 0.61519726 Earth years
// Earth: orbital period 1.0 Earth years, 365.25 Earth days, or 31557600 seconds
// Mars: orbital period 1.8808158 Earth years
// Jupiter: orbital period 11.862615 Earth years
// Saturn: orbital period 29.447498 Earth years
// Uranus: orbital period 84.016846 Earth years
// Neptune: orbital period 164.79132 Earth years
// So if you were told someone were 1,000,000,000 seconds old, you should be able to say that they're 31.69 Earth-years old.
