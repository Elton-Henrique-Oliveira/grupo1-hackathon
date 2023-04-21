package br.com.lince.singe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "br.com.lince.singe",
        "org.jetbrains.exposed.spring",
    ],
)
class SingeCoreApplication

fun main(args: Array<String>) {
    runApplication<SingeCoreApplication>(*args)
}