package cadastrobd.model;

public class PessoaFisica extends Pessoa {

    public PessoaFisica() {
        this.tipo = 'F';
    }

    public PessoaFisica(int id, String nome, String endereco, String telefone, String email, String cpf) {
        super(id, nome, endereco, telefone, email, 'F', cpf, null);
    }

    @Override
    public void exibir() {
        super.exibir(); // já está exibindo o CPF com base no tipo
    }
}
