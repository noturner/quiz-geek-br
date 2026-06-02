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


class Four : BaseActivity() {

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

        Question("Qual é a técnica de Goku para se teletransportar?", "Transmissão instantânea", listOf("Transmissão instantânea", "Kamehameha", "Genki Dama", "Kai Kai")),
        Question("Qual é o nome do planeta natal dos Saiyajins?", "Planeta Vegeta", listOf("Planeta Vegeta", "Planeta Terra", "Planeta Namekusei", "Planeta Freeza")),
        Question("Quem é considerado o Deus da Destruição no universo 7?", "Beerus", listOf("Beerus", "Whis", "Champa", "Majin Boo")),
        Question("Qual é a técnica que Goku aprendeu com o Senhor Kaioh?", "Kai Kai", listOf("Kai Kai", "Instant Transmission", "Kamehameha", "Genki Dama")),
        Question("Qual é a transformação de Vegeta que faz seu cabelo ficar azul?", "Super Saiyajin Blue", listOf("Super Saiyajin Blue", "Super Saiyajin 1", "Super Saiyajin 2", "Super Saiyajin 3")),
        Question("Quem é o pai de Goku?", "Bardock", listOf("Bardock", "Vegeta", "Gohan", "Piccolo")),
        Question("Quem é a esposa de Goku?", "Chichi", listOf("Chichi", "Bulma", "Videl", "Androide 18")),
        Question("Qual é a primeira Habilidade que Goku aprendeu com o Mestre Kame?", "Kamehameha", listOf("Kamehameha", "Genki Dama", "Kai Kai", "Transmissão instantânea")),
        Question("Qual é o nome da espada de Trunks do Futuro?", "Espada de Trunks do Futuro", listOf("Espada de Trunks do Futuro", "Espada Z", "Espada Sayajin", "Espada do futuro")),
        Question("Qual é a cor do cabelo de Gohan na fase adulta?", "Preto", listOf("Preto", "Loiro", "Marrom", "Branco")),
        Question("Além do Kaioken, qual habilidade o Senhor Kaioh ensinou para Goku? ", "Genki Dama", listOf("Genki Dama", "Final Flash", "Transmissão instantânea", "Kamehameha")),
        Question("Qual é o nome da raça de Goku?", "Sayajin", listOf("Sayajin", "Namekuseijin", "Terraquio", "Boov")),
        Question("Qual é o nome da fusão de Goku e Vegeta?", "Gogeta", listOf("Gogeta", "Vegito", "Gotenks", "Kefla")),
        Question("Qual é o nome do irmão mais velho de Goku?", "Raditz", listOf("Raditz", "Vegeta", "Gohan", "Piccolo")),
        Question("Qual é a transformação de Vegeta que deixa seu cabelo preto e seus olhos vermelhos?", "Super Saiyajin Deus", listOf("Super Saiyajin Deus", "Super Saiyajin Blue", "Super Saiyajin Red", "Super Saiyajin King")),
        Question("Qual é a técnica que Gohan usou para derrotar Cell?", "Kamehameha", listOf("Kamehameha", "Genki Dama", "Kai Kai", "Transmissão instantânea")),
        Question("Qual é o nome do cão de Bulma?", "Fubuki", listOf("Fubuki", "Shenlong", "Ulong", "Arack")),
        Question("Qual é o nome do pai de Vegeta?", "Vegeta", listOf("Vegeta", "Kuririn", "Gohan", "Raditz")),
        Question("Qual é a profissão de Goku?", "Agricultor", listOf("Lutador", "Agricultor", "Professor", "Piloto")),
        Question("Qual é o nome do mestre de artes marciais que treinou Goku na infância?", "Mestre Kame", listOf("Mestre Kame", "Mestre Karin", "Mestre Roshi", "Mestre Popo")),
        Question("Qual é a técnica que Goku usou para derrotar Piccolo?", "Kamehameha", listOf("Kamehameha", "Genki Dama", "Kai Kai", "Instant Transmission")),
        Question("Qual é o nome da mãe de Goku?", "Gine", listOf("Gine", "Chi-Chi", "Bulma", "Milk")),
        Question("Qual é o nome da quarta transformação de Freeza?", "Forma Final", listOf("Forma Final", "Forma Dourada", "Forma 100%", "Forma Mecha")),
        Question("Qual é o nome da androide criada pelo Dr. Gero?", "Android 18", listOf("Android 18", "Android 17", "Cell", "Majin Boo")),
        Question("Qual é a habilidade especial de Majin Boo que lhe permite absorver outros lutadores?", "Absorção", listOf("Kamehameha", "Absorção", "Regeneração", "Transmissão Instantânea")),
        Question("Quem foi o primeiro a alcançar a transformação em Super Saiyajin 2?", "Gohan", listOf("Goku", "Vegeta", "Gohan", "Trunks")),
        Question("Qual é o nome do planeta natal de Piccolo?", "Namekusei", listOf("Terra", "Vegeta", "Namekusei", "Kaioshin")),
        Question("Quem matou o Rei Cold?", "Trunks do Futuro", listOf("Goku", "Vegeta", "Freeza", "Trunks do Futuro")),
        Question("Qual técnica Goku aprendeu durante seu treinamento no planeta Yardrat?","Transmissão Instantânea", listOf(" Kaioken", "Kamehameha", "Transmissão Instantânea", "Genki Dama")),
        Question("Qual é o nome da fusão resultante da fusão de Goku e Vegeta usando os brincos Potara?", "Vegito", listOf("Vegito", "Gogeta", "Gotenks", "Kefla")),

    ).shuffled()

    /// Índice da pergunta atual
    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_four)

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
            score >= 90 -> "Parabéns, você é um Príncipe(sa) Saiyajin!"
            score >= 40 && score < 90 -> "Seu treinamento não está finalizado, volte para a Sala do Tempo!"
            else -> "Parabéns, você morreria tranquilamente para Kuririn!"
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
