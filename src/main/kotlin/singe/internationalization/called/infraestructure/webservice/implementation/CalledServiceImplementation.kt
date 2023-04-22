package singe.internationalization.called.infraestructure.webservice.implementation

import br.com.lince.singe.core.shared.webservice.BasicFilter
import org.springframework.web.bind.annotation.*
import singe.internationalization.called.domain.entities.Called
import singe.internationalization.called.domain.usecases.CalledUseCase
import singe.internationalization.called.domain.usecases.response.CalledListAllResponse
import singe.internationalization.called.domain.usecases.response.CalledResponse
import singe.internationalization.called.infraestructure.webservice.CalledService
import java.util.*


@RestController
@CrossOrigin(
    origins = ["http://10.0.11.143:3000", "http://10.0.11.77:3000", "http://10.0.11.139:3000"],
    allowCredentials = "true"
)
@RequestMapping("/called")
class CalledServiceImplementation(
    val usecase: CalledUseCase,
) : CalledService {

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

    @GetMapping("/all")
    override fun listAllCalled(
        @RequestParam("page", required = false, defaultValue = "1") page: Int,
        @RequestParam("size", required = false, defaultValue = "30") size: Int,
        @RequestParam("orderBy", required = false, defaultValue = "") orderBy: String,
        @RequestParam("sortBy", required = false, defaultValue = "asc") sortBy: String,
        @RequestParam("filter", required = false) filter: List<BasicFilter>?,
    ): CalledListAllResponse {
        return usecase.listAllCalled(
            page = page,
            size = size,
            orderBy = orderBy,
            sortBy = sortBy,
            filters = filter
        )
    }
}