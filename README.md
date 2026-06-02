# QuizGeek BR

QuizGeek BR é um aplicativo de quiz para Android focado em cultura pop, séries e animes.

O app traz diversos questionários temáticos com perguntas sobre:

- Game of Thrones  
- Stranger Things  
- The Mandalorian  
- Fullmetal Alchemist: Brotherhood  
- Prison Break  
- Breaking Bad  
- The Boys  
- Attack on Titan  
- Dragon Ball  
- Naruto  
- One Piece  
- Outros animes e séries

Cada tema possui uma sequência de perguntas de múltipla escolha, feedback visual para respostas certas e erradas, trilha sonora de fundo, efeitos sonoros e mensagens personalizadas no final de cada quiz.


## Tecnologias utilizadas

- **Linguagem:** Kotlin  
- **Plataforma:** Android  
- **Arquitetura:** baseada em Activities (uma por tema de quiz)  
- **Multimídia:**
  - `MediaPlayer` para sons (acertos, erros, vitória, falha)
  - `VideoView` para a tela de splash com vídeo de introdução


## Estrutura principal do projeto

Algumas classes importantes:

- `SplashActivity.kt`  
  - Exibe um vídeo de introdução (`intro` em `res/raw`) usando `VideoView`  
  - Ao fim do vídeo, abre a tela inicial (`Inicial`)

- `Inicial.kt`  
  - Tela inicial / menu principal do app  
  - Navega para outras telas, como créditos e seleção de categorias

- `Two.kt`  
  - Tela de seleção principal dos modos:
    - `Three2`: séries (Game of Thrones, Stranger Things, The Mandalorian, etc.)  
    - `Three`: animes (Dragon Ball, Naruto, One Piece, etc.)

- `Three2.kt`  
  - Menu de séries:
    - `Eleven` – Game of Thrones  
    - `Twelve` – Stranger Things  
    - `Thirteen` – The Mandalorian  
    - `Fourteen` – The Witcher  
    - `Fifteen` – Breaking Bad  
    - `Sixteen` – Prison Break  
    - `Seventeen` – Supernatural  
    - `Eighteen` – The Boys  

- `Three.kt`  
  - Menu de animes:
    - `Four` – Dragon Ball  
    - `Five` – Naruto  
    - `Six` – One Piece  
    - `Seven` – Jujutsu Kaisen  
    - `Eight` – Attack on Titan  
    - `Nine` – Demon Slayer  
    - `Ten` – Fullmetal Alchemist: Brotherhood  

- `MusicService.kt`  
  - Serviço responsável pela trilha sonora em background  
  - Iniciado a partir da `BaseActivity`

- `BaseActivity.kt`  
  - Activity base que:
    - Configura o layout padrão (atividade inicial)  
    - Garante que o `MusicService` seja iniciado quando a primeira activity é criada

- `Creditos.kt`  
  - Tela de créditos do app

Cada Activity de quiz (como `Eleven`, `Twelve`, `Thirteen`, `Ten`, `Sixteen`, `Fifteen`, `Eight`, `Eighteen`, etc.) segue um padrão:

- Lista de perguntas (`questions`) com:
  - Texto da pergunta
  - Resposta correta
  - Lista de alternativas
- Quatro botões de resposta (`buttonAnswer1` a `buttonAnswer4`)
- Lógica para:
  - Embaralhar respostas  
  - Validar resposta clicada  
  - Colorir botões (verde/vermelho)  
  - Contabilizar acertos  
  - Reproduzir sons de acerto/erro  
  - Exibir mensagem final personalizada de acordo com a pontuação  
  - Voltar para a tela de seleção (`Two`)


## Recursos de áudio e vídeo

Na pasta `res/raw`, o projeto utiliza:

- `intro` – vídeo exibido na `SplashActivity`
- `sound_win` – som de acerto
- `sound_wrong` – som de erro
- `sound_celebration` – som de vitória (bom desempenho)
- `sound_fail` – som de falha (baixo desempenho)


## Como executar o projeto

1. Abra o projeto no **Android Studio** (ou importe o projeto Gradle).
2. Conecte um dispositivo Android ou use um emulador.
3. Compile e rode o app:
   - Menu *Run > Run 'app'* ou ícone de **Play**.


## Roadmap / Possíveis melhorias

- Adicionar mais categorias e perguntas
- Salvar pontuações e histórico do usuário (leaderboard local)
- Modo desafio (tempo limitado por pergunta)
- Tradução para outros idiomas
- Refatoração para uma arquitetura com ViewModel / Jetpack (futuro)
- Tela de configurações (mutar sons, escolher temas, etc.)


## Créditos

- Desenvolvimento: **Gustavo de Oliveira** (ajuste com seu nome completo ou nick)  
- Conteúdo de perguntas: **Autor(es) do projeto QuizGeek BR**  
- Projeto criado como um quiz de cultura geek para fãs de séries e animes.
  

## Licença

Este projeto está licenciado sob os termos da licença MIT.

Você pode copiar, modificar e distribuir este software, desde que mantenha o aviso de copyright e a licença original.
