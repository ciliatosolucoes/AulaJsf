/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.PessoaDao;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.Pessoa;

/**
 *
 * @author cleberson.ciliato
 */
@Named(value = "pessoaBean")
@SessionScoped
public class PessoaBean implements Serializable {

    /**
     * Creates a new instance of PessoaBean
     */
    
    @EJB
    private PessoaDao pDao;
    
    private Pessoa pessoa = new Pessoa();
    private List<Pessoa> pessoas;
    private boolean telaInserir=false;
    
    public PessoaBean() {
    }
    
    public void posicaoInicial(){
        pessoa = new Pessoa();
        telaInserir=false;
    }

    
    public void selecionar(ActionEvent evento) {
        Long codigo = (Long) evento.
        getComponent().getAttributes().get("codigo");
        pessoa = pDao.selecionar(codigo);
        telaInserir=true;
    }
    
    public void novo(){
        pessoa = new Pessoa();
        telaInserir=true;
    }
 
    
    
    public void gravar() {
        FacesContext context = 
                FacesContext.getCurrentInstance();
//        Cidade city = cidDao.selecionar(idCidade);
//        pessoa.setIdCidade(city);
        boolean resultado = pDao.gravar(pessoa);

        if (resultado) {
            pessoa = new Pessoa();
            telaInserir=false;
            context.addMessage(null, new 
         FacesMessage("Pessoa gravada com sucesso"));
        } else {
            context.addMessage(null, new 
         FacesMessage("Falha ao gravar a pessoa!"));
        }
    }

    
     public void remover() {
        FacesContext context = FacesContext.
                getCurrentInstance();
        boolean resultado = pDao.remover(pessoa);

        if (resultado) {
            pessoa = new Pessoa();
            telaInserir=false;
            context.addMessage(null, new FacesMessage
                    ("Pessoa removida com sucesso"));
        } else {
            context.addMessage(null, new FacesMessage
                    ("Falha ao remover a pessoa!"));
        }
    }
    
    public PessoaDao getpDao() {
        return pDao;
    }

    public void setpDao(PessoaDao pDao) {
        this.pDao = pDao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
       pessoas =  pDao.listar();
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public boolean isTelaInserir() {
        return telaInserir;
    }

    public void setTelaInserir(boolean telaInserir) {
        this.telaInserir = telaInserir;
    }
 
    
    
    
    
}
