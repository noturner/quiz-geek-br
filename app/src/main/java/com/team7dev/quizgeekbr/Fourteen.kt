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


class Fourteen : BaseActivity() {

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
        Question("Qual é o nome do protagonista da série The Witcher?", "Geralt de Rivia", listOf("Geralt de Rivia", "Jaskier", "Vesemir", "Yennefer")),
        Question("Qual é o nome da atriz que interpreta Yennefer de Vengerberg?", "Anya Chalotra", listOf("Anya Chalotra", "Freya Allan", "Emma Appleton", "Anna Shaffer")),
        Question("Qual é o nome da princesa de Cintra que é perseguida por Nilfgaard?", "Ciri", listOf("Ciri", "Triss", "Renfri", "Yennefer")),
        Question("Qual é o nome do bardo que acompanha Geralt em suas aventuras?", "Jaskier", listOf("Jaskier", "Dandelion", "Zoltan", "Eskel")),
        Question("Qual é o nome do reino onde Ciri nasceu?", "Cintra", listOf("Cintra", "Temeria", "Redania", "Aedirn")),
        Question("Qual é o nome da mãe adotiva de Geralt?", "Visenna", listOf("Visenna", "Calanthe", "Pavetta", "Tissaia")),
        Question("Qual é o nome da espada de aço de Geralt?", "Zireael", listOf("Zireael", "Sihil", "Gwyhyr", "Aerondight")),
        Question("Qual é a profissão de Geralt de Rivia?", "Witcher", listOf("Witcher", "Mago", "Bardo", "Cavaleiro")),
        Question("Quem é o rei de Cintra?", "Eist Tuirseach", listOf("Eist Tuirseach", "Foltest", "Vizimir", "Henselt")),
        Question("Qual é o nome do pai de Ciri?", "Duny", listOf("Duny", "Eredin", "Cahir", "Istredd")),
        Question("Qual é o nome da mãe de Ciri?", "Pavetta", listOf("Pavetta", "Calanthe", "Tissaia", "Renfri")),
        Question("Qual é o nome da fortaleza dos Witchers?", "Kaer Morhen", listOf("Kaer Morhen", "Aretuza", "Gors Velen", "Cintra")),
        Question("Qual é o nome do mentor de Geralt?", "Vesemir", listOf("Vesemir", "Lambert", "Eskel", "Coën")),
        Question("Quem transforma Yennefer em uma feiticeira poderosa?", "Tissaia de Vries", listOf("Tissaia de Vries", "Fringilla Vigo", "Philippa Eilhart", "Triss Merigold")),
        Question("Qual é a moeda usada no mundo de The Witcher?", "Coroa", listOf("Coroa", "Florim", "Ducat", "Novigrad")),
        Question("Qual é o nome do cavalo de Geralt?", "Roach", listOf("Roach", "Bucephalus", "Carpeado", "Epona")),
        Question("Qual é a habilidade especial dos Witchers?", "Sentidos aumentados", listOf("Sentidos aumentados", "Imortalidade", "Invisibilidade", "Teletransporte")),
        Question("Qual é o nome da feiticeira que ajuda Geralt em várias ocasiões?", "Triss Merigold", listOf("Triss Merigold", "Yennefer", "Philippa Eilhart", "Fringilla Vigo")),
        Question("Qual é o nome da rainha de Cintra?", "Calanthe", listOf("Calanthe", "Meve", "Adda", "Saskia")),
        Question("Qual é o nome da ordem de feiticeiras de que Yennefer faz parte?", "Aretuza", listOf("Aretuza", "Ban Ard", "Brokilon", "Ellander")),
        Question("Quem é o líder do exército Nilfgaardiano que persegue Ciri?", "Cahir", listOf("Cahir", "Emhyr", "Eredin", "Vilgefortz")),
        Question("Qual é o nome do monstro que Geralt enfrenta no primeiro episódio?", "Kikimora", listOf("Kikimora", "Striga", "Griffin", "Leshen")),
        Question("Qual é a cor dos olhos de Geralt?", "Amarelos", listOf("Amarelos", "Azuis", "Verdes", "Vermelhos")),
        Question("Qual é o nome da jovem que Geralt protege como seu destino?", "Ciri", listOf("Ciri", "Renfri", "Adda", "Fringilla")),
        Question("Quem é o autor dos livros nos quais a série The Witcher é baseada?", "Andrzej Sapkowski", listOf("Andrzej Sapkowski", "George R. R. Martin", "J. R. R. Tolkien", "Brandon Sanderson")),
        Question("Qual é o símbolo associado aos Witchers da Escola do Lobo?", "Lobo", listOf("Lobo", "Grifo", "Víbora", "Urso")),
        Question("Qual é o nome do festival onde Pavetta revela seus poderes?", "Banquete da promessa", listOf("Banquete da promessa", "Festa de Belleteyn", "Baile da Estrela Negra", "Feast of Saovine")),
        Question("Qual é o nome da cidade onde Yennefer cresceu?", "Vengerberg", listOf("Vengerberg", "Oxenfurt", "Novigrad", "Cintra")),
        Question("Quem é o comandante do exército Nilfgaardiano?", "Emhyr var Emreis", listOf("Emhyr var Emreis", "Foltest", "Vizimir", "Demawend")),
        Question("Qual é a primeira criatura que Geralt enfrenta na série?", "Renfri", listOf("Renfri", "Striga", "Kikimora", "Griffin"))

    ).shuffled()

    /// Índice da pergunta atual
    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourteen)

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
            score >= 90 -> "Seu desempenho é digno de um posto de honra na Guilda dos Bruxos!"
            score >= 40 && score < 90 -> "Você precisa de mais treino na Escola do Lobo antes de enfrentar o esse Quiz novamente."
            else -> "Temos uma vaga para você, como bruxo de festa infantil, é pegar ou largar!."
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
