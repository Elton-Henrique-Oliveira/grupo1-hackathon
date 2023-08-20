package projetoL.core.shared.error

/**
 * This class should be used as a basis for creating predefined error types.
 */
abstract class GenericError(
    /**
     * The name of the module that defined the actual specification of this error.
     */
    val moduleName: String,

    /**
     * A code representing this error.
     *
     * Obs.: Note: Follow the same constant naming standards: UPPER_CASE_SEPARATED_BY_UNDERLINE
     */
    val code: String,

    /**
     * The description of this error.
     */
    val description: String,
)
