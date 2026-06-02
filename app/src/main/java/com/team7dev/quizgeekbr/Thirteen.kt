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


class Thirteen : BaseActivity() {

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
        Question("Qual é o nome verdadeiro do Mandaloriano?", "Din Djarin", listOf("Din Djarin", "Boba Fett", "Jango Fett", "Pre Vizsla")),
        Question("Qual é o nome da criatura conhecida como Baby Yoda?", "Grogu", listOf("Grogu", "Yaddle", "Yoda", "Gungi")),
        Question("Qual é o nome do planeta onde o Mandaloriano encontra Grogu?", "Arvala-7", listOf("Arvala-7", "Tatooine", "Navarro", "Sorgan")),
        Question("Quem é a líder dos Mandalorianos que forja a armadura de Din Djarin?", "A Armeira", listOf("A Armeira", "Bo-Katan", "Fennec Shand", "Cara Dune")),
        Question("Qual é o nome do personagem interpretado por Carl Weathers?", "Greef Karga", listOf("Greef Karga", "Moff Gideon", "Kuiil", "Cobb Vanth")),
        Question("Quem é o caçador de recompensas que ajuda o Mandaloriano em Tatooine?", "Cobb Vanth", listOf("Cobb Vanth", "IG-11", "Cara Dune", "Boba Fett")),
        Question("Qual é o nome do droide que se sacrifica para salvar Grogu e o Mandaloriano?", "IG-11", listOf("IG-11", "R2-D2", "C-3PO", "K-2SO")),
        Question("Quem é o vilão principal da série, interpretado por Giancarlo Esposito?", "Moff Gideon", listOf("Moff Gideon", "Grand Moff Tarkin", "Darth Vader", "Thrawn")),
        Question("Qual é a arma que Moff Gideon usa e é símbolo de liderança Mandaloriana?", "Darksaber", listOf("Darksaber", "Lightsaber", "Blaster", "Beskar Spear")),
        Question("Qual é o nome da ex-soldado Rebelde que se torna aliada do Mandaloriano?", "Cara Dune", listOf("Cara Dune", "Fennec Shand", "Bo-Katan", "Sabine Wren")),
        Question("Qual é o nome do personagem que diz a frase 'I have spoken'?", "Kuiil", listOf("Kuiil", "Greef Karga", "Moff Gideon", "Din Djarin")),
        Question("Qual é o nome do planeta onde Din Djarin encontra Ahsoka Tano?", "Corvus", listOf("Corvus", "Tython", "Nevarro", "Sorgan")),
        Question("Quem é o mandaloriano que busca recuperar o Darksaber de Moff Gideon?", "Bo-Katan", listOf("Bo-Katan", "Sabine Wren", "Fennec Shand", "Boba Fett")),
        Question("Qual é o nome do droide interpretado por Taika Waititi?", "IG-11", listOf("IG-11", "HK-47", "K-2SO", "Chopper")),
        Question("Qual é o nome da raça do Mandaloriano?", "Humano", listOf("Humano", "Twi'lek", "Rodiano", "Zabrak")),
        Question("Qual é o nome do planeta natal de Grogu?", "Desconhecido", listOf("Desconhecido", "Dagobah", "Kashyyyk", "Muunilinst")),
        Question("Qual é o nome do caçador de recompensas que retorna em The Mandalorian?", "Boba Fett", listOf("Boba Fett", "Cad Bane", "Bossk", "Dengar")),
        Question("Quem ajuda o Mandaloriano a treinar Grogu na segunda temporada?", "Ahsoka Tano", listOf("Ahsoka Tano", "Luke Skywalker", "Obi-Wan Kenobi", "Ezra Bridger")),
        Question("Qual é o nome do personagem que usa um jetpack e tem uma armadura laranja?", "Cobb Vanth", listOf("Cobb Vanth", "Cara Dune", "Din Djarin", "Greef Karga")),
        Question("Qual é o nome do planeta deserto onde o Mandaloriano encontra Cobb Vanth?", "Tatooine", listOf("Tatooine", "Arvala-7", "Navarro", "Sorgan")),
        Question("Quem é o personagem que se junta ao Mandaloriano e tem um rifle disruptor?", "IG-11", listOf("IG-11", "Kuiil", "Cara Dune", "Bo-Katan")),
        Question("Qual é o nome do material usado para fazer a armadura do Mandaloriano?", "Beskar", listOf("Beskar", "Durasteel", "Phrik", "Cortosis")),
        Question("Quem é o governador corrupto de Nevarro antes de ser derrubado?", "Greef Karga", listOf("Greef Karga", "Moff Gideon", "Kuiil", "Cobb Vanth")),
        Question("Qual é o nome da criatura gigante que o Mandaloriano enfrenta em Arvala-7?", "Mudhorn", listOf("Mudhorn", "Krayt Dragon", "Sarlacc", "Rancor")),
        Question("Qual é o nome da cidade onde o Mandaloriano trabalha como caçador de recompensas?", "Nevarro", listOf("Nevarro", "Tatooine", "Coruscant", "Mos Eisley")),
        Question("Quem é o líder do clã de Mandalorianos que ajuda Din Djarin?", "A Armeira", listOf("A Armeira", "Bo-Katan", "Pre Vizsla", "Sabine Wren")),
        Question("Qual é o nome do planeta onde o Mandaloriano encontra os Mandalorianos liderados por Bo-Katan?", "Trask", listOf("Trask", "Corvus", "Nevarro", "Sorgan")),
        Question("Qual é o nome do caçador de recompensas que usa uma armadura azul e preta?", "Bo-Katan", listOf("Bo-Katan", "Cobb Vanth", "Fennec Shand", "Greef Karga")),
        Question("Qual é o nome da nave do Mandaloriano?", "Razor Crest", listOf("Razor Crest", "Slave I", "Millennium Falcon", "Ghost")),
        Question("Quem é o personagem que oferece caçadas ao Mandaloriano em Nevarro?", "Greef Karga", listOf("Greef Karga", "Kuiil", "Moff Gideon", "Cara Dune"))

    ).shuffled()

    /// Índice da pergunta atual
    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thirteen)

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
            score >= 90 -> "Você é digno de possuir o Sabre Negro! É como deve ser."
            score >= 40 && score < 90 -> "Este é o caminho.. caminho da perdição! Volte para Mandalore e tente novamente."
            else -> "Suas respostas foram tão precisas quanto um Stormtrooper atirando com um Blaster."
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
