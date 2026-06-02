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


class Seventeen : BaseActivity() {

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
        Question("Qual é o nome do carro que os irmãos Winchester dirigem?", "Chevrolet Impala", listOf("Chevrolet Impala", "Ford Mustang", "Dodge Charger", "Pontiac GTO")),
        Question("Quem é o irmão mais velho, caçador de monstros, da série?", "Dean Winchester", listOf("Dean Winchester", "Sam Winchester", "John Winchester", "Castiel")),
        Question("Qual é o nome do demônio de olhos amarelos que matou a mãe de Sam e Dean?", "Azazel", listOf("Azazel", "Lilith", "Crowley", "Abaddon")),
        Question("Quem é o anjo que se torna aliado dos irmãos Winchester?", "Castiel", listOf("Castiel", "Gabriel", "Michael", "Lucifer")),
        Question("Qual é a ocupação de Bobby Singer antes de se tornar caçador?", "Dono de um ferro-velho", listOf("Dono de um ferro-velho", "Policial", "Professor", "Médico")),
        Question("Qual é o nome da faca que pode matar demônios?", "Faca de Ruby", listOf("Faca de Ruby", "Lâmina de Arcanjo", "Faca de Bobby", "Faca de Castiel")),
        Question("Qual é o nome do profeta que escreve sobre a vida dos irmãos Winchester?", "Chuck Shurley", listOf("Chuck Shurley", "Kevin Tran", "Metatron", "Joshua")),
        Question("Qual é o nome do local onde os anjos são julgados?", "O Céu", listOf("O Céu", "O Inferno", "O Purgatório", "O Paraíso")),
        Question("Qual é o nome do cavalo do apocalipse que causa fome?", "Fome", listOf("Fome", "Morte", "Peste", "Guerra")),
        Question("Quem é o rei do Inferno durante a maior parte da série?", "Crowley", listOf("Crowley", "Lucifer", "Azazel", "Lilith")),
        Question("Qual é o nome da mãe dos irmãos Winchester?", "Mary Winchester", listOf("Mary Winchester", "Ellen Harvelle", "Jo Harvelle", "Lisa Braeden")),
        Question("Qual é o nome do arcanjo que é o líder dos anjos rebeldes?", "Lucifer", listOf("Lucifer", "Gabriel", "Michael", "Raphael")),
        Question("Qual é o nome do objeto que os irmãos usam para capturar fantasmas?", "Armadilha para fantasmas", listOf("Armadilha para fantasmas", "Prisão de espectros", "Caixa de contenção", "Armadilha de sal")),
        Question("Qual é o nome do caçador que é como um pai para Sam e Dean?", "Bobby Singer", listOf("Bobby Singer", "John Winchester", "Rufus Turner", "Garth Fitzgerald")),
        Question("Qual é o nome da bruxa que é uma amiga e aliada dos irmãos?", "Rowena", listOf("Rowena", "Meg", "Ruby", "Lilith")),
        Question("Qual é o nome do filho de Lucifer e Kelly Kline?", "Jack", listOf("Jack", "Adam", "Sam", "Dean")),
        Question("Quem é o arcanjo que toma o corpo de Adam Milligan?", "Michael", listOf("Michael", "Lucifer", "Gabriel", "Raphael")),
        Question("Qual é o nome da entidade cósmica que é irmã de Deus?", "A Escuridão (Amara)", listOf("A Escuridão (Amara)", "Morte", "Destino", "Pazuzu")),
        Question("Qual é o nome do demônio que se torna aliado dos irmãos após sua libertação do inferno?", "Meg", listOf("Meg", "Ruby", "Crowley", "Azazel")),
        Question("Qual é o nome do pai dos irmãos Winchester?", "John Winchester", listOf("John Winchester", "Bobby Singer", "Chuck Shurley", "Rufus Turner")),
        Question("Qual é o nome do local onde Dean é enviado depois de ser morto por cães do inferno?", "O Purgatório", listOf("O Purgatório", "O Inferno", "O Céu", "A Terra")),
        Question("Qual é o nome do personagem que é um caçador e um hacker?", "Charlie Bradbury", listOf("Charlie Bradbury", "Jo Harvelle", "Ellen Harvelle", "Rowena")),
        Question("Quem é o cavaleiro do apocalipse que é imortal e tem um papel importante na série?", "Morte", listOf("Morte", "Guerra", "Fome", "Peste")),
        Question("Qual é o nome da arma que Dean usa para matar Lúcifer?", "A Lâmina do Arcanjo", listOf("A Lâmina do Arcanjo", "A Faca de Ruby", "A Espada de Miguel", "A Faca de Caçador")),
        Question("Qual é o nome da criatura que consome almas e é conhecida como 'Ceifador'?", "Reaper", listOf("Reaper", "Wendigo", "Ghoul", "Demônio")),
        Question("Qual é o nome da esposa de John Winchester que é ressuscitada na 12ª temporada?", "Mary Winchester", listOf("Mary Winchester", "Ellen Harvelle", "Jo Harvelle", "Rowena")),
        Question("Quem é o personagem que é um caçador de demônios e também um policial?", "Gordon Walker", listOf("Gordon Walker", "Rufus Turner", "Benny Lafitte", "Asa Fox")),
        Question("Qual é o nome da deusa pagã que tenta trazer o apocalipse na 15ª temporada?", "Caronte", listOf("Caronte", "Baba Yaga", "Kali", "Rowena")),
        Question("Qual é o nome do local onde os anjos armazenam suas lâminas?", "O Céu", listOf("O Céu", "O Inferno", "O Purgatório", "O Paraíso")),
        Question("Quem é o personagem que se torna o novo Rei do Inferno após a morte de Crowley?", "Asmodeus", listOf("Asmodeus", "Lucifer", "Dagon", "Azazel"))
    ).shuffled()

    /// Índice da pergunta atual
    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seventeen)

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
            score >= 90 -> "Você salvou pessoas e caçou coisas. Bem vindo a Familia Winchester!"
            score >= 40 && score < 90 -> "Parece que você precisa um caminhão de sal grosso pra afastar essas respostas erradas."
            else -> "Crowley é o rei do inferno, você é o rei dos erros. "
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
