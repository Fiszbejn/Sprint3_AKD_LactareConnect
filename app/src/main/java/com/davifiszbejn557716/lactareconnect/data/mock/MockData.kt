package com.davifiszbejn557716.lactareconnect.data.mock

import com.davifiszbejn557716.lactareconnect.data.model.BancoLeiteLactare
import com.davifiszbejn557716.lactareconnect.data.model.CategoriaFaq
import com.davifiszbejn557716.lactareconnect.data.model.CategoriaRecompensa
import com.davifiszbejn557716.lactareconnect.data.model.CorCard
import com.davifiszbejn557716.lactareconnect.data.model.DiaAgenda
import com.davifiszbejn557716.lactareconnect.data.model.Endereco
import com.davifiszbejn557716.lactareconnect.data.model.ExamePreDoacao
import com.davifiszbejn557716.lactareconnect.data.model.HorarioAgenda
import com.davifiszbejn557716.lactareconnect.data.model.Mensagem
import com.davifiszbejn557716.lactareconnect.data.model.Nutriz
import com.davifiszbejn557716.lactareconnect.data.model.PerguntaFrequente
import com.davifiszbejn557716.lactareconnect.data.model.RemetenteMensagem
import com.davifiszbejn557716.lactareconnect.data.model.Recompensa
import com.davifiszbejn557716.lactareconnect.data.model.StatusExame

// Fonte única de dados mockados do app — sem backend, tudo em memória.
object MockData {

    val nutrizAtual = Nutriz(
        nomeCompleto = "Maria Teste Silva",
        iniciais = "MS",
        cpf = "178.737.044-38",
        email = "maria.teste@example.com",
        dataNascimento = "08/08/2001",
        telefone = "11987654321",
        endereco = Endereco(
            cep = "01310100",
            rua = "Avenida Paulista",
            numero = "1000",
            bairro = "Bela Vista",
            cidade = "São Paulo",
            uf = "SP"
        ),
        dataCadastro = "agosto de 2026",
        statusCadastro = "Cadastro em análise",
        bancoVinculadoId = "banco-1"
    )

    val bancos = listOf(
        BancoLeiteLactare("banco-1", "Lactare - Araújo", "Atende toda a cidade"),
        BancoLeiteLactare("banco-2", "Lactare - Barueri", "Atende toda a cidade"),
        BancoLeiteLactare("banco-3", "Lactare - Caieiras", "Atende toda a cidade"),
        BancoLeiteLactare("banco-4", "Lactare - Cajamar", "Atende toda a cidade"),
        BancoLeiteLactare("banco-5", "Lactare - Carapicuíba", "Atende toda a cidade")
    )

    val exames = listOf(
        ExamePreDoacao("ex-1", "Hemograma completo", StatusExame.PENDENTE),
        ExamePreDoacao("ex-2", "Sorologia HIV", StatusExame.PENDENTE),
        ExamePreDoacao("ex-3", "VDRL (sífilis)", StatusExame.PENDENTE),
        ExamePreDoacao("ex-4", "Sorologia hepatites B e C", StatusExame.PENDENTE)
    )

    val diasAgenda = listOf(
        DiaAgenda("Seg", 12),
        DiaAgenda("Ter", 13),
        DiaAgenda("Qua", 14),
        DiaAgenda("Qui", 15),
        DiaAgenda("Sex", 16)
    )

    val horariosAgenda = listOf(
        HorarioAgenda("08:00"),
        HorarioAgenda("09:30"),
        HorarioAgenda("11:00"),
        HorarioAgenda("13:30")
    )

