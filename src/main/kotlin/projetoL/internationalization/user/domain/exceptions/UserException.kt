package projetoL.internationalization.user.domain.exceptions

import projetoL.core.shared.error.GenericError

val USUARIO_NAO_EXISTE = UserException("USUARIO_NAO_EXISTE","Um erro ocorreu porque o usuário não está cadastrado")
val UM_ERRO_OCORREU_NO_USUARIO = UserException("UM_ERRO_OCORREU_NO_USUARIO","Um erro ocorreu na parte do usuário")
val NOME_DO_USUARIO_NAO_INFORMADO = UserException("NOME_DO_USUARIO_NAO_INFORMADO","Um erro ocorreu porque nome do usuário não foi informado")
val TIPO_DE_USUARIO_NAO_EXISTE = UserException("TIPO_DE_USUARIO_NAO_EXISTE","Um erro ocorreu porque o tipo de usuário informado não existe")
val TIPO_DE_USUARIO_NAO_INFORMADO = UserException("TIPO_DE_USUARIO_NAO_INFORMADO","Um erro ocorreu porque o tipo de usuário não foi informado")
val IS_ACTIVE_NAO_INFORMADO = UserException("IS_ACTIVE_NAO_INFORMADO","Um erro ocorreu porque o is_active não foi informado")
val EMAIL_NAO_INFORMADO = UserException("EMAIL_NAO_INFORMADO","Um erro ocorreu porque o email não foi informado")
val AUTHENTICATOR_RECORD_NAO_INFORMADO = UserException("AUTHENTICATOR_RECORD_NAO_INFORMADO","Um erro ocorreu porque o autenticador de usuário não foi informado")
val PASSWORD_NAO_INFORMADO = UserException("PASSWORD_NAO_INFORMADO","Um erro ocorreu porque a senha do usuário não foi informado")
val CONTATO_NAO_INFORMADO = UserException("CONTATO_NAO_INFORMADO","Um erro ocorreu porque o contato do usuário não foi informado")
val USUARIO_E_SENHA_INVALIDOS = UserException("USUARIO_E_SENHA_INVALIDOS","Um erro ocorreu porque o usuário e senha não estão corretos")
val USUARIO_NAO_INFORMADO = UserException("USUARIO_NAO_INFORMADO","Um erro ocorreu porque o usuário não informado")
val SENHAS_NAO_CONFERE = UserException("SENHAS_NAO_CONFERE","Um erro ocorreu porque as senhas não conferem")
val SENHA_ANTIGA_NAO_CONFERE = UserException("SENHA_ANTIGA_NAO_CONFERE","Um erro ocorreu porque a senha antiga não confere")
val SENHA_ANTIGA_IGUAL_ATUAL = UserException("SENHA_ANTIGA_IGUAL_ATUAL","Um erro ocorreu porque a senha antiga é igual a atual")
val SENHA_ANTIGA_NAO_INFORMADA = UserException("SENHA_ANTIGA_NAO_INFORMADA","Um erro ocorreu porque senha antiga não foi informada")
val SENHA_DE_CONFIRMACAO_NAO_INFORMADA = UserException("SENHA_DE_CONFIRMACAO_NAO_INFORMADA","Um erro ocorreu porque a senha de confirmação não informada")
val SENHA_NOVA_NAO_INFORMADA = UserException("SENHA_NOME_NAO_INFORMADA","Um erro ocorreu porque a senha nova não foi informada")
class UserException(
    code: String,
    description: String
) : GenericError("user-module", code, description)