package singe.internationalization.called.infraestructure.webservice.implementation

import org.springframework.web.bind.annotation.*
import singe.internationalization.called.domain.entities.Called
import singe.internationalization.called.domain.usecases.ReturnFlowUseCase
import singe.internationalization.called.domain.usecases.response.CalledResponse
import singe.internationalization.called.infraestructure.webservice.CalledService
import java.util.*


@RestController
@CrossOrigin(
        origins = ["http://10.0.11.143:3000", "http://10.0.11.77:3000", "http://10.0.11.139:3000"],
        allowCredentials = "true"
)
@RequestMapping("/called")
class ReturnFlowServiceImplementation(
        val usecase: ReturnFlowUseCase,
) : ReturnFlowService {

    @PostMapping
    override fun createAndUpdate(@RequestBody called: Called): CalledResponse? {
        println("elton")
        return usecase.createAndUpdate(called)
    }

    @GetMapping("")
    override fun getCalled(): List<Called>? {
        return usecase.getCalled();
    }

    @PostMapping("/{calledUUID}/{situationUUID}")
    override fun updateCalledSituation(
            @PathVariable("calledUUID") calledUUID: UUID,
            @PathVariable("situationUUID") situationUUID: UUID
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