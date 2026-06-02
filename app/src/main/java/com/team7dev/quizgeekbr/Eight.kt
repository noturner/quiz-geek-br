package com.team7dev.quizgeekbr
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView


class Eight : BaseActivity() {

    private lateinit var buttonAnswer1: Button
    private lateinit var buttonAnswer2: Button
    private lateinit var buttonAnswer3: Button
    private lateinit var buttonAnswer4: Button
    private lateinit var mediaPlayerWin: MediaPlayer
    private lateinit var mediaPlayerWrong: MediaPlayer
    private lateinit var mediaPlayerCelebration: MediaPlayer
    private lateinit var mediaPlayerFail: MediaPlayer
    private val handler = Handler(Looper.getMainLooper())

    // Lista de perguntas e respostas
    private val questions = listOf(
        Question("Qual é o nome do protagonista de Attack on Titan?", "Eren Yeager", listOf("Eren Yeager", "Mikasa Ackerman", "Armin Arlert", "Levi Ackerman")),
        Question("Qual é o nome da unidade militar que luta diretamente contra os Titãs?", "Corpo de Exploração", listOf("Corpo de Exploração", "Guarnição", "Polícia Militar", "Unidade de Recrutas")),
        Question("Quem Eren adotou como irmão(ã) adotivo(a) ?", "Mikasa Ackerman", listOf("Mikasa Ackerman", "Historia Reiss", "Zeke Yeager", "Armin Arlert")),
        Question("Qual é o poder especial de Eren Yeager?", "Titã de Ataque", listOf("Titã de Ataque", "Titã Colossal", "Titã Blindado", "Titã Fêmea")),
        Question("Quem é o líder do Corpo de Exploração no início da série?", "Erwin Smith", listOf("Erwin Smith", "Levi Ackerman", "Hange Zoë", "Mike Zacharias")),
        Question("Qual é o nome verdadeiro da rainha Historia?", "Historia Reiss", listOf("Historia Reiss", "Ymir", "Frieda Reiss", "Mikasa Ackerman")),
        Question("Qual é o nome do Titã que pode endurecer sua pele?", "Titã Blindado", listOf("Titã Blindado", "Titã Colossal", "Titã Fêmea", "Titã Bestial")),
        Question("Quem é o portador do Titã Colossal?", "Bertholdt Hoover", listOf("Bertholdt Hoover", "Reiner Braun", "Zeke Yeager", "Annie Leonhart")),
        Question("Qual é a habilidade especial do Titã Bestial?", "Lançar objetos com precisão e falar", listOf("Lançar objetos com precisão e falar", "Endurecimento", "Regeneração rápida", "Transformar outros em Titãs")),
        Question("Qual é o nome da cidade principal onde a história começa?", "Shiganshina", listOf("Shiganshina", "Trost", "Stohess", "Mitras")),
        Question("Quem é a Titã Fêmea?", "Annie Leonhart", listOf("Annie Leonhart", "Historia Reiss", "Sasha Blouse", "Mikasa Ackerman")),
        Question("Qual é o nome da técnica de combate que usa ganchos e manobras aéreas?", "Equipamento de Manobra Tridimensional", listOf("Equipamento de Manobra Tridimensional", "Equipamento de Voo", "Equipamento de Combate", "Equipamento de Artilharia")),
        Question("Quem é o melhor amigo de Eren e também se torna um estrategista?", "Armin Arlert", listOf("Armin Arlert", "Jean Kirstein", "Connie Springer", "Sasha Blouse")),
        Question("Qual é o nome do Titã que Reiner Braun se transforma?", "Titã Blindado", listOf("Titã Blindado", "Titã Colossal", "Titã de Ataque", "Titã Fêmea")),
        Question("Qual é o objetivo principal do Corpo de Exploração?", "Explorar e recuperar territórios dos Titãs", listOf("Explorar e recuperar territórios dos Titãs", "Proteger a capital", "Treinar novos recrutas", "Desenvolver novas armas")),
        Question("Quem se sacrifica para salvar Eren e Armin da captura pelo Titã Colossal?", "Hannes", listOf("Hannes", "Erwin Smith", "Levi Ackerman", "Sasha Blouse")),
        Question("Qual é o nome do Titã que tem o poder de controlar outros Titãs?", "Titã Fundador", listOf("Titã Fundador", "Titã Bestial", "Titã Colossal", "Titã de Ataque")),
        Question("Qual é o nome do grupo que se opõe ao governo de Paradis e apoia os Titãs?", "Aldeões de Marley", listOf("Aldeões de Marley", "Exploradores de Marley", "Guerreiros de Marley", "Soldados de Marley")),
        Question("Quem se torna o novo líder do Corpo de Exploração após a morte de Erwin?", "Hange Zoë", listOf("Hange Zoë", "Levi Ackerman", "Mikasa Ackerman", "Jean Kirstein")),
        Question("Qual é a relação de Levi Ackerman com Mikasa?", "São parentes distantes", listOf("São parentes distantes", "São irmãos", "São primos", "São amigos de infância")),
        Question("Quem é o pai de Eren Yeager e também um Titã?", "Grisha Yeager", listOf("Grisha Yeager", "Zeke Yeager", "Erwin Smith", "Reiner Braun")),
        Question("Qual é o nome da organização secreta que busca a verdade sobre os Titãs?", "A Ordem das Muralhas", listOf("A Ordem das Muralhas", "A Seita dos Titãs", "A Liga da Verdade", "Os Exploradores Secretos")),
        Question("Quem é o comandante da Polícia Militar?", "Nile Dok", listOf("Nile Dok", "Erwin Smith", "Pixis", "Mike Zacharias")),
        Question("Qual é a identidade verdadeira do Titã Bestial?", "Zeke Yeager", listOf("Zeke Yeager", "Bertholdt Hoover", "Reiner Braun", "Annie Leonhart")),
        Question("Qual é o nome da muralha mais externa?", "Muralha Maria", listOf("Muralha Maria", "Muralha Rose", "Muralha Sina", "Muralha Reiss")),
        Question("Quem era o líder dos Guerreiros de Marley?", "Zeke Yeager", listOf("Zeke Yeager", "Reiner Braun", "Bertholdt Hoover", "Annie Leonhart")),
        Question("Qual é o nome da Titã que luta ao lado dos Guerreiros de Marley e tem uma forma quadrúpede?", "Titã Carruagem", listOf("Titã Carruagem", "Titã Fêmea", "Titã Colossal", "Titã Blindado")),
        Question("Quem herda o Titã Colossal após a morte de Bertholdt?", "Armin Arlert", listOf("Armin Arlert", "Eren Yeager", "Jean Kirstein", "Mikasa Ackerman")),
        Question("Qual é a habilidade única do Titã Martelo de Guerra?", "Criar armas a partir de seu corpo", listOf("Criar armas a partir de seu corpo", "Endurecimento total", "Controle de outros Titãs", "Regeneração rápida")),
        Question("Quem é a irmã de Zeke Yeager e também herda o Titã Bestial?", "Eren Yeager", listOf("Eren Yeager", "Historia Reiss", "Mikasa Ackerman", "Annie Leonhart"))


    ).shuffled()

