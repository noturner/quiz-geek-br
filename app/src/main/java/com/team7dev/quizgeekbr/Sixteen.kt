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


class Sixteen : BaseActivity() {

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
        Question("Qual é o nome do personagem principal que cria o plano de fuga em Prison Break?", "Michael Scofield", listOf("Michael Scofield", "Lincoln Burrows", "Theodore Bagwell", "Fernando Sucre")),
        Question("Qual é o nome do irmão de Michael Scofield que é preso injustamente?", "Lincoln Burrows", listOf("Lincoln Burrows", "Michael Scofield", "Theodore Bagwell", "Fernando Sucre")),
        Question("Qual é a profissão de Michael Scofield antes de ser preso?", "Engenheiro Civil", listOf("Engenheiro Civil", "Advogado", "Médico", "Policial")),
        Question("Qual é o nome da prisão de onde Michael Scofield planeja escapar na primeira temporada?", "Fox River", listOf("Fox River", "Sona", "St. Louis", "Panama City")),
        Question("Qual é o nome da médica que Michael Scofield se apaixona?", "Sara Tancredi", listOf("Sara Tancredi", "Veronica Donovan", "Kellerman", "Gretchen Morgan")),
        Question("Qual é o apelido de Theodore Bagwell na série?", "T-Bag", listOf("T-Bag", "C-Note", "Linc", "Whistler")),
        Question("Qual é o nome do personagem que é um ex-soldado e membro da gangue de Michael?", "Benjamin Miles 'C-Note' Franklin", listOf("Benjamin Miles 'C-Note' Franklin", "Fernando Sucre", "Theodore Bagwell", "Alexander Mahone")),
        Question("Quem é o advogado de Lincoln Burrows no início da série?", "Veronica Donovan", listOf("Veronica Donovan", "Sara Tancredi", "Gretchen Morgan", "Sofia Lugo")),
        Question("Qual é o nome do agente do FBI que persegue Michael e Lincoln?", "Alexander Mahone", listOf("Alexander Mahone", "Paul Kellerman", "Donald Self", "Brad Bellick")),
        Question("Qual é o nome da organização secreta que está por trás da conspiração contra Lincoln?", "The Company", listOf("The Company", "The Brotherhood", "The Order", "The Cartel")),
        Question("Qual é o nome do filho de Lincoln Burrows?", "L.J. Burrows", listOf("L.J. Burrows", "Michael Jr.", "John Abruzzi", "Fernando Sucre")),
        Question("Qual é o nome do diretor da prisão Fox River?", "Henry Pope", listOf("Henry Pope", "Brad Bellick", "Paul Kellerman", "Alexander Mahone")),
        Question("Qual é o nome do personagem que é o companheiro de cela de Michael Scofield em Fox River?", "Fernando Sucre", listOf("Fernando Sucre", "John Abruzzi", "Theodore Bagwell", "Benjamin Miles 'C-Note' Franklin")),
        Question("Qual é o nome da gangue de prisioneiros que lidera o comércio de drogas em Fox River?", "Os Árabes", listOf("Os Árabes", "Os Latinos", "Os Irlandeses", "Os Negros")),
        Question("Qual é o nome da prisão no Panamá onde Michael é preso na terceira temporada?", "Sona", listOf("Sona", "Fox River", "St. Louis", "Grafton")),
        Question("Qual é o nome da mãe de Michael Scofield e Lincoln Burrows?", "Christina Scofield", listOf("Christina Scofield", "Sara Tancredi", "Veronica Donovan", "Gretchen Morgan")),
        Question("Qual é o apelido de Fernando Sucre dado pelos outros prisioneiros?", "Sucre", listOf("Sucre", "Scofield", "Linc", "T-Bag")),
        Question("Quem é o personagem que ajuda Michael a fugir em troca de vingança contra um chefe da máfia?", "John Abruzzi", listOf("John Abruzzi", "Fernando Sucre", "Brad Bellick", "Benjamin Miles 'C-Note' Franklin")),
        Question("Qual é o nome do personagem que é um ex-correio e membro da gangue de Michael?", "David 'Tweener' Apolskis", listOf("David 'Tweener' Apolskis", "Paul Kellerman", "Alexander Mahone", "John Abruzzi")),
        Question("Qual é o nome do detetive que ajuda Veronica Donovan a investigar a conspiração contra Lincoln?", "Nick Savrinn", listOf("Nick Savrinn", "Paul Kellerman", "Alexander Mahone", "Donald Self")),
        Question("Qual é o nome da filha de Sara Tancredi e Michael Scofield?", "Mike Jr.", listOf("Mike Jr.", "L.J. Burrows", "Sophia Burrows", "Lisa Scofield")),
        Question("Qual é o nome do personagem que é o agente de segurança que se torna aliado de Michael e Lincoln?", "Paul Kellerman", listOf("Paul Kellerman", "Alexander Mahone", "Donald Self", "Henry Pope")),
        Question("Qual é o nome do barco usado por Michael e Lincoln para fugir para o Panamá?", "Christina Rose", listOf("Christina Rose", "Veronica", "Sara", "Sona")),
        Question("Quem é o personagem que é um ex-policial e se torna um mercenário perseguindo Michael?", "Brad Bellick", listOf("Brad Bellick", "Paul Kellerman", "Alexander Mahone", "John Abruzzi")),
        Question("Qual é o nome da operação secreta da CIA que visa destruir The Company?", "Operação Genebra", listOf("Operação Genebra", "Operação Sona", "Operação Fox River", "Operação Panamá")),
        Question("Qual é o nome do general que é o líder de The Company?", "Jonathan Krantz", listOf("Jonathan Krantz", "Paul Kellerman", "Alexander Mahone", "Brad Bellick")),
        Question("Qual é o nome do agente da Segurança Interna que trabalha com Michael e Lincoln na quarta temporada?", "Donald Self", listOf("Donald Self", "Paul Kellerman", "Alexander Mahone", "Brad Bellick")),
        Question("Qual é o nome do personagem que é uma hacker habilidosa e ajuda Michael e Lincoln na quarta temporada?", "Roland Glenn", listOf("Roland Glenn", "Sara Tancredi", "Veronica Donovan", "Gretchen Morgan")),
        Question("Qual é o nome do prisioneiro que é o líder dos prisioneiros em Sona?", "Lechero", listOf("Lechero", "T-Bag", "John Abruzzi", "Fernando Sucre")),
        Question("Qual é o nome da esposa de Fernando Sucre?", "Maricruz Delgado", listOf("Maricruz Delgado", "Sara Tancredi", "Veronica Donovan", "Sophia Lugo"))

    ).shuffled()

    /// Índice da pergunta atual
    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sixteen)

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
            score >= 90 -> "Parabéns, você conseguiu escapar de Prision Break!!!"
            score >= 40 && score < 90 -> "Seu desempenho foi confuso igual Sucre misturando inglês com espanhol!"
            else -> "Na vida você é T Bag e o Quiz é o Vendedor de Biblia!"
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
