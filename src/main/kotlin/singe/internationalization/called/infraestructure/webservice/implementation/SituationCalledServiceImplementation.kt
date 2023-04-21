package singe.internationalization.called.infraestructure.webservice.implementation

import org.springframework.web.bind.annotation.*
import singe.internationalization.called.domain.entities.SituationCalled
import singe.internationalization.called.domain.usecases.SituationCalledUseCase
import singe.internationalization.called.infraestructure.webservice.SituationCalledService


@RestController
@RequestMapping("/situationCalled")
class SituationCalledServiceImplementation(
        val situationCalledUserCase: SituationCalledUseCase,
) : SituationCalledService {
    @GetMapping("")
    override fun getSituationCalled(): List<SituationCalled>? {
        return situationCalledUserCase.getSituationCalled();
    }
}

