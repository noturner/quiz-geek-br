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


class Twelve : BaseActivity() {

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
        Question("Qual é o nome da cidade onde se passa Stranger Things?", "Hawkins", listOf("Hawkins", "Springfield", "Sunnydale", "Riverdale")),
        Question("Qual é o nome do personagem desaparecido na primeira temporada?", "Will Byers", listOf("Will Byers", "Mike Wheeler", "Lucas Sinclair", "Dustin Henderson")),
        Question("Qual é o nome da garota com poderes telecinéticos?", "Eleven", listOf("Eleven", "Max", "Nancy", "Robin")),
        Question("Qual é o nome da mãe de Will Byers?", "Joyce Byers", listOf("Joyce Byers", "Karen Wheeler", "Claudia Henderson", "Susan Hargrove")),
        Question("Qual é o nome do policial chefe de Hawkins?", "Jim Hopper", listOf("Jim Hopper", "Bob Newby", "Dr. Owens", "Sam Owens")),
        Question("Qual é o nome do mundo alternativo em Stranger Things?", "Mundo Invertido", listOf("Mundo Invertido", "Realidade invertidade", "Terra invertidade", "Zona invertida")),
        Question("Qual é o nome do monstro que ataca Hawkins na primeira temporada?", "Demogorgon", listOf("Demogorgon", "Mind Flayer", "Vecna", "Dart")),
        Question("Qual é o nome da irmã de Mike Wheeler?", "Nancy Wheeler", listOf("Nancy Wheeler", "Karen Wheeler", "Max Mayfield", "Joyce Byers")),
        Question("Quem trabalha no Scoops Ahoy na terceira temporada?", "Steve Harrington", listOf("Steve Harrington", "Jonathan Byers", "Billy Hargrove", "Jim Hopper")),
        Question("Qual é o nome do namorado de Nancy na primeira temporada?", "Steve Harrington", listOf("Steve Harrington", "Jonathan Byers", "Billy Hargrove", "Mike Wheeler")),
        Question("Quem se muda para Hawkins na segunda temporada e se torna amiga de Eleven?", "Max Mayfield", listOf("Max Mayfield", "Robin Buckley", "Erica Sinclair", "Nancy Wheeler")),
        Question("Quem é o cientista responsável pelo Laboratório Hawkins?", "Dr. Martin Brenner", listOf("Dr. Martin Brenner", "Dr. Sam Owens", "Dr. Alexei", "Dr. Clarke")),
        Question("Qual é o nome do amigo de Dustin que ele conhece no acampamento de ciências?", "Suzie", listOf("Suzie", "Max", "Nancy", "Robin")),
        Question("Qual é o nome completo de Eleven?", "Jane Hopper", listOf("Jane Hopper", "Jane Byers", "Jane Wheeler", "Jane Sinclair")),
        Question("Qual é o nome do irmão de Max?", "Billy Hargrove", listOf("Billy Hargrove", "Steve Harrington", "Jonathan Byers", "Lucas Sinclair")),
        Question("Qual é o nome da canção que toca quando Eleven usa seus poderes?", "Should I Stay or Should I Go", listOf("Should I Stay or Should I Go", "Africa", "Heroes", "Time After Time")),
        Question("Qual é o nome do jogo de RPG que os meninos jogam?", "Dungeons & Dragons", listOf("Dungeons & Dragons", "Banco Imobiliário", "Warhammer", "Magic: The Gathering")),
        Question("Qual é o nome do centro comercial que se torna um ponto central na terceira temporada?", "Starcourt Mall", listOf("Starcourt Mall", "Hawkins Mall", "Sunshine Mall", "Riverdale Mall")),
        Question("Qual é o nome do grupo russo que constrói uma base secreta em Hawkins?", "Laboratório Nacional de Hawkins", listOf("Braço Vermelho", "Laboratório Nacional de Hawkins", "Sindicato Soviético", "Laboratório de Riverdale")),
        Question("Qual é o nome da irmã de Lucas?", "Erica Sinclair", listOf("Erica Sinclair", "Nancy Wheeler", "Robin Buckley", "Suzie")),
        Question("Qual é o apelido de Eleven dado pelos meninos?", "El", listOf("El", "Ellie", "Jane", "Max")),
        Question("Qual é o nome do cachorro-demônio encontrado por Dustin?", "Dart", listOf("Dart", "Demodog", "Demogorgon", "Mind Flayer")),
        Question("Quem é o novo chefe de polícia após Jim Hopper desaparecer?", "Chief Callahan", listOf("Chief Callahan", "Chief Powell", "Chief Clarke", "Chief Owens")),
        Question("Qual é o nome da amiga desaparecida de Nancy?", "Barb", listOf("Barb", "Robin", "Max", "Erica")),
        Question("Qual é o nome do evento escolar onde Will desaparece?", "A Noite dos Jogos", listOf("A Noite dos Jogos", "O Baile de Inverno", "A Feira de Ciências", "O Festival de Verão")),
        Question("Quem é o namorado de Joyce Byers na segunda temporada?", "Bob Newby", listOf("Bob Newby", "Jim Hopper", "Dr. Sam Owens", "Murray Bauman")),
        Question("Qual é o nome do programa governamental que realiza experimentos em Eleven?", "Projeto MKUltra", listOf("Projeto MKUltra", "Projeto Pegasus", "Projeto Phoenix", "Projeto Nova")),
        Question("Quem resgata Eleven do laboratório no final da primeira temporada?", "Jim Hopper", listOf("Jim Hopper", "Mike Wheeler", "Lucas Sinclair", "Dustin Henderson")),
        Question("Qual é o nome da mãe adotiva de Eleven?", "Terry Ives", listOf("Terry Ives", "Joyce Byers", "Karen Wheeler", "Claudia Henderson")),
        Question("Qual é o nome do prefeito corrupto de Hawkins na terceira temporada?", "Larry Kline", listOf("Larry Kline", "Tom Holloway", "Bruce Lowe", "Mayor Klein"))

    ).shuffled()

    /// Índice da pergunta atual
    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twelve)

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
            score >= 90 -> "Seu desempenho é digno de uma sessão de Dungeons & Dragons com os amigos de Hawkins!!!"
            score >= 40 && score < 90 -> "Seu desempenho foi mais confuso do que os flashbacks da Eleven."
            else -> "Seu conhecimento de Stranger Things está mais 'apagado' do que as luzes piscantes de Joyce Byers."
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
