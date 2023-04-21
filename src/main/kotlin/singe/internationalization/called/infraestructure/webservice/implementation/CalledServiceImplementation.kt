package singe.internationalization.called.infraestructure.webservice.implementation

import org.springframework.web.bind.annotation.*
import singe.internationalization.called.domain.entities.Called
import singe.internationalization.called.domain.usecases.CalledUseCase
import singe.internationalization.called.domain.usecases.response.CalledResponse
import singe.internationalization.called.infraestructure.webservice.CalledService


@RestController
@RequestMapping("/called")
class CalledServiceImplementation(
    val usecase: CalledUseCase,
) : CalledService {

    @PostMapping
    override fun createAndUpdate(@RequestBody called: Called): CalledResponse? {
        return usecase.createAndUpdate(called)
    }

    @GetMapping("")
    override fun getCalled(): List<Called>? {
        return usecase.getCalled();
    }
}