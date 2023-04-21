package br.com.lince.singe.core.shared.utils

import java.security.MessageDigest
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class Utils {
    companion object {
        fun getRandomString(length: Int): String {
            val digits = ('0'..'9')
            val specials = "+%*!@#$?"
            val upperChar = ('A'..'Z')
            val lowerChar = ('a'..'z')
            val allowedChars = upperChar + lowerChar + digits + specials

            var randomString = ""

            val buf: ArrayList<String> = arrayListOf()

            buf.add((1..1).map { digits.random() }.joinToString(""))
            buf.add((1..1).map { specials.random() }.joinToString(""))
            buf.add((1..1).map { upperChar.random() }.joinToString(""))
            buf.add((1..1).map { lowerChar.random() }.joinToString(""))

            for (x in 4 until length) {
                buf.add((1..1).map { allowedChars.random() }.joinToString(""))
            }

            for (x in buf.shuffled()) {
                randomString += x
            }

            return randomString
        }

        fun dateConverter(string: String): LocalDateTime {
            val date = LocalDate.parse(string, DateTimeFormatter.ISO_DATE)
            return LocalDateTime.of(date, LocalTime.MIN)
        }

        fun dateConverterMax(string: String): LocalDateTime {
            val date = LocalDate.parse(string, DateTimeFormatter.ISO_DATE)
            return LocalDateTime.of(date, LocalTime.MAX)
        }

        fun hashSha512(input: String) = hashString("SHA-512", input)

        private fun hashString(type: String, input: String) =
            MessageDigest.getInstance(type).digest(input.toByteArray()).map { String.format("%02X", it) }
                .joinToString(separator = "")

        fun uuidOrEmpty(uuid: String): UUID {
            return try {
                UUID.fromString(uuid)
            } catch (e: Exception) {
                UUID.fromString("00000000-0000-0000-0000-000000000000")
            }
        }

        fun intOrZero(string: String): Int {
            return try {
                string.toInt()
            } catch (e: Exception) {
                0
            }
        }
    }
}