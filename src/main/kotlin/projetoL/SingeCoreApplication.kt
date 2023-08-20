package projetoL

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "projetoL",
        "org.jetbrains.exposed.spring",
    ],
)
class SingeCoreApplication

fun main(args: Array<String>) {
    runApplication<SingeCoreApplication>(*args)
}