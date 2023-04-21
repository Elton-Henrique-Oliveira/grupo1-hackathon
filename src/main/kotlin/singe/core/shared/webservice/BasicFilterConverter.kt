package br.com.lince.singe.core.shared.webservice

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

/**
 * This class converts the request params for application filters from the defined standard ({name}:{value}) to
 * the object representation, defined by the class BasicFilter.
 */
@Component
class BasicFilterConverter : Converter<String, BasicFilter?> {
    override fun convert(source: String): BasicFilter? {
        if (source.isBlank()) {
            return null
        }
        return source.substring(1, source.length - 1).split(":").run {
            BasicFilter(this[0], this[1])
        }
    }
}