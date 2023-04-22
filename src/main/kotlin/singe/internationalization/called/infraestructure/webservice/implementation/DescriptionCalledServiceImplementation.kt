package singe.internationalization.called.infraestructure.webservice.implementation

import org.springframework.web.bind.annotation.*
import singe.internationalization.called.domain.entities.DescriptionCalled
import singe.internationalization.called.domain.usecases.DescriptionCalledUseCase
import singe.internationalization.called.domain.usecases.response.DescriptionCalledResponse
import singe.internationalization.called.infraestructure.webservice.DescriptionCalledService
import java.util.*

@RestController
@CrossOrigin(
    origins = ["http://10.0.11.143:3000", "http://10.0.11.77:3000", "http://10.0.11.139:3000"],
    allowCredentials = "true"
)
@RequestMapping("/descriptionCalled")
class DescriptionCalledServiceImplementation(
    val useCase: DescriptionCalledUseCase
) : DescriptionCalledService {

    @PostMapping
    override fun createAndUpdate(
        @RequestBody descriptionCalled: DescriptionCalled
    ): DescriptionCalledResponse? {
        return useCase.createAndUpdate(descriptionCalled)
    }

    @GetMapping("")
    override fun getDescriptionCalled(): List<DescriptionCalled>? {
        return useCase.getDescriptionCalled()
    }

    @GetMapping("/{uuid}")
    override fun getDescriptionCalledByUUID(@PathVariable("uuid") uuid: UUID): DescriptionCalled? {
        return useCase.getDescriptionCalledByUUID(uuid)
    }
}