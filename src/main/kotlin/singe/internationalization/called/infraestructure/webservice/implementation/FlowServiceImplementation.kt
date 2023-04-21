package singe.internationalization.called.infraestructure.webservice.implementation

import org.springframework.web.bind.annotation.*
import singe.internationalization.called.domain.entities.Flow
import singe.internationalization.called.domain.usecases.FlowUseCase
import singe.internationalization.called.infraestructure.webservice.FlowService


@RestController
@RequestMapping("/flow")
class FlowServiceImplementation(
        val flowUserCase: FlowUseCase,
) : FlowService {
    @GetMapping("")
    override fun getFlow(): List<Flow>? {
        return flowUserCase.getFlow();
    }
}
