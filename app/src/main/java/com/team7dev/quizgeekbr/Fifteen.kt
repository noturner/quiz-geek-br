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


class Fifteen : BaseActivity() {

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
        Question("Qual é o nome completo do personagem principal em Breaking Bad?", "Walter White", listOf("Walter White", "Jesse Pinkman", "Hank Schrader", "Gus Fring")),
        Question("Qual é a profissão de Walter White antes de se tornar um fabricante de metanfetamina?", "Professor de Química", listOf("Professor de Química", "Policial", "Médico", "Advogado")),
        Question("Qual é o nome do parceiro de Walter White na fabricação de metanfetamina?", "Jesse Pinkman", listOf("Jesse Pinkman", "Hank Schrader", "Saul Goodman", "Gus Fring")),
        Question("Qual é o nome da esposa de Walter White?", "Skyler White", listOf("Skyler White", "Marie Schrader", "Jane Margolis", "Lydia Rodarte-Quayle")),
        Question("Qual é o pseudônimo usado por Walter White no mundo do crime?", "Heisenberg", listOf("Heisenberg", "Walt", "Blue", "Reisenberg")),
        Question("Qual é o nome do cunhado de Walter White, que trabalha na DEA?", "Hank Schrader", listOf("Hank Schrader", "Gus Fring", "Saul Goodman", "Mike Ehrmantraut")),
        Question("Qual é a cor característica da metanfetamina produzida por Walter e Jesse?", "Azul", listOf("Azul", "Rosa", "Branca", "Verde")),
        Question("Qual é o nome do advogado de Walter White e Jesse Pinkman?", "Saul Goodman", listOf("Saul Goodman", "Mike Ehrmantraut", "James Morgan McGill", "Chuck McGill")),
        Question("Qual é o nome do restaurante de fast food usado como fachada por Gus Fring?", "Los Pollos Hermanos", listOf("Los Pollos Hermanos", "Los Tacos Hermanos", "Los Pollos Amigos", "El Pollo Loco")),
        Question("Quem é o fundador da Gray Matter Technologies junto com Walter White?", "Elliott Schwartz", listOf("Elliott Schwartz", "Gretchen Schwartz", "Gale Boetticher", "Lydia Rodarte-Quayle")),
        Question("Qual é o nome do personagem interpretado por Aaron Paul?", "Jesse Pinkman", listOf("Jesse Pinkman", "Walter White", "Gus Fring", "Mike Ehrmantraut")),
        Question("Qual é o nome da filha de Walter e Skyler White?", "Holly White", listOf("Holly White", "Marie White", "Jane White", "Lydia White")),
        Question("Qual é o nome do filho de Walter White?", "Walter White Jr.", listOf("Walter White Jr.", "Hank White", "Mike White", "Valter White")),
        Question("Qual é a marca do carro que Walter White dirige na primeira temporada?", "Pontiac Aztek", listOf("Pontiac Aztek", "Toyota Camry", "Ford Taurus", "Chevrolet Malibu")),
        Question("Quem é o chefe do cartel mexicano que tem uma rivalidade com Gus Fring?", "Hector Salamanca", listOf("Hector Salamanca", "Tuco Salamanca", "Lalo Salamanca", "Marco Salamanca")),
        Question("Qual é o nome do assistente de laboratório de Walter White contratado por Gus Fring?", "Gale Boetticher", listOf("Gale Boetticher", "Todd Alquist", "Victor", "Mike Ehrmantraut")),
        Question("Quem é a namorada de Jesse Pinkman que morre de overdose?", "Jane Margolis", listOf("Jane Margolis", "Andrea Cantillo", "Lydia Rodarte-Quayle", "Skyler White")),
        Question("Qual é o nome da empresa de lavagem de carros comprada por Walter e Skyler?", "A1A Car Wash", listOf("A1A Car Wash", "Sparkle Car Wash", "Deluxe Car Wash", "Sunshine Car Wash")),
        Question("Qual é o nome do filho de Andrea Cantillo, que se torna próximo de Jesse?", "Brock Cantillo", listOf("Brock Cantillo", "Holly White", "Jake Pinkman", "Walter White Jr.")),
        Question("Qual é o nome do personagem interpretado por Giancarlo Esposito?", "Gus Fring", listOf("Gus Fring", "Hector Salamanca", "Mike Ehrmantraut", "Saul Goodman")),
        Question("Qual é a ocupação de Mike Ehrmantraut antes de trabalhar para Gus Fring?", "Policial", listOf("Policial", "Advogado", "Médico", "Professor")),
        Question("Qual é o nome do irmão de Marie Schrader?", "Skyler White", listOf("Skyler White", "Walter White", "Ted Beneke", "Jesse Pinkman")),
        Question("Quem é o personagem que diz a frase 'Say my name'?", "Walter White", listOf("Walter White", "Jesse Pinkman", "Gus Fring", "Saul Goodman")),
        Question("Qual é o nome da personagem que trabalha como contato de negócios de Lydia Rodarte-Quayle?", "Todd Alquist", listOf("Todd Alquist", "Mike Ehrmantraut", "Saul Goodman", "Hank Schrader")),
        Question("Qual é o nome do cartel mexicano liderado por Don Eladio?", "Cartel Juarez", listOf("Cartel Juarez", "Cartel de Sinaloa", "Cartel de Medellin", "Cartel Tijuana")),
        Question("Qual é o nome da esposa de Hank Schrader?", "Marie Schrader", listOf("Marie Schrader", "Skyler White", "Lydia Rodarte-Quayle", "Jane Margolis")),
        Question("Qual é a cor do chapéu icônico usado por Walter White?", "Preto", listOf("Preto", "Branco", "Marrom", "Cinza")),
        Question("Qual é a substância usada por Walter White para envenenar Lydia Rodarte-Quayle?", "Ricin", listOf("Ricin", "Cianeto", "Estricnina", "Veneno de cobra")),
        Question("Quem é o personagem que se torna líder do cartel após a morte de Gus Fring?", "Lydia Rodarte-Quayle", listOf("Lydia Rodarte-Quayle", "Todd Alquist", "Jesse Pinkman", "Saul Goodman")),
        Question("O laboratório subterrâneo usado por Walter White fica embaixo de um(a)?", "Lavanderia", listOf("Lanchonete", "Lavarápido", "Lavanderia", "Informática"))

    ).shuffled()

    /// Índice da pergunta atual
    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifteen)

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
            score >= 90 -> "Você não está em Perigo, VOCÊ É O PERIGO !!!"
            score >= 40 && score < 90 -> "Parabéns, você daria um bom figurante no Los Polos Hermanos."
            else -> "Melhor Ligar Para o Saul, Você não respondeu perguntas.. cometeu um crime e alguém precisa te defender!"
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
