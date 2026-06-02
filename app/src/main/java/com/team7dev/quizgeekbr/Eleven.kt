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


class Eleven : BaseActivity() {

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
        Question("Quem é conhecido como o Rei do Norte no início da série?", "Eddard Stark", listOf("Robb Stark", "Eddard Stark", "Jon Snow", "Bran Stark")),
        Question("Qual é o lema da Casa Stark?", "O Inverno Está Chegando", listOf("O Inverno Está Chegando", "Ouça-me Rugir", "Fogo e Sangue", "Nós Não Semeamos")),
        Question("Quem é a mãe dos dragões?", "Daenerys Targaryen", listOf("Daenerys Targaryen", "Cersei Lannister", "Sansa Stark", "Margaery Tyrell")),
        Question("Qual é o nome do lobo gigante de Jon Snow?", "Ghost", listOf("Ghost", "Nymeria", "Grey Wind", "Summer")),
        Question("Quem mata Joffrey Baratheon?", "Olenna Tyrell", listOf("Olenna Tyrell", "Tyrion Lannister", "Sansa Stark", "Arya Stark")),
        Question("Qual é o nome da espada de Jon Snow?", "Garra longa", listOf("Garralonga", "Garraafia da", "Garra do lobo", "Agulha")),
        Question("Quem é o Cavaleiro das Flores?", "Loras Tyrell", listOf("Loras Tyrell", "Jaime Lannister", "Brienne de Tarth", "Sandor Clegane")),
        Question("Quem se torna o Rei de Westeros no final da série?", "Bran Stark", listOf("Bran Stark", "Jon Snow", "Daenerys Targaryen", "Tyrion Lannister")),
        Question("Qual é o nome da espada de Arya Stark?", "Agulha", listOf("Agulha", "Lamento da Viúva", "Garra longa", "Veneno do Coração")),
        Question("Quem é o pai verdadeiro de Jon Snow?", "Rhaegar Targaryen", listOf("Rhaegar Targaryen", "Ned Stark", "Robert Baratheon", "Jaime Lannister")),
        Question("Qual é o lema da Casa Lannister?", "Sempre Pagamos Nossas Dívidas", listOf("Ouça-me Rugir", "Sempre Pagamos Nossas Dívidas", "Nós Não Semeamos", "Fogo e Sangue")),
        Question("Quem mata o Rei da Noite?", "Arya Stark", listOf("Arya Stark", "Jon Snow", "Daenerys Targaryen", "Bran Stark")),
        Question("Qual é o nome do castelo da Casa Stark?", "Winterfell", listOf("Winterfell", "Castelo Negro", "Pyke", "Pedra do Dragão")),
        Question("Qual é o nome do bastardo criado por Eddard Stark?", "Jon Snow", listOf("Jon Snow", "Theon Greyjoy", "Samwell Tarly", "Gendry")),
        Question("Quem como o Rei Joffrey morreu ?", "Envenenado", listOf("Queimado", "Envenenado", "Enforcado", "Apunhalado")),
        Question("Qual é o nome do bastardo de Robert Baratheon?", "Gendry", listOf("Gendry", "Ramsay Bolton", "Jon Snow", "Podrick Payne")),
        Question("Quem é a Mão do Rei de Robert Baratheon no início da série?", "Eddard Stark", listOf("Eddard Stark", "Jaime Lannister", "Tyrion Lannister", "Stannis Baratheon")),
        Question("Quem é o irmão mais novo de Daenerys Targaryen?", "Viserys Targaryen", listOf("Viserys Targaryen", "Rhaegar Targaryen", "Aegon Targaryen", "Jon Snow")),
        Question("Qual é o nome verdadeiro do Rei da Noite?", "Desconhecido", listOf("Desconhecido", "Rhaegar Targaryen", "Aegon Targaryen", "Benjen Stark")),
        Question("Qual é o nome da cidade livre onde Daenerys liberta os escravos?", "Meereen", listOf("Meereen", "Astapor", "Qarth", "Braavos")),
        Question("Quem treina Arya Stark na arte de lutar com espadas?", "Syrio Forel", listOf("Syrio Forel", "Sandor Clegane", "Brienne de Tarth", "Jaqen H'ghar")),
        Question("Qual é o nome do gigante amigo de Bran Stark?", "Hodor", listOf("Hodor", "Wun Wun", "Mag the Mighty", "One-Eye")),
        Question("Qual é o nome do lobo gigante de Sansa Stark?", "Lady", listOf("Lady", "Nymeria", "Summer", "Shaggydog")),
        Question("Quem se autoproclama o Rei das Ilhas de Ferro?", "Euron Greyjoy", listOf("Euron Greyjoy", "Theon Greyjoy", "Balon Greyjoy", "Asha Greyjoy")),
        Question("Qual é o nome do castelo da Casa Targaryen?", "Pedra do Dragão", listOf("Pedra do Dragão", "Porto Real", "Castelo Negro", "Harrenhal")),
        Question("Quem é conhecida a quebradora de correntes ?", "Daenerys Targaryen", listOf("Daenerys Targaryen", "Cersei Lannister", "Sansa Stark", "Margaery Tyrell")),
        Question("Qual é o nome do conselheiro leal de Daenerys?", "Jorah Mormont", listOf("Jorah Mormont", "Tyrion Lannister", "Varys", "Daario Naharis")),
        Question("Quem mata Ramsay Bolton?", "Sansa Stark", listOf("Sansa Stark", "Jon Snow", "Bran Stark", "Arya Stark")),
        Question("Qual é o nome do deus da luz adorado por Melisandre?", "R'hllor", listOf("R'hllor", "O Estranho", "O Senhor da Tempestade", "O Pai")),
        Question("Quem é o comandante da Patrulha da Noite no início da série?", "Jeor Mormont", listOf("Jeor Mormont", "Jon Snow", "Eddard Stark", "Benjen Stark"))

    ).shuffled()

    /// Índice da pergunta atual
    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eleven)

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
            score >= 90 -> "Pelos sete reinos! Você é o Azor Ahai, o Príncipe(sa) que Foi Prometido(da)!!"
            score >= 40 && score < 90 -> "Talvez você precise de mais 'treinamento' na Cidadela antes de enfrentar o próximo quiz."
            else -> "VERGONHA, *ding* VERGONHA, *ding* VERGONHA, *ding* VERGONHA, *ding*, VERGONHA.. "
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
