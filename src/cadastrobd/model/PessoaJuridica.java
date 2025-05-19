package cadastrobd.model;

public class PessoaJuridica extends Pessoa {

    public PessoaJuridica() {
        this.tipo = 'J';
    }

    public PessoaJuridica(int id, String nome, String endereco, String telefone, String email, String cnpj) {
        super(id, nome, endereco, telefone, email, 'J', null, cnpj);
    }

    @Override
    public void exibir() {
        super.exibir(); // já está exibindo o CNPJ com base no tipo
    }
}