    /// Índice da pergunta atual
    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eight)

        buttonAnswer1 = findViewById(R.id.buttonAnswer1)
        buttonAnswer2 = findViewById(R.id.buttonAnswer2)
        buttonAnswer3 = findViewById(R.id.buttonAnswer3)
        buttonAnswer4 = findViewById(R.id.buttonAnswer4)

        mediaPlayerWin = MediaPlayer.create(this, R.raw.sound_win)
        mediaPlayerWrong = MediaPlayer.create(this, R.raw.sound_wrong)
        mediaPlayerCelebration = MediaPlayer.create(this, R.raw.sound_celebration)
        mediaPlayerFail = MediaPlayer.create(this, R.raw.sound_fail)

        setQuestionsAndAnswers()

        buttonAnswer1.setOnClickListener { checkAnswer(buttonAnswer1) }
        buttonAnswer2.setOnClickListener { checkAnswer(buttonAnswer2) }
        buttonAnswer3.setOnClickListener { checkAnswer(buttonAnswer3) }
        buttonAnswer4.setOnClickListener { checkAnswer(buttonAnswer4) }
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestionsAndAnswers() {
        if (currentQuestionIndex < questions.size) {
            val currentQuestion = questions[currentQuestionIndex]

            // Atualiza o texto da pergunta
            val textViewQuestion = findViewById<TextView>(R.id.textViewQuestion)
            textViewQuestion.text = currentQuestion.questionText

            // Atualiza o contador de perguntas
            val textViewQuestionCounter = findViewById<TextView>(R.id.textViewQuestionCounter)
            textViewQuestionCounter.text = "Pergunta ${currentQuestionIndex + 1}/${questions.size}"


            // Resetando cores e habilitando botões
            resetButtonColors()
            enableAllButtons()

            // Embaralhando as respostas
            val shuffledAnswers = currentQuestion.answers.shuffled()

            // Definindo as respostas para os botões
            buttonAnswer1.text = shuffledAnswers[0]
            buttonAnswer2.text = shuffledAnswers[1]
            buttonAnswer3.text = shuffledAnswers[2]
            buttonAnswer4.text = shuffledAnswers[3]
        }
    }

    private fun checkAnswer(clickedButton: Button) {
        val currentQuestion = questions[currentQuestionIndex]
        val correctAnswer = currentQuestion.correctAnswer

        disableAllButtons()

        if (clickedButton.text == correctAnswer) {
            // Resposta correta
            clickedButton.setBackgroundColor(Color.GREEN)
            playSoundWin()
            correctAnswersCount++
        } else {
            // Resposta errada
            clickedButton.setBackgroundColor(Color.RED)
            showCorrectAnswer()
            playSoundWrong()
        }

        // Avançar para a próxima pergunta após 3 segundos
        handler.postDelayed({
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                setQuestionsAndAnswers()
            } else {
                finishQuiz()
            }
        }, 3000)
    }

    private fun showCorrectAnswer() {
        val currentQuestion = questions[currentQuestionIndex]
        val correctAnswer = currentQuestion.correctAnswer

        // Mostra a resposta correta
        when (correctAnswer) {
            buttonAnswer1.text -> buttonAnswer1.setBackgroundColor(Color.GREEN)
            buttonAnswer2.text -> buttonAnswer2.setBackgroundColor(Color.GREEN)
            buttonAnswer3.text -> buttonAnswer3.setBackgroundColor(Color.GREEN)
            buttonAnswer4.text -> buttonAnswer4.setBackgroundColor(Color.GREEN)
        }
    }

    private fun resetButtonColors() {
        val defaultColor = Color.parseColor("#FFFFFF") // Cor padrão dos botões
        buttonAnswer1.setBackgroundColor(defaultColor)
        buttonAnswer2.setBackgroundColor(defaultColor)
        buttonAnswer3.setBackgroundColor(defaultColor)
        buttonAnswer4.setBackgroundColor(defaultColor)
    }

    private fun enableAllButtons() {
        buttonAnswer1.isEnabled = true
        buttonAnswer2.isEnabled = true
        buttonAnswer3.isEnabled = true
        buttonAnswer4.isEnabled = true
    }

    private fun disableAllButtons() {
        buttonAnswer1.isEnabled = false
        buttonAnswer2.isEnabled = false
        buttonAnswer3.isEnabled = false
        buttonAnswer4.isEnabled = false
    }

    private fun playSoundWin() {
        mediaPlayerWin.start()
    }

    private fun playSoundWrong() {
        mediaPlayerWrong.start()
    }

    private fun playSoundCelebration() {
        mediaPlayerCelebration.start()
    }

    private fun playSoundFail() {
        mediaPlayerFail.start()
    }

    @SuppressLint("SetTextI18n")
    private fun finishQuiz() {
        val score = (correctAnswersCount.toFloat() / questions.size.toFloat()) * 100
        val intent = Intent(this, Two::class.java)

        // Definir música com base na pontuação
        if (score >= 90) {
            playSoundCelebration()
        } else {
            playSoundFail()
        }

        // Ocultar perguntas e respostas
        val textViewQuestion = findViewById<TextView>(R.id.textViewQuestion)
        textViewQuestion.visibility = View.INVISIBLE

        buttonAnswer1.visibility = View.INVISIBLE
        buttonAnswer2.visibility = View.INVISIBLE
        buttonAnswer3.visibility = View.INVISIBLE
        buttonAnswer4.visibility = View.INVISIBLE

        // Ocultar letras A, B, C e D
        val textViewA = findViewById<TextView>(R.id.textViewAnswerA)
        val textViewB = findViewById<TextView>(R.id.textViewAnswerB)
        val textViewC = findViewById<TextView>(R.id.textViewAnswerC)
        val textViewD = findViewById<TextView>(R.id.textViewAnswerD)

        textViewA.visibility = View.INVISIBLE
        textViewB.visibility = View.INVISIBLE
        textViewC.visibility = View.INVISIBLE
        textViewD.visibility = View.INVISIBLE

        // Define a mensagem com base na pontuação
        val message = when {
            score >= 90 -> "Na vida, você é o Titã Colossal e esse Quiz a muralha Maria !"
            score >= 40 && score < 90 -> "Os titãs estão pedindo para você treinar mais antes de enfrentá-los."
            else -> " Foi uma exibição digna de um Titã Colossal... de erros"
        }

        val textViewMessage = findViewById<TextView>(R.id.textViewMessage)
        textViewMessage.text = message
        textViewMessage.visibility = View.VISIBLE
        // Mostrar quantidade de respostas corretas

        val textViewScore = findViewById<TextView>(R.id.textViewScore)
        textViewScore.text = "Você acertou $correctAnswersCount de ${questions.size}!"
        textViewScore.visibility = View.VISIBLE

        handler.postDelayed({
            startActivity(intent)
            finish()
        }, 8000) // Aguarda 8 segundos antes de voltar para a atividade Two
    }

    fun home(view: View) {
        val intent = Intent(this, Two::class.java)
        startActivity(intent)
        finish()
    }

    // Classe interna para representar uma pergunta
    private data class Question(val questionText: String, val correctAnswer: String, val answers: List<String>)

    fun checkAnswer() {
        // Método extra, pode ser removido se não for necessário
    }
}
