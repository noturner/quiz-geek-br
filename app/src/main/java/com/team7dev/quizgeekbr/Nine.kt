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


class Nine : BaseActivity() {

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
        Question("Qual é o nome do protagonista de Demon Slayer?", "Tanjiro Kamado", listOf("Tanjiro Kamado", "Zenitsu Agatsuma", "Inosuke Hashibira", "Giyu Tomioka")),
        Question("Qual é o nome da irmã de Tanjiro que se transforma em demônio?", "Nezuko Kamado", listOf("Nezuko Kamado", "Kanao Tsuyuri", "Aoi Kanzaki", "Shinobu Kocho")),
        Question("Qual é o nome do vilão principal em Demon Slayer?", "Muzan Kibutsuji", listOf("Muzan Kibutsuji", "Rui", "Akaza", "Enmu")),
        Question("Qual é a técnica de respiração que Tanjiro usa?", "Respiração da Água", listOf("Respiração da Água", "Respiração da Fera", "Respiração do Trovão", "Respiração do Vento")),
        Question("Qual é o nome do Hashira da Água?", "Giyu Tomioka", listOf("Giyu Tomioka", "Sanemi Shinazugawa", "Kyojuro Rengoku", "Muichiro Tokito")),
        Question("Qual é o nome do corvo mensageiro de Tanjiro?", "Kasugai", listOf("Kasugai", "Tomioka", "Kanzaki", "Tokito")),
        Question("Quem treina Tanjiro no início da série?", "Sakonji Urokodaki", listOf("Sakonji Urokodaki", "Kyojuro Rengoku", "Sanemi Shinazugawa", "Giyu Tomioka")),
        Question("Qual é a marca distintiva dos Hashira?", "Marca de Hashira", listOf("Marca de Hashira", "Marca da Lua", "Marca do Demônio", "Marca do Caçador")),
        Question("Qual é o nome do Hashira do Trovão?", "Zenitsu Agatsuma", listOf("Zenitsu Agatsuma", "Inosuke Hashibira", "Tengen Uzui", "Sanemi Shinazugawa")),
        Question("Qual é a técnica de respiração que Zenitsu usa?", "Respiração do Trovão", listOf("Respiração do Trovão", "Respiração da Água", "Respiração da Fera", "Respiração do Vento")),
        Question("Qual é o nome do Hashira do Som?", "Tengen Uzui", listOf("Tengen Uzui", "Kyojuro Rengoku", "Giyu Tomioka", "Shinobu Kocho")),
        Question("Qual é a técnica de respiração que Inosuke usa?", "Respiração da Besta", listOf("Respiração da Besta", "Respiração do Fogo", "Respiração do Trovão", "Respiração do Vento")),
        Question("Qual é o nome da Hashira do Inseto?", "Shinobu Kocho", listOf("Shinobu Kocho", "Kanao Tsuyuri", "Aoi Kanzaki", "Mitsuri Kanroji")),
        Question("Qual é o nome do Hashira das Chamas?", "Kyojuro Rengoku", listOf("Kyojuro Rengoku", "Tengen Uzui", "Giyu Tomioka", "Muichiro Tokito")),
        Question("Qual é a técnica de respiração que Kyojuro Rengoku usa?", "Respiração das Chamas", listOf("Respiração das Chamas", "Respiração da Água", "Respiração da Besta", "Respiração do Trovão")),
        Question("Qual é o nome da organização de caçadores de demônios?", "Corpo de Exterminadores de Demônios", listOf("Corpo de Exterminadores de Demônios", "Corpo de Caçadores", "Esquadrão de Demônios", "Sociedade de Caçadores")),
        Question("Qual é o nome da espada usada por Inosuke?", "Espada Nichirin", listOf("Espada Nichirin", "Espada Demoníaca", "Espada de Hashira", "Espada de Caçador")),
        Question("Qual é o nome do Hashira do Vento?", "Sanemi Shinazugawa", listOf("Sanemi Shinazugawa", "Muichiro Tokito", "Giyu Tomioka", "Kyojuro Rengoku")),
        Question("Qual é a técnica de respiração que Sanemi Shinazugawa usa?", "Respiração do Vento", listOf("Respiração do Vento", "Respiração da Água", "Respiração das Chamas", "Respiração do Trovão")),
        Question("Qual é o nome da Hashira do Amor?", "Mitsuri Kanroji", listOf("Mitsuri Kanroji", "Kanao Tsuyuri", "Shinobu Kocho", "Aoi Kanzaki")),
        Question("Qual é a técnica de respiração que Mitsuri Kanroji usa?", "Respiração do Amor", listOf("Respiração do Amor", "Respiração da Água", "Respiração da Besta", "Respiração das Chamas")),
        Question("Qual é o nome do Hashira da Névoa?", "Muichiro Tokito", listOf("Muichiro Tokito", "Kyojuro Rengoku", "Giyu Tomioka", "Tengen Uzui")),
        Question("Qual é a técnica de respiração que Muichiro Tokito usa?", "Respiração da Névoa", listOf("Respiração da Névoa", "Respiração da Água", "Respiração das Chamas", "Respiração do Trovão")),
        Question("Qual é o nome do Hashira da Serpente?", "Obanai Iguro", listOf("Obanai Iguro", "Sanemi Shinazugawa", "Tengen Uzui", "Muichiro Tokito")),
        Question("Qual é a técnica de respiração que Obanai Iguro usa?", "Respiração da Serpente", listOf("Respiração da Serpente", "Respiração da Água", "Respiração do Amor", "Respiração do Vento")),
        Question("Qual é o nome do chefe do Corpo de Exterminadores de Demônios?", "Kagaya Ubuyashiki", listOf("Kagaya Ubuyashiki", "Giyu Tomioka", "Sakonji Urokodaki", "Kyojuro Rengoku")),
        Question("Qual é o nome da Lua Superior que mata Rengoku?", "Akaza", listOf("Akaza", "Muzan Kibutsuji", "Rui", "Enmu")),
        Question("Qual é a técnica de respiração que Kanao Tsuyuri usa?", "Respiração da Flor", listOf("Respiração da Flor", "Respiração da Água", "Respiração da Névoa", "Respiração das Chamas")),
        Question("Qual é o nome do Hashira que usa técnicas de respiração baseadas no Som?", "Tengen Uzui", listOf("Tengen Uzui", "Kyojuro Rengoku", "Sanemi Shinazugawa", "Muichiro Tokito")),
        Question("Qual é o nome da arte demoníaca usada por Nezuko?", "Explosão de Sangue", listOf("Explosão de Sangue", "Manipulação de Fios", "Controle de Vento", "Transfiguração Demoníaca"))


    ).shuffled()

    /// Índice da pergunta atual
    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nine)

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
        val intent = Intent(this, Nine::class.java)

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
            score >= 90 -> "Você é o novo Hashira do Pilar do Quiz !"
            score >= 40 && score < 90 -> "Está mais para um aprendiz de Nezuko do que um Muzan!"
            else -> "Sua pontuação foi como a lealdade do Muzan...praticamente inexistente! "
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
