package singe.internationalization.called.infraestructure.webservice.implementation

import org.springframework.web.bind.annotation.*
import singe.internationalization.called.domain.entities.ReturnedCalled
import singe.internationalization.called.domain.usecases.ReturnedCalledUseCase
import singe.internationalization.called.domain.usecases.response.ReturnedCalledResponse
import singe.internationalization.called.infraestructure.webservice.ReturnedCalledService
import java.util.*


@RestController
@CrossOrigin(
    origins = ["http://10.0.11.143:3000", "http://10.0.11.77:3000", "http://10.0.11.139:3000"],
    allowCredentials = "true"
)
@RequestMapping("/returnedCalled")
class ReturnedCalledServiceImplementation(
    val useCase: ReturnedCalledUseCase,
) : ReturnedCalledService {

    @PostMapping
    override fun createAndUpdate(
        @RequestBody returnedCalled: ReturnedCalled
    ): ReturnedCalledResponse? {
        return useCase.createAndUpdate(returnedCalled)
    }

    @GetMapping("")
    override fun getReturnedCalled(): List<ReturnedCalled>? {
        return useCase.getReturnedCalled()
    }

    @GetMapping("/{uuid}")
    override fun getReturnedCalledByUUID(@PathVariable("uuid") uuid: UUID): List<ReturnedCalled>? {
        return useCase.getReturnedCalledByUUID(uuid)
    }
}