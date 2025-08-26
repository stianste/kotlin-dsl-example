package org.example

fun capitalizeWords(str: String): String =
  str.split(" ").joinToString(" ") { it.replaceFirstChar { c -> c.uppercaseChar() } }

fun String.capitalizeWordsExtensionFn(): String =
  split(" ").joinToString(" ") { it.replaceFirstChar { c -> c.uppercaseChar() } }

// Example usage with extension function
private val name = "john doe"
private val capitalizedNormally = capitalizeWords(name) // "John Doe"
private val capitalizedWithExtension = name.capitalizeWordsExtensionFn() // "John Doe"

fun main() {
  println("Capitalized normally: $capitalizedNormally")
  println("Capitalized with extension: $capitalizedWithExtension")
}
