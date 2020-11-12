package livrovotlin.com.listadecompras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btn_principal_adicionar

class MainActivity : AppCompatActivity() {
    val utils = Utils()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //liste de contatos se eu quiser passar uma lsita, prédefinida é só adicionar um terceiro parametro para o ArrayAdapter
        //var contatos = mutableListOf("Mariana", "João", "Francisco", "Vitória")

        //Criação do adaptador passando a lista
        //val produtosAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)

        val produtosAdapter =ProdutoAdapter(this)

        //defindo o adaptador na lista
        list_view_produtos.adapter = produtosAdapter
        btn_principal_adicionar.setOnClickListener{
            //Criando uma Intent explicita
            val intent = Intent(this, CadastroActivity::class.java)

            //Iniciando a Intent
            startActivity(intent)

        }


        list_view_produtos.setOnItemLongClickListener{
            adapterView: AdapterView<*>?, view: View?, position: Int, id: Long ->


            //buscando o item clicado
            val item = produtosAdapter.getItem(position)

            //removendo o ítem clicado da lista
            produtosAdapter.remove(item)

            //retorno indicando que o clicque foi realizado com sucesso
            true
        }
    }

    override fun onResume() {
        super.onResume()
        val adapter = list_view_produtos.adapter as ProdutoAdapter

        adapter.clear()
        adapter.addAll(utils.produtosGlobal)
    }
}