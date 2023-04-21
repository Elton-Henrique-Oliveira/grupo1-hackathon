package singe.internationalization.called.infraestructure.webservice.implementation

import org.springframework.web.bind.annotation.*
import singe.internationalization.called.domain.entities.Called
import singe.internationalization.called.domain.usecases.CalledUseCase
import singe.internationalization.called.domain.usecases.response.CalledResponse
import singe.internationalization.called.infraestructure.webservice.CalledService
import java.util.*


@RestController
@CrossOrigin(origins = ["http://10.0.11.143:3000"], allowCredentials = "true")
@RequestMapping("/called")
class CalledServiceImplementation(
    val usecase: CalledUseCase,
) : CalledService {

    @PostMapping
    override fun createAndUpdate(@RequestBody called: Called): CalledResponse? {
        println("porra")

        return usecase.createAndUpdate(called)
    }

    @GetMapping("")
    override fun getCalled(): List<Called>? {
        return usecase.getCalled();
    }

    @PostMapping("/{calledUUID}/{situationUUID}")
    override fun updateCalledSituation(
        @PathVariable("calledUUID") calledUUID: UUID,
        @PathVariable("situationUUID") situationUUID: Int
    ): Boolean {
        return usecase.updateCalledSituation(calledUUID, situationUUID)!!
    }

    @GetMapping("/{calledUUID}")
    override fun getCalledByUUID(
        @PathVariable("calledUUID") calledUUID: UUID,
    ): Called? {
        return usecase.getCalledByUUID(calledUUID);
    }
}