package br.com.lince.singe.core.shared.webservice

/**
 * This is the basic info used to decode filters
 */
data class BasicFilter(
    /**
     * The name of the column to be filtered
     */
    val name: String,

    /**
     * The actual value to filter
     */
    val value: String,
) {
    override fun toString(): String = "BasicFilter=($name, $value)"
}
