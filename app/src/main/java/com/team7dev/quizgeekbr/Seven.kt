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


class Seven : BaseActivity() {

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
        Question("Qual é o nome do protagonista de Jujutsu Kaisen?", "Yuji Itadori", listOf("Yuji Itadori", "Megumi Fushiguro", "Satoru Gojo", "Nobara Kugisaki")),
        Question("Qual é o nome do espírito amaldiçoado que Yuji ingere?", "Ryomen Sukuna", listOf("Ryomen Sukuna", "Mahito", "Jogo", "Hanami")),
        Question("Quem é o professor de Yuji na Tokyo Jujutsu High?", "Satoru Gojo", listOf("Satoru Gojo", "Kento Nanami", "Masamichi Yaga", "Aoi Todo")),
        Question("Qual é a habilidade especial de Satoru Gojo?", "Limitless", listOf("Limitless", "Ten Shadows Technique", "Boogie Woogie", "Cursed Speech")),
        Question("Qual é o nome da escola rival da Tokyo Jujutsu High?", "Kyoto Jujutsu High", listOf("Kyoto Jujutsu High", "Osaka Jujutsu High", "Nagoya Jujutsu High", "Hokkaido Jujutsu High")),
        Question("Qual é a técnica amaldiçoada de Megumi Fushiguro?", "Ten Shadows Technique", listOf("Ten Shadows Technique", "Boogie Woogie", "Idle Transfiguration", "Straw Doll Technique")),
        Question("Quem é o diretor da Tokyo Jujutsu High?", "Masamichi Yaga", listOf("Masamichi Yaga", "Satoru Gojo", "Kiyotaka Ijichi", "Kento Nanami")),
        Question("Qual é o nome do espírito amaldiçoado que pode modificar corpos humanos?", "Mahito", listOf("Mahito", "Jogo", "Dagon", "Hanami")),
        Question("Qual é o nome do melhor amigo de Yuji na escola?", "Junpei Yoshino", listOf("Junpei Yoshino", "Toge Inumaki", "Aoi Todo", "Yuta Okkotsu")),
        Question("Quem usa a técnica Boogie Woogie?", "Aoi Todo", listOf("Aoi Todo", "Yuji Itadori", "Megumi Fushiguro", "Kento Nanami")),
        Question("Qual é a técnica de Nobara Kugisaki?", "Straw Doll Technique", listOf("Straw Doll Technique", "Boogie Woogie", "Cursed Speech", "Ten Shadows Technique")),
        Question("Quem é o usuário da técnica Cursed Speech?", "Toge Inumaki", listOf("Toge Inumaki", "Yuji Itadori", "Aoi Todo", "Maki Zenin")),
        Question("Qual é o nome da irmã de Maki Zenin?", "Mai Zenin", listOf("Mai Zenin", "Nobara Kugisaki Zenin", "Mei Mei Zenin", "Kasumi Zenin Miwa")),
        Question("Qual é a técnica de Kento Nanami?", "Ratio Technique", listOf("Ratio Technique", "Boogie Woogie", "Limitless", "Idle Transfiguration")),
        Question("Qual é a técnica de Panda?", "Gorilla Mode", listOf("Gorilla Mode", "Boogie Woogie", "Cursed Speech", "Ten Shadows Technique")),
        Question("Quem é o principal vilão no do anime?", "Ryomen Sukuna", listOf("Ryomen Sukuna", "Mahito", "Jogo", "Hanami")),
        Question("Qual é o nome do festival onde escolas de jujutsu competem?", "Goodwill Event", listOf("Goodwill Event", "Kyoto Exchange Event", "Tokyo Festival", "Jujutsu Battle")),
        Question("Qual é o objetivo principal dos feiticeiros de jujutsu?", "Exorcizar espíritos amaldiçoados", listOf("Exorcizar espíritos amaldiçoados", "Proteger relíquias", "Treinar novos feiticeiros", "Curar maldições")),
        Question("Quem é o usuário da técnica Idle Transfiguration?", "Mahito", listOf("Mahito", "Jogo", "Ryomen Sukuna", "Hanami")),
        Question("Qual é a técnica de combate principal de Yuji Itadori?", "Black Flash", listOf("Black Flash", "Boogie Woogie", "Cursed Speech", "Straw Doll Technique")),
        Question("Qual é o nome do grupo de espíritos amaldiçoados que querem destruir a humanidade?", "Cursed Spirits", listOf("Cursed Spirits", "Cursed Alliance", "Jujutsu Sorcerers", "Spirit Society")),
        Question("Quem é o assistente de Satoru Gojo?", "Kiyotaka Ijichi", listOf("Kiyotaka Ijichi", "Kento Nanami", "Masamichi Yaga", "Aoi Todo")),
        Question("Qual é o nome da técnica de Mai Zenin?", "Construction", listOf("Construction", "Straw Doll Technique", "Boogie Woogie", "Idle Transfiguration")),
        Question("Qual é o nome do domínio expandido de Satoru Gojo?", "Infinite Void", listOf("Infinite Void", "Malevolent Shrine", "Coffin of the Iron Mountain", "Self-Embodiment of Perfection")),
        Question("Quem é o feiticeiro de jujutsu que é um animal?", "Panda", listOf("Panda", "Toge, O castor", "Maki Salamandra", "Todo Gorila")),
        Question("Qual é o nome do espírito amaldiçoado que usa flores como armas?", "Hanami", listOf("Hanami", "Jogo", "Dagon", "Mahito")),
        Question("Qual é a técnica de combate principal de Satoru Gojo?", "Infinity", listOf("Infinity", "Limitless", "Red", "Blue")),
        Question("Quem é o feiticeiro de jujutsu que é considerado o mais forte?", "Satoru Gojo", listOf("Satoru Gojo", "Yuji Itadori", "Megumi Fushiguro", "Kento Nanami")),
        Question("Qual é o nome do domínio expandido de Mahito?", "Self-Embodiment of Perfection", listOf("Self-Embodiment of Perfection", "Infinite Void", "Malevolent Shrine", "Coffin of the Iron Mountain")),
        Question("Quem é o feiticeiro de jujutsu que trabalha como professor e exorcista?", "Kento Nanami", listOf("Kento Nanami", "Satoru Gojo", "Aoi Todo", "Masamichi Yaga"))

    ).shuffled()

    /// Índice da pergunta atual
    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seven)

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
            score >= 90 -> "Digno de ser o receptáculo do próprio Sukuna. !"
            score >= 40 && score < 90 -> "As maldições estão se divertindo, continue!"
            else -> " Foi uma performance tão ruim que até as maldições sentiram pena."
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
