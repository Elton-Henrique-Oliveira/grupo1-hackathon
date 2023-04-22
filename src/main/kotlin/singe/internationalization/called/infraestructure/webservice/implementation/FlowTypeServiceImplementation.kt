package singe.internationalization.called.infraestructure.webservice.implementation

import org.springframework.web.bind.annotation.*
import singe.internationalization.called.domain.entities.FlowType
import singe.internationalization.called.domain.usecases.FlowTypeUseCase
import singe.internationalization.called.infraestructure.webservice.FlowTypeService
import java.util.*

@RestController
@CrossOrigin(
    origins = ["http://10.0.11.143:3000", "http://10.0.11.76:3000", "http://10.0.11.139:3000"],
    allowCredentials = "true"
)

@RequestMapping("/flowType")
class FlowTypeServiceImplementation(
    val flowTypeUserCase: FlowTypeUseCase,
) : FlowTypeService {

    @GetMapping("")
    override fun getFlowType(): List<FlowType>? {
        return flowTypeUserCase.getFlowType();
    }

    @GetMapping("/{flowUUID}")
    override fun getFlowTypeByFlowUUID(
        @PathVariable("flowUUID")  flowUUID: UUID,
    ): List<FlowType>? {
        return flowTypeUserCase.getFlowTypeByFlowUUID(flowUUID);
    }

}

