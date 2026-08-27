package com.davifiszbejn557716.lactareconnect.data.model

data class Endereco(
    val cep: String,
    val rua: String,
    val numero: String,
    val bairro: String,
    val cidade: String,
    val uf: String
)

data class Nutriz(
    val nomeCompleto: String,
    val iniciais: String,
    val cpf: String,
    val email: String,
    val dataNascimento: String,
    val telefone: String,
    val endereco: Endereco,
    val dataCadastro: String,
    val statusCadastro: String,
    val bancoVinculadoId: String
)

enum class StatusExame { ENVIADO, PENDENTE }

data class ExamePreDoacao(
    val id: String,
    val nome: String,
    val status: StatusExame
)

data class BancoLeiteLactare(
    val id: String,
    val nome: String,
    val descricaoAtendimento: String
)

data class DiaAgenda(
    val label: String,
    val diaMes: Int
)

data class HorarioAgenda(
    val label: String
)

enum class CategoriaFaq(val label: String) {
    DOACAO("Doação"),
    EXAMES("Exames"),
    COLETA("Coleta"),
    ARMAZENAMENTO("Armazenamento")
}

data class PerguntaFrequente(
    val id: String,
    val categoria: CategoriaFaq,
    val pergunta: String,
    val resposta: String
)

enum class RemetenteMensagem { BOT, USUARIO }

data class Mensagem(
    val remetente: RemetenteMensagem,
    val texto: String,
    val horario: String,
    val hint: String? = null,
    val acoes: List<String> = emptyList()
)

enum class CategoriaRecompensa(val label: String) {
    TODOS("Todos"),
    AUTOCUIDADO("Autocuidado"),
    CONFORTO("Conforto"),
    BEM_ESTAR("Bem Estar")
}

enum class CorCard { NAVY, LIGHT_BLUE, TEAL, PINK }

data class Recompensa(
    val id: String,
    val nome: String,
    val parceiro: String,
    val custoGotinhas: Int,
    val categoria: CategoriaRecompensa,
    val cor: CorCard,
    val descricaoItens: List<String> = emptyList()
)
