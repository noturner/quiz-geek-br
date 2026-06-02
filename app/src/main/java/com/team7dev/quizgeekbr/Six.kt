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


class Six : BaseActivity() {

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
        Question("Qual o nome do protagonista principal de One Piece?", "Monkey D. Luffy", listOf("Monkey D. Luffy", "Gol D. Roger", "Eiichiro Oda", "Deus Usopp")),
        Question("Qual é a profissão de Nami no início da série?", "Cartógrafa", listOf("Cartógrafa", "Navegadora", "Ladra", "Médica")),
        Question("Quem é o personagem que utiliza mais espadas ao mesmo tempo?", "Hatchan", listOf("Roronoa Zoro", "Dracule Mihawk", "Hatchan", "Kin'emon")),
        Question("Que personagem é apaixonada pelo Luffy?", "Boa Hancock", listOf("Nico Robin", "Nami", "Camie", "Boa Hancock")),
        Question("Qual é o sonho de infância de Usopp?", "Se tornar um bravo guerreiro dos mares", listOf("Se tornar um bravo guerreiro dos mares", "Se tornar o maior atirador", "Se tornar um grande inventor", "Se tornar um grande navegador")),
        Question("Qual é o nome do navio atual dos Chapéu de Palha?", "Thousand Sunny", listOf("Thousand Sunny", "Going Merry", "Red Force", "Victory Hunter")),
        Question("Quem é o capitão dos Piratas do Coração?", "Trafalgar D. Water Law", listOf("Trafalgar D. Water Law", "Eustass Kid", "Capone Bege", "Scratchmen Apoo")),
        Question("Quem é conhecido como 'o Homem mais Forte do Mundo' em One Piece?", "Edward Newgate (Barba Branca)", listOf("Edward Newgate (Barba Branca)", "Gol D. Roger", "Kaido", "Marshall D. Teach (Barba Negra)")),
        Question("Qual é o nome verdadeiro da Akuma no Mi do Luffy?", "Hito Hito no Mi", listOf("Gomu Gomu no Mi", "Hana Hana no Mi", "Hito Hito no Mi", "Bomu Bomu no Mi")),
        Question("Quem é o fundador dos Piratas do Sol?", "Fisher Tiger", listOf("Jinbe", "Arlong", "Fisher Tiger", "Aladine")),
        Question("Qual é o nome da técnica de Nami que controla o clima?", "Clima-Tact", listOf("Clima-Tact", "Perfect Clima-Tact", "Clima Ball", "Thunderbolt Tempo")),
        Question("Quem é conhecido como 'o Canibal' em One Piece?", "Bartolomeo", listOf("Bartolomeo", "Wapol", "Charlotte Linlin (Big Mom)", "Bellamy")),
        Question("Qual é o nome da Akuma no mi do Brook?", "Yomi Yomi no Mi", listOf("Yomi Yomi no Mi", "Yami Yami no Mi", "Tori Tori no Mi", "Yuki Yuki no Mi")),
        Question("Qual é o nome da espada de Zoro que ele recebe de Kuina?", "Wado Ichimonji", listOf("Wado Ichimonji", "Shusui", "Sandai Kitetsu", "Yubashiri")),
        Question("Quem é o Yonkou mais forte da antiga geração?", "Kaido", listOf("Kaido", "Charlotte Linlin", "Shanks", "Edward Newgate")),
        Question("Qual é o Akuma no Mi que pode conceder a juventude eterna?", "Ope Ope no Mi", listOf("Ope Ope no Mi", "Pika Pika no Mi", "Gura Gura no Mi", "Mero Mero no Mi")),
        Question("Quem é o comandante da 3ª divisão de Piratas do Barba Branca?", "Jozu", listOf("Ace", "Marco", "Jozu", "Vista")),
        Question("Qual é o nome da primeira ilha visitada pelos Chapéu de Palha no Novo Mundo?", "Punk Hazard", listOf("Punk Hazard", "Dressrosa", "Ilha dos Homens-Peixe", "Ilha Whole Cake")),
        Question("Qual nome do primeiro vilão de One Piece", "Alvida", listOf("Alvida", "Morgan", "Klahadore", "Buggy")),
        Question("Qual é o nome da habilidade mais forte do Ace?", "Entei", listOf("Dai Enkai", "Hiken", "Entei", "Hidaruma")),
        Question("Qual comida preferida do Marshall D. Teach?", "Tortas", listOf("Tortas", "Carne", "Algodão Doce", "Sake")),
        Question("Qual nome da ilha do reino Kamabakka?", "Momoiro", listOf("Momoiro", "Jaya", "Okama Paradise", "New Kama")),
        Question("Quem se sacrificou pelo Luffy em Impel Down?", "Bon Clay", listOf("Bon Clay", "Ace", "Buggy", "Jinbei")),
        Question("Quanto tempo durou a luta de Aokiji vs Akainu", "10 dias", listOf("10 dias", "1 dias", "5 dias", "3 dias")),
        Question("Quem é o decimo Capitão Colossal dos Piratas do Barba Negra?", "Kuzan", listOf("Kuzan", "Shiryu", "Jesus Burgess", "Laffitte")),
        Question("Qual nome das áreas que separam a Grand Line dos demais oceanos?", "Calm Belt", listOf("Calm Belt", "Red Line", "Oceano Celeste", "All Blue")),
        Question("Qual nome do dispositivo necessário para navegar na Grand Line?", "Log Pose", listOf("Log Pose", "Bússola", "Magnet Log", "Log Slip")),
        Question("Qual é o nome da primeira música que o Brook canta?", "Bink's Sake", listOf("Bink's Sake", "We Are", "Yahazu giri", "Party Song")),
        Question("Qual família abandonou o título de Tenryuubito?", "Nefertari", listOf("Doflamingo", "Figarland", "Nerona", "Nefertari")),
        Question("Qual é o nome da ilha em que nasceu Nami?", "Cocoyasi", listOf("Cocoyasi", "Loguetown", "Orange Town", "Syrup Village")),
        Question("Quem é o líder dos Piratas do Big Mom?", "Charlotte Linlin", listOf("Charlotte Linlin", "Charlotte Katakuri", "Charlotte Smoothie", "Charlotte Cracker")),
        Question("Qual é o nome da ilha em que nasceu Nico Robin?", "Ohara", listOf("Ohara", "Water 7", "Mariejois", "Dressrosa")),
        Question("Quem é um dos lideres do Exército Revolucionário?", "Sabo", listOf("Emporio Ivankov", "Sabo", "Inazuma", "Bartholomew Kuma")),
        Question("Qual título de Bartholomew Kuma?", "Tirano", listOf("O Tirano", "O Canibal", "A Hiena", "O Revolucionário")),
        ).shuffled()

    /// Índice da pergunta atual
    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_six)

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
            score >= 90 -> "Parabéns, você é um verdadeiro rei dos piratas!"
            score >= 40 && score < 90 -> "Luffy e sua tripulação também enfrentaram desafios. Você está no caminho certo!"
            else -> "Yo-ho-ho-ho, Zoro se perdeu mais vezes que sua pontuação!"
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
