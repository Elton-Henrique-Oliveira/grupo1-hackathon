package singe.internationalization.called.infraestructure.webservice.implementation

import org.springframework.web.bind.annotation.*
import singe.internationalization.called.domain.entities.CalledStatus
import singe.internationalization.called.domain.usecases.CalledStatusUseCase
import singe.internationalization.called.infraestructure.webservice.CalledStatusService
import java.util.*

@RestController
@CrossOrigin(
        origins = ["http://10.0.11.143:3000", "http://10.0.11.76:3000", "http://10.0.11.139:3000"],
        allowCredentials = "true"
)

@RequestMapping("/calledStatus")
class CalledStatusServiceImplementation(
        val calledStatusUserCase: CalledStatusUseCase,
) : CalledStatusService {

    @GetMapping("")
    override fun getCalledStatus(): List<CalledStatus>? {
        return calledStatusUserCase.getCalledStatus();
    }
}


