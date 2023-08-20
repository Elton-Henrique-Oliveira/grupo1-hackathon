package projetoL.internationalization.enterprise.domain.exceptions

import projetoL.core.shared.error.GenericError

val EMPRESA_NAO_CADASTRADA =
    EnterpriseException("EMPRESA_NAO_CADASTRADA", "Um erro ocorreu porque a empresa não está cadastrada no sistema")
val EMPRESA_JA_CADASTRADA =
    EnterpriseException("EMPRESA_JA_CADASTRADA", "Um erro ocorreu porque a empresa já está cadastrada no sistema")
val SERVIDOR_NAO_ENCONTRADO =
    EnterpriseException("SERVIDOR_NAO_ENCONTRADO", "Um erro ocorreu porque o servidor informado não está cadastrado")
val NOME_NAO_INFORMADO =
    EnterpriseException("NOME_NAO_INFORMADO", "Um erro ocorreu porque o nome da empresa não foi informado")
val UM_ERRO_OCORREU_NA_EMPRESA =
    EnterpriseException("UM_ERRO_OCORREU_NA_EMPRESA", "Um erro ocorreu na parte da empresa")

class EnterpriseException(
    code: String,
    description: String
) : GenericError("enterprise-module", code, description)