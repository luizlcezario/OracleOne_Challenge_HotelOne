package src.models;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Hospedes {
    private Integer id;
    private  String nome;

    private String sobreNome;

    private Date dataNascimento;

    private String nascionalidade;

    private String telefone;

    private List<Reservas> reservas;

    public Hospedes() {
    }

    public Hospedes(Integer id, String nome, String sobreNome, Date dataNascimento, String nascionalidade, String telefone, List<Reservas> reservas) {
        this.id = id;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.dataNascimento = dataNascimento;
        this.nascionalidade = nascionalidade;
        this.telefone = telefone;
        this.reservas = reservas;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNascionalidade() {
        return nascionalidade;
    }

    public void setNascionalidade(String nascionalidade) {
        this.nascionalidade = nascionalidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Reservas> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reservas> reservas) {
        this.reservas = reservas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hospedes)) return false;
        Hospedes hospedes = (Hospedes) o;
        return Objects.equals(id, hospedes.id) && Objects.equals(nome, hospedes.nome) && Objects.equals(sobreNome, hospedes.sobreNome) && Objects.equals(dataNascimento, hospedes.dataNascimento) && Objects.equals(nascionalidade, hospedes.nascionalidade) && Objects.equals(telefone, hospedes.telefone) && Objects.equals(reservas, hospedes.reservas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, sobreNome, dataNascimento, nascionalidade, telefone, reservas);
    }

}