    val faqs = listOf(
        PerguntaFrequente(
            "faq-1", CategoriaFaq.DOACAO,
            "Quem pode ser uma pessoa doadora de leite humano?",
            "Mães saudáveis, sem uso de medicamentos contraindicados, que produzem mais leite do que o bebê consome. O cadastro no app já verifica os principais critérios."
        ),
        PerguntaFrequente(
            "faq-2", CategoriaFaq.DOACAO,
            "Como eu começo a doar?",
            "Basta se cadastrar no app, enviar os exames pré-doação e agendar a coleta em casa. Nossa equipe cuida do resto."
        ),
        PerguntaFrequente(
            "faq-3", CategoriaFaq.DOACAO,
            "Doar leite humano prejudica a amamentação do meu bebê?",
            "Não. As orientações do app garantem que você só doe o excedente, sem afetar a amamentação do seu próprio bebê."
        ),
        PerguntaFrequente(
            "faq-4", CategoriaFaq.DOACAO,
            "Doar leite pelo Lactare tem algum custo?",
            "Não é totalmente gratuito. A coleta acontece na sua casa e você não precisa pagar nada em nenhuma etapa da doação."
        ),
        PerguntaFrequente(
            "faq-5", CategoriaFaq.EXAMES,
            "Quais exames são pedidos pra doar?",
            "Hemograma completo, sorologia HIV, VDRL (sífilis) e sorologia de hepatites B e C. São pedidos só uma vez."
        ),
        PerguntaFrequente(
            "faq-6", CategoriaFaq.EXAMES,
            "Onde eu envio o resultado dos exames?",
            "Direto pelo app, na tela de agendamento — você pode anexar um arquivo ou tirar uma foto do resultado."
        ),
        PerguntaFrequente(
            "faq-7", CategoriaFaq.EXAMES,
            "Os exames têm validade?",
            "Sim, nossa equipe avisa pelo app quando estiver perto de vencer e for preciso reenviar."
        ),
        PerguntaFrequente(
            "faq-8", CategoriaFaq.COLETA,
            "Como funciona a coleta em casa?",
            "Uma equipe da Lactare vai até sua casa no dia e horário agendados, recolhe o leite refrigerado e cuida do transporte até o banco de leite."
        ),
        PerguntaFrequente(
            "faq-9", CategoriaFaq.COLETA,
            "Posso trocar o dia da coleta?",
            "Sim, você pode reagendar pelo app até 24h antes do horário marcado."
        ),
        PerguntaFrequente(
            "faq-10", CategoriaFaq.COLETA,
            "Preciso estar em casa na hora da coleta?",
            "Sim, alguém precisa estar presente para entregar o leite refrigerado à nossa equipe."
        ),
        PerguntaFrequente(
            "faq-11", CategoriaFaq.ARMAZENAMENTO,
            "Como armazenar o leite até a coleta?",
            "Use potes esterilizados, identifique com data e horário da ordenha e mantenha congelado até a coleta."
        )
    )

    val conversaInicial = listOf(
        Mensagem(
            RemetenteMensagem.BOT,
            "Oi! Eu sou a Lila 💙 Estou aqui pra tirar suas dúvidas sobre doação de leite. Pode perguntar o que quiser.",
            "agora"
        )
    )

    const val saldoGotinhas = 0

    val recompensas = listOf(
        Recompensa(
            "rec-1", "Kit de cuidados pós-parto", "Farmácia Vida", 350,
            CategoriaRecompensa.AUTOCUIDADO, CorCard.NAVY,
            descricaoItens = listOf("Pomada para fissuras", "Absorvente para seios", "Chá de amamentação")
        ),
        Recompensa(
            "rec-2", "Sutiã de amamentação confortável", "Loja Maternidade Feliz", 500,
            CategoriaRecompensa.CONFORTO, CorCard.LIGHT_BLUE
        ),
        Recompensa(
            "rec-3", "Almofada de amamentação", "Loja Maternidade Feliz", 600,
            CategoriaRecompensa.CONFORTO, CorCard.TEAL
        ),
        Recompensa(
            "rec-4", "Sessão de massagem relaxante", "Espaço Bem Estar", 900,
            CategoriaRecompensa.BEM_ESTAR, CorCard.PINK
        )
    )
}
