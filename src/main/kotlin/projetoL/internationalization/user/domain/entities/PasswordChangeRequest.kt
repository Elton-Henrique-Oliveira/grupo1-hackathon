package projetoL.internationalization.user.domain.entities

import java.util.UUID

/**
 * Data required to reset a user password.
 *
 * This data must be used in together with either:
 *  - the current authentication scope, to reset the current user password
 */
data class PasswordChangeRequest(

    var userUUID: UUID,

    var enterpriseUUID: UUID,
    /**
     * The current password of this user.
     */
    var currentPassword: String,

    /**
     * The user authentication record
     */
    var newPassword: String,

    /**
     * The user authentication record
     */
    var newPasswordConfirmation: String
)
