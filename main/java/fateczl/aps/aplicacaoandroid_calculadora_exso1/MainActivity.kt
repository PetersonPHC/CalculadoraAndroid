package fateczl.aps.aplicacaoandroid_calculadora_exso1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView



class MainActivity : AppCompatActivity() {

    private lateinit var exibeNumeros: TextView
    private lateinit var exibeResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        exibeNumeros = findViewById(R.id.exibe_numeros)
        exibeResultado = findViewById(R.id.exibe_resultado)

        val btnNumero0: Button = findViewById(R.id.number0)
        val btnNumero00: Button = findViewById(R.id.number00)
        val btnNumero1: Button = findViewById(R.id.number1)
        val btnNumero2: Button = findViewById(R.id.number2)
        val btnNumero3: Button = findViewById(R.id.number3)
        val btnNumero4: Button = findViewById(R.id.number4)
        val btnNumero5: Button = findViewById(R.id.number5)
        val btnNumero6: Button = findViewById(R.id.number6)
        val btnNumero7: Button = findViewById(R.id.number7)
        val btnNumero8: Button = findViewById(R.id.number8)
        val btnNumero9: Button = findViewById(R.id.number9)
        val btnSoma: Button = findViewById(R.id.adicao)
        val btnSubtracao: Button = findViewById(R.id.subtracao)
        val btnMultiplicacao: Button = findViewById(R.id.multiplicacao)
        val btnDivisao: Button = findViewById(R.id.divisao)
        val btnIgualdade: Button = findViewById(R.id.igualdade)

        val onClickListener = { view: android.view.View ->
            val button = view as Button
            val buttonText = button.text.toString()
            val currentText = exibeNumeros.text.toString()

            if (currentText.isNotEmpty() && isOperator(buttonText)) {
                if (!isOperator(currentText.last().toString())) {
                    exibeNumeros.text = currentText + buttonText
                }
            } else {
                exibeNumeros.text = currentText + buttonText
            }
        }

        btnIgualdade.setOnClickListener {
            calcularResultado()
        }

        btnNumero0.setOnClickListener(onClickListener)
        btnNumero00.setOnClickListener(onClickListener)
        btnNumero1.setOnClickListener(onClickListener)
        btnNumero2.setOnClickListener(onClickListener)
        btnNumero3.setOnClickListener(onClickListener)
        btnNumero4.setOnClickListener(onClickListener)
        btnNumero5.setOnClickListener(onClickListener)
        btnNumero6.setOnClickListener(onClickListener)
        btnNumero7.setOnClickListener(onClickListener)
        btnNumero8.setOnClickListener(onClickListener)
        btnNumero9.setOnClickListener(onClickListener)
        btnSoma.setOnClickListener(onClickListener)
        btnSubtracao.setOnClickListener(onClickListener)
        btnMultiplicacao.setOnClickListener(onClickListener)
        btnDivisao.setOnClickListener(onClickListener)
    }

    private fun isOperator(text: String): Boolean {
        return text == "+" || text == "-" || text == "×" || text == "÷"
    }

    private fun calcularResultado() {
        val expressao = exibeNumeros.text.toString()

        try {
            if (expressao.isNotEmpty()) {
                println("Expressão original: $expressao")
                val expressaoLimpa = expressao.replace("×", "*").replace("÷", "/")
                println("Expressão após limpeza: $expressaoLimpa")
                val tokens = tokenize(expressaoLimpa)
                println("Tokens: $tokens")

                val resultado = evaluate(tokens)
                exibeResultado.text = resultado.toString()

                exibeNumeros.text = " "//Limpa o Campo 'exibe_numeros'

                println("Resultado depois do cálculo: $resultado")
            } else {
                // Se a expressão estiver vazia, exiba zero ou uma mensagem apropriada
                exibeResultado.text = "0"
            }
        } catch (e: Exception) {
            // Se ocorrer um erro ao avaliar a expressão, você pode tratar aqui
            exibeResultado.text = "Erro"
        }
    }



    private fun eval(expressao: String): Double {
        try {
            val expressaoLimpa = expressao.replace("×", "*").replace("÷", "/")
            val tokens = tokenize(expressaoLimpa)
            val resultado = evaluate(tokens)
            return resultado
        } catch (e: Exception) {
            // Se ocorrer um erro ao avaliar a expressão, você pode tratar aqui
            return Double.NaN
        }
    }

    private fun tokenize(expressao: String): List<String> {
        val tokens = mutableListOf<String>()
        var numAtual = StringBuilder()

        for (char in expressao) {
            when {
                char.isDigit() || char == '.' -> numAtual.append(char)
                char.isWhitespace() -> {
                    if (numAtual.isNotEmpty()) {
                        tokens.add(numAtual.toString())
                        numAtual.clear()
                    }
                }
                else -> {
                    if (numAtual.isNotEmpty()) {
                        tokens.add(numAtual.toString())
                        numAtual.clear()
                    }
                    tokens.add(char.toString())
                }
            }
        }

        if (numAtual.isNotEmpty()) {
            tokens.add(numAtual.toString())
        }

        return tokens
    }

    private fun evaluate(tokens: List<String>): Double {
        println(tokens[0] +" "+ tokens[1] +" "+ tokens[2])
        val primeiroValor: Double = tokens[0].toDouble();
        val segundoValor: Double = tokens[2].toDouble();
        val resultado: Double

        if(tokens[1] == "+"){
            resultado = primeiroValor + segundoValor;
        }else if (tokens[1] == "-"){
            resultado = primeiroValor - segundoValor;
        }else if(tokens[1] == "*"){
            resultado = primeiroValor * segundoValor;
        }else{
            resultado = primeiroValor / segundoValor;
        }

        return resultado;
    }

}
