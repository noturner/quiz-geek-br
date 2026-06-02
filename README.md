QuizGeek BR
QuizGeek BR é um aplicativo de quiz para Android focado em cultura pop, séries e animes.
O app traz diversos questionários temáticos com perguntas sobre:

Game of Thrones
Stranger Things
The Mandalorian
Fullmetal Alchemist: Brotherhood
Prison Break
Breaking Bad
The Boys
Attack on Titan
One Piece
Outros animes e séries (Dragon Ball, Naruto, etc.)
Cada tema possui uma sequência de perguntas de múltipla escolha, feedback visual para respostas certas e erradas, trilha sonora, efeitos sonoros e mensagens personalizadas no final de cada quiz.

Tecnologias utilizadas
Linguagem: Kotlin
Plataforma: Android
Arquitetura: Activities e Services
Bibliotecas principais:
AndroidX AppCompat
MediaPlayer (para sons de vitória/derrota, trilha, etc.)
VideoView (tela de splash com vídeo)
Estrutura principal do projeto
Algumas classes importantes:

SplashActivity

Exibe um vídeo de introdução (intro) usando VideoView e depois redireciona para a tela inicial (Inicial).
Inicial

Tela inicial do app (não exibida aqui, mas presente no projeto) que leva o usuário para o menu de categorias.
Two

Tela de menu principal para escolher categorias de quiz, levando para:
Three (animes)
Three2 (séries)
Menus de categorias:

Three – Menu de animes (Dragon Ball, Naruto, One Piece, etc.)
Three2 – Menu de séries (Game of Thrones, Stranger Things, Breaking Bad, etc.)
Telas de quizzes (exemplos):

Four, Five, Six, Seven, Eight, Nine, Ten – Quizzes de animes/mangás
Eleven, Twelve, Thirteen, Fourteen, Fifteen, Sixteen, Seventeen, Eighteen – Quizzes de séries
BaseActivity

Classe base para as Activities do app.
Responsável por iniciar o MusicService (música de fundo) quando a primeira Activity é criada.
MusicService

Serviço que controla a música de fundo enquanto o app está aberto.
Creditos

Tela de créditos do aplicativo.
Funcionamento dos quizzes
Cada Activity de quiz (por exemplo, Eleven, Twelve, Thirteen etc.) segue um padrão:

Uma lista de perguntas é definida em código via uma lista de Question:
questionText – enunciado
correctAnswer – resposta correta
answers – lista de 4 alternativas
As perguntas são embaralhadas com .shuffled().
Cada pergunta:
Exibe o texto e um contador do tipo Pergunta X/Y
Mostra quatro botões de resposta
Ao clicar:
Resposta correta: botão fica verde, som de acerto toca, pontuação é incrementada
Resposta errada: botão fica vermelho, a alternativa correta é destacada, som de erro toca
Ao final:
Calcula a pontuação percentual
Mostra mensagem personalizada conforme o desempenho
Exibe quantas perguntas o usuário acertou
Toca um som de celebração ou de falha
Depois de alguns segundos, retorna ao menu (Two)
Como executar o projeto
Clonar o repositório
   git clone https://github.com/seu-usuario/quizgeek-br.git
   cd quizgeek-br
Abrir no Android Studio

Abra o Android Studio
Clique em "Open an Existing Project"
Selecione a pasta do projeto
Build e run

Aguarde o Gradle sincronizar
Conecte um dispositivo Android ou inicie um emulador
Clique em "Run" (botão ▶) para instalar e rodar o app
Estrutura de arquivos (resumo)
app/src/main/java/com/team7dev/quizgeekbr/
BaseActivity.kt
SplashActivity.kt
Inicial.kt
Two.kt, Three.kt, Three2.kt
Activities de quiz: Four.kt até Eighteen.kt
MusicService.kt
Creditos.kt
QuizGeekBRApplication.kt
app/src/main/res/layout/
Layouts para cada Activity (telas de quiz, menu, splash, etc.)
app/src/main/res/raw/
intro (vídeo de abertura)
sound_win, sound_wrong, sound_celebration, sound_fail (efeitos sonoros)
Roadmap / Possíveis melhorias
Adicionar mais categorias e perguntas
Salvar pontuações e histórico do usuário
Sistema de ranking/local leaderboard
Tradução para outros idiomas
Migração para uma arquitetura com ViewModel e LiveData/Compose (futuro)
Créditos
Desenvolvimento: [seu nome / time]
Perguntas e conteúdo: [seu nome / colaboradores]
Projeto criado como um quiz de cultura geek para fãs de séries e animes.
