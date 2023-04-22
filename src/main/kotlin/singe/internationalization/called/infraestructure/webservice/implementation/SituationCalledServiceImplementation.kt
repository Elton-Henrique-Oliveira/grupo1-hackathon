package singe.internationalization.called.infraestructure.webservice.implementation

import org.springframework.web.bind.annotation.*
import singe.internationalization.called.domain.entities.SituationCalled
import singe.internationalization.called.domain.usecases.SituationCalledUseCase
import singe.internationalization.called.infraestructure.webservice.SituationCalledService


@RestController
@CrossOrigin(
    origins = ["http://10.0.11.143:3000", "http://10.0.11.76:3000", "http://10.0.11.139:3000"],
    allowCredentials = "true"
)
@RequestMapping("/situationCalled")
class SituationCalledServiceImplementation(
        val situationCalledUserCase: SituationCalledUseCase,
) : SituationCalledService {
    @GetMapping("")
    override fun getSituationCalled(): List<SituationCalled>? {
        return situationCalledUserCase.getSituationCalled();
    }
}

