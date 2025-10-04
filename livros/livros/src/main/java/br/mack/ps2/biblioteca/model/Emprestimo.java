package br.mack.ps2.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

@Entity
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idLivro;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRetirada;

    public Emprestimo() {}

    public Emprestimo(Long id, Long idLivro, Date dataRetirada){
        this.id = id;
        this.idLivro = idLivro;
        this.dataRetirada = dataRetirada;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getIdLivro(){
        return this.idLivro;
    }

    public void setIdLivro(Long idLivro){
        this.idLivro = idLivro;
    }

    public Date getDataRetirada(){
        return this.dataRetirada;
    }

    public void setDataRetirada(Date dataRetirada){
        this.dataRetirada = dataRetirada;
    }
}
