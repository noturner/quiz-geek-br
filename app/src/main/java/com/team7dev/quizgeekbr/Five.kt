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


class Five : BaseActivity() {

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
        Question("Quem é o pai de Naruto?", "Minato Namikaze", listOf("Minato Namikaze", "Hiruzen Sarutobi", "Jiraiya", "Kakashi Hatake")),
        Question("Qual é o nome do Bijuu dentro de Naruto?", "Kurama", listOf("Kurama", "Shukaku", "Gyuki", "Matatabi")),
        Question("Quem foi o primeiro Hokage de Konoha?", "Hashirama Senju", listOf("Hashirama Senju", "Tobirama Senju", "Hiruzen Sarutobi", "Minato Namikaze")),
        Question("Qual é o nome do clã de Sasuke?", "Uchiha", listOf("Uchiha", "Hyuga", "Senju", "Nara")),
        Question("Quem é a esposa de Naruto?", "Hinata Hyuga", listOf("Hinata Hyuga", "Sakura Haruno", "Ino Yamanaka", "Tenten")),
        Question("Qual é o nome do melhor amigo de Naruto?", "Sasuke Uchiha", listOf("Sasuke Uchiha", "Shikamaru Nara", "Kiba Inuzuka", "Rock Lee")),
        Question("Quem criou a técnica do Rasengan?", "Minato Namikaze", listOf("Minato Namikaze", "Jiraiya", "Kakashi Hatake", "Naruto Uzumaki")),
        Question("Qual é a habilidade especial do clã Hyuga?", "Byakugan", listOf("Byakugan", "Sharingan", "Rinnegan", "Mangekyou Sharingan")),
        Question("Qual é o nome da organização criminosa à qual Itachi pertenceu?", "Akatsuki", listOf("Akatsuki", "Kara", "Hebi", "Root")),
        Question("Quem incapacitou Orochimaru por um longo periodo de tempo no anime?", "Sasuke Uchiha", listOf("Sasuke Uchiha", "Itachi Uchiha", "Naruto Uzumaki", "Hiruzen Sarutobi")),
        Question("Qual é o nome do professor de Naruto, Sasuke e Sakura?", "Kakashi Hatake", listOf("Kakashi Hatake", "Iruka Umino", "Jiraiya", "Might Guy")),
        Question("Quem se torna o Sexto Hokage?", "Kakashi Hatake", listOf("Kakashi Hatake", "Naruto Uzumaki", "Tsunade Senju", "Sasuke Uchiha")),
        Question("Qual é a habilidade ocular mais poderosa?", "Rinnegan", listOf("Rinnegan", "Sharingan", "Byakugan", "Mangekyou Sharingan")),
        Question("Quem matou Hiruzen Sarutobi?", "Orochimaru", listOf("Orochimaru", "Itachi Uchiha", "Nagato", "Madara Uchiha")),
        Question("Qual é o nome do exame que Naruto reprovou em sua primeira vez fazendo?", "Exame Chunin", listOf("Exame Chunin", "Exame Genin", "Exame Jonin", "Exame ANBU")),
        Question("Quem é o líder da Akatsuki?", "Pain", listOf("Pain", "Itachi Uchiha", "Kisame Hoshigaki", "Konan")),
        Question("Qual é o nome da invocação de Jiraiya?", "Gama Bunta", listOf("Gama Bunta", "Manda", "Katsuyu", "Gamaken")),
        Question("Quem derrotou Pain?", "Naruto Uzumaki", listOf("Naruto Uzumaki", "Kakashi Hatake", "Jiraiya", "Tsunade Senju")),
        Question("Qual é o nome da técnica que Naruto usa para multiplicar-se?", "Kage Bunshin no Jutsu", listOf("Kage Bunshin no Jutsu", "Henge no Jutsu", "Kawarimi no Jutsu", "Shuriken Kage Bunshin no Jutsu")),
        Question("Qual é o nome do terceiro Hokage?", "Hiruzen Sarutobi", listOf("Hiruzen Sarutobi", "Tobirama Senju", "Minato Namikaze", "Hashirama Senju")),
        Question("Quem é conhecido como o 'Sannin Lendário' ao lado de Jiraiya e Tsunade?", "Orochimaru", listOf("Orochimaru", "Itachi Uchiha", "Kakashi Hatake", "Sasuke Uchiha")),
        Question("Qual é o nome verdadeiro de Pain?", "Nagato", listOf("Nagato", "Yahiko", "Konan", "Obito")),
        Question("Quem ensinou Naruto a usar o Rasengan?", "Jiraiya", listOf("Jiraiya", "Kakashi Hatake", "Minato Namikaze", "Tsunade Senju")),
        Question("Qual é o nome da técnica ocular de Kakashi?", "Sharingan", listOf("Sharingan", "Byakugan", "Rinnegan", "Mangekyou Sharingan")),
        Question("Qual é o nome do irmão de Itachi?", "Sasuke Uchiha", listOf("Sasuke Uchiha", "Madara Uchiha", "Shisui Uchiha", "Obito Uchiha")),
        Question("Quem foi o Quinto Hokage?", "Tsunade Senju", listOf("Tsunade Senju", "Minato Namikaze", "Kakashi Hatake", "Hiruzen Sarutobi")),
        Question("Qual é o nome da técnica que permite ao usuário controlar marionetes?", "Kugutsu no Jutsu", listOf("Kugutsu no Jutsu", "Kuchiyose no Jutsu", "Kage Mane no Jutsu", "Fūinjutsu")),
        Question("Quem é o Jinchuriki do Shukaku?", "Gaara", listOf("Gaara", "Naruto Uzumaki", "Killer Bee", "Yagura")),
        Question("Qual é o nome do clã de Neji?", "Hyuga", listOf("Hyuga", "Uchiha", "Akimichi", "Nara")),
        Question("Quem foi o Sétimo Hokage?", "Naruto Uzumaki", listOf("Tsunade Senju", "Minato Namikaze", "Kakashi Hatake", "Naruto Uzumaki")),
    ).shuffled()

    /// Índice da pergunta atual
    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_five)

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
            score >= 90 -> "Madara te declara, o ninja mais forte!"
            score >= 40 && score < 90 -> "Até os melhores ninjas começaram como genins. Continue sua jornada!"
            else -> "Mais vergonhoso que a Kurenai tentando lançar um genjutso no Itachi"
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
