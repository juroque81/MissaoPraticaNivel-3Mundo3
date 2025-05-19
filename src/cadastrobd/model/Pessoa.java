package cadastrobd.model;

public class Pessoa {
    protected int id;
    protected String nome;
    protected String endereco;
    protected String telefone;
    protected String email;
    protected char tipo; // 'F' ou 'J'
    protected String cpf;
    protected String cnpj;

    public Pessoa() {
    }

    public Pessoa(int id, String nome, String endereco, String telefone, String email, char tipo, String cpf, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.tipo = tipo;
        this.cpf = cpf;
        this.cnpj = cnpj;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void exibir() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Endereco: " + endereco);
        System.out.println("Telefone: " + telefone);
        System.out.println("Email: " + email);
        System.out.println("Tipo: " + tipo);
        if (tipo == 'F') {
            System.out.println("CPF: " + cpf);
        } else if (tipo == 'J') {
            System.out.println("CNPJ: " + cnpj);
        }
    }
}