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


class Eighteen: BaseActivity() {

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
        Question("Qual é o nome do líder dos The Boys?", "Billy Butcher", listOf("Billy Butcher", "Hughie Campbell", "Mother's Milk", "Frenchie")),
        Question("Qual é o nome do super-herói mais poderoso da série, líder dos Seven?", "Homelander", listOf("Homelander", "A-Train", "Black Noir", "Queen Maeve")),
        Question("Qual é o nome do composto que dá poderes aos super-heróis na série?", "Composto V", listOf("Composto V", "Composto X", "Composto Z", "Composto S")),
        Question("Qual é o nome do personagem que Hughie Campbell namora no início da série?", "Robin", listOf("Robin", "Annie", "Kimiko", "Becca")),
        Question("Qual é o nome da identidade secreta de Starlight?", "Annie January", listOf("Annie January", "Robin Ward", "Kimiko Miyashiro", "Becca Butcher")),
        Question("Qual é o nome da empresa que gerencia os super-heróis na série?", "Vought International", listOf("Vought International", "Wayne Enterprises", "Stark Industries", "LexCorp")),
        Question("Quem é o super-herói que causa a morte da namorada de Hughie no início da série?", "A-Train", listOf("A-Train", "Homelander", "The Deep", "Black Noir")),
        Question("Qual é o nome do personagem que é um ex-militar e membro dos The Boys, conhecido como Mother's Milk?", "Marvin T. Milk", listOf("Marvin T. Milk", "Billy Butcher", "Frenchie", "Hughie Campbell")),
        Question("Qual é o nome do super-herói que pode respirar debaixo d'água e se comunicar com criaturas marinhas?", "The Deep", listOf("The Deep", "Homelander", "A-Train", "Black Noir")),
        Question("Quem é o fundador da Vought International?", "Frederick Vought", listOf("Frederick Vought", "Stan Edgar", "Madelyn Stillwell", "Ashley Barrett")),
        Question("Qual é o nome do personagem com habilidades de regeneração que não fala?", "Black Noir", listOf("Black Noir", "Homelander", "A-Train", "The Deep")),
        Question("Qual é o nome da esposa desaparecida de Billy Butcher?", "Becca Butcher", listOf("Becca Butcher", "Annie January", "Kimiko Miyashiro", "Robin Ward")),
        Question("Qual é o nome do personagem que usa habilidades de parkour e tecnologia para lutar contra os super-heróis?", "Frenchie", listOf("Frenchie", "Mother's Milk", "Hughie Campbell", "Billy Butcher")),
        Question("Qual é o verdadeiro nome de Homelander?", "John", listOf("John", "William", "Kevin", "Anthony")),
        Question("Qual é o nome da personagem que é uma super-heroína com super-força e invulnerabilidade, membro dos Seven?", "Queen Maeve", listOf("Queen Maeve", "Starlight", "Stormfront", "Kimiko")),
        Question("Qual é o nome do filho de Becca e Homelander?", "Ryan", listOf("Ryan", "Billy", "Hughie", "Marvin")),
        Question("Quem é o CEO da Vought International durante a maior parte da série?", "Stan Edgar", listOf("Stan Edgar", "Frederick Vought", "Madelyn Stillwell", "Ashley Barrett")),
        Question("Qual é o nome da personagem que se junta aos Seven como uma nova super-heroína na segunda temporada?", "Stormfront", listOf("Stormfront", "Starlight", "Queen Maeve", "Kimiko")),
        Question("Qual é a habilidade especial de A-Train?", "Supervelocidade", listOf("Supervelocidade", "Superforça", "Voo", "Invisibilidade")),
        Question("Qual é o nome da personagem interpretada por Karen Fukuhara?", "Kimiko Miyashiro", listOf("Kimiko Miyashiro", "Annie January", "Queen Maeve", "Stormfront")),
        Question("Qual é o nome do personagem que foi o assistente de Madelyn Stillwell e depois se torna o assistente de Homelander?", "Ashley Barrett", listOf("Ashley Barrett", "Stan Edgar", "Frederick Vought", "Marvin Milk")),
        Question("Qual é o nome do grupo terrorista que The Boys enfrentam na segunda temporada?", "Os Super-Terroristas", listOf("Os Super-Terroristas", "Os Sete", "Os Vigilantes", "Os Justicieros")),
        Question("Quem é o personagem que tem a habilidade de manipular eletricidade e é membro dos Seven?", "Starlight", listOf("Starlight", "Stormfront", "The Deep", "A-Train")),
        Question("Qual é o nome do laboratório onde o Composto V é produzido?", "Sage Grove Center", listOf("Sage Grove Center", "Vought Tower", "Stillwell Facility", "Redwood Labs")),
        Question("Qual é o nome do personagem que cria um culto religioso e tenta recrutar The Deep?", "Eagle the Archer", listOf("Eagle the Archer", "A-Train", "Homelander", "Black Noir")),
        Question("Qual é a fraqueza conhecida de Black Noir?", "Alergia a nozes", listOf("Alergia a nozes", "Luz solar", "Água", "Som alto")),
        Question("Qual é o nome da super-heroína que é revelada como uma supremacista branca na segunda temporada?", "Stormfront", listOf("Stormfront", "Queen Maeve", "Starlight", "Kimiko")),
        Question("Quem é o personagem que possui uma empresa de relações públicas que trabalha para Vought International?", "Ashley Barrett", listOf("Ashley Barrett", "Stan Edgar", "Madelyn Stillwell", "Marvin Milk")),
        Question("Qual é o nome da cidade fictícia onde The Boys é ambientada?", "Nova York", listOf("Nova York", "Los Angeles", "Chicago", "Seattle")),
        Question("Qual é a bebida preferida de Billy Butcher?", "Leite", listOf("Leite", "Cerveja", "Uísque", "Vodka"))


    ).shuffled()

    /// Índice da pergunta atual
    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eighteen)

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
            score >= 90 -> "P#T4 M3@rD*, isso foi do cacessete! *SELO - BILLY BRUTOS APPROVED*"
            score >= 40 && score < 90 -> "Ficamos emocinados com seu desempenho, ele nos lembrou os discursos do Black Noir."
            else -> "Você não é como outros. Você comete erros, Você é mais fraco, mais burro..VOCÊ É PIOR!"
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
