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


class Ten : BaseActivity() {

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

        Question("Qual é o nome do principal protagonista de Fullmetal Alchemist: Brotherhood?", "Edward Elric", listOf("Edward Elric", "Alphonse Elric", "Roy Mustang", "Winry Rockbell")),
        Question("Qual é o objetivo principal de Edward e Alphonse Elric na série?", "Recuperar seus corpos originais", listOf("Recuperar seus corpos originais", "Encontrar a Pedra Filosofal", "Destruir a Homunculi", "Proteger a nação de Amestris")),
        Question("Quem é conhecido como o 'Alquimista das Chamas'?", "Roy Mustang", listOf("Roy Mustang", "Maes Hughes", "Scar", "Kimblee")),
        Question("Qual é o nome do pai de Edward e Alphonse Elric?", "Van Hohenheim", listOf("Van Hohenheim", "King Bradley", "Maes Hughes", "Scar")),
        Question("Qual é a habilidade especial de Scar?", "Destruir usando alquimia de destruição", listOf("Destruir usando alquimia de destruição", "Controlar chamas", "Manipular água", "Transmutar metal")),
        Question("Quem é o principal vilão da série?", "Pai (Father)", listOf("Pai (Father)", "Inveja", "Gula", "Orgulho")),
        Question("Qual é a identidade secreta do Führer King Bradley?", "Orgulho", listOf("Orgulho", "Ira", "Preguiça", "Luxúria")),
        Question("O que Edward Elric sacrifica para realizar a transmutação humana?", "Seu braço direito e perna esquerda", listOf("Seu braço direito e perna esquerda", "Seu coração", "Sua visão", "Sua alma")),
        Question("Quem é a amiga de infância de Edward e Alphonse?", "Winry Rockbell", listOf("Winry Rockbell", "Riza Hawkeye", "May Chang", "Lan Fan")),
        Question("Qual é o nome da entidade que vive dentro do Portão da Verdade?", "A Verdade (The Truth)", listOf("A Verdade (The Truth)", "Pai (Father)", "Inveja", "Hohenheim")),
        Question("Qual é o nome verdadeiro do Homúnculo Luxúria?", "Caroline", listOf("Caroline", "Solara", "Cecilia", "Amelia")),
        Question("Quem mata Maes Hughes?", "Inveja", listOf("Inveja", "Luxúria", "Gula", "Ira")),
        Question("Qual é a filosofia de alquimia de Izumi Curtis?", "Uma vida vale uma vida", listOf("Uma vida vale uma vida", "O sacrifício é necessário", "A destruição leva à criação", "A alquimia é para todos")),
        Question("Quem é o irmão mais novo de Edward Elric?", "Alphonse Elric", listOf("Alphonse Elric", "Roy Mustang", "Ling Yao", "Jean Havoc")),
        Question("Qual é a habilidade especial de Roy Mustang?", "Manipulação de fogo", listOf("Manipulação de fogo", "Controle de lava", "Transmutação de faiscas", "Manipulação de água")),
        Question("Qual é o objetivo de Father ao tentar obter a Pedra Filosofal?", "Alcançar a imortalidade", listOf("Alcançar a imortalidade", "Conquistar o mundo", "Destruir Amestris", "Reviver os antigos homúnculos")),
        Question("Qual homúnculo tem a habilidade de se transformar em qualquer pessoa?", "Inveja", listOf("Inveja", "Orgulho", "Luxúria", "Gula")),
        Question("O que Alphonse Elric sacrifica para trazer Edward de volta à vida?", "Seu corpo físico", listOf("Seu corpo físico", "Sua alma", "Sua memória", "Seu braço direito")),
        Question("Quem é o líder dos Homúnculos?", "Pai (Father)", listOf("Pai (Father)", "Orgulho", "Ira", "Inveja")),
        Question("Quem é o General que defende a Fortaleza Briggs?", "Olivier Mira Armstrong", listOf("Olivier Mira Armstrong", "Alex Louis Armstrong", "Maes Hughes", "Roy Mustang")),
        Question("Qual é a conexão de Van Hohenheim com os Homúnculos?", "Ele é a base da criação dos Homúnculos", listOf("Ele é a base da criação dos Homúnculos", "Ele é o líder dos Homúnculos", "Ele é um Homúnculo", "Ele os criou")),
        Question("Quem é a mata o homúnculo Luxúria?", "Roy Mustang", listOf("Roy Mustang", "Edward Elric", "Riza Hawkeye", "Scar")),
        Question("Qual é o nome da arma de Roy Mustang?", "Luva de ignição", listOf("Luva de ignição", "Espada de fogo", "Anel de chamas", "Pedra Filosofal")),
        Question("Qual é a habilidade especial do Orgulho?", "Manipulação de sombras", listOf("Manipulação de sombras", "Super força", "Regeneração rápida", "Transformação")),
        Question("Quem ajuda os irmãos Elric a entender mais sobre a Pedra Filosofal em Xing?", "May Chang", listOf("May Chang", "Ling Yao", "Lan Fan", "Fu")),
        Question("Qual é o nome do cachorro fiel de Alexander Louis Armstrong?", "Alex Louis", listOf("Alex Louis", "Sig", "Hawkeye", "Den")),
        Question("Qual é o segredo dos soldados imortais de Father?", "Eles contêm almas humanas", listOf("Eles contêm almas humanas", "Eles são feitos de pedra filosofal", "Eles são regenerados por alquimia", "Eles são robôs")),
        Question("Quem substitui Hughes após sua morte?", "Maria Ross", listOf("Maria Ross", "Jean Havoc", "Sheska", "Buccaneer")),
        Question("Qual é o símbolo que representa a alquimia dos Elric?", "Cruz de Flamel", listOf("Cruz de Flamel", "Serpente alada", "Cruz de transmutação", "Marca de sangue")),
        Question("Quem é a companheira fiel e subordinada de Roy Mustang?", "Riza Hawkeye", listOf("Riza Hawkeye", "Winry Rockbell", "Olivier Mira Armstrong", "Lan Fan")),
        Question("Qual é a missão principal de Scar?", "Vingar-se dos alquimistas estaduais", listOf("Vingar-se dos alquimistas estaduais", "Encontrar a Pedra Filosofal", "Proteger seu povo", "Destruir a Homunculi"))




    ).shuffled()

    /// Índice da pergunta atual
    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ten)

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
            score >= 90 -> "Parabéns, como Roy Mustang manipulando suas chamas, você queimou as perguntas certas!"
            score >= 40 && score < 90 -> "Seu desempenho foi tão brilhante quanto a habilidade do Ed de crescer..continue tentando!"
            else -> "Você acertou tanto quanto o Shou Tucker acerta em ser um bom pai!"
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
