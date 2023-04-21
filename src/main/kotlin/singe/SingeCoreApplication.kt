package singe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "singe",
        "org.jetbrains.exposed.spring",
    ],
)
class SingeCoreApplication

fun main(args: Array<String>) {
    runApplication<SingeCoreApplication>(*args)
}