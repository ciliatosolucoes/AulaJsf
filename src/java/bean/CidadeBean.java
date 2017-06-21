/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.CidadeDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Cidade;
import model.Uf;

/**
 *
 * @author adria
 */

@Named(value = "cidadeBean")
@SessionScoped

public class CidadeBean implements Serializable {
    
    @EJB
    private CidadeDao cDao;
    
    private Cidade cidade = new Cidade();
    private List<Cidade> cidades;
    private boolean telaInserir = false;
    
    private List<Uf> ufs;
    private Uf uf;
    
    public List<Uf> getUfs() {
        ufs = new ArrayList<>();
        
        uf = new Uf();
        uf.setSigla("MT");
        uf.setDescricao("Mato Grosso");
        ufs.add(uf);
        
        uf = new Uf();
        uf.setSigla("MS");
        uf.setDescricao("Mato Grosso do Sul");
        ufs.add(uf);
        
        uf = new Uf();
        uf.setSigla("SP");
        uf.setDescricao("Sao Paulo");
        ufs.add(uf);
        
        uf = new Uf();
        uf.setSigla("GO");
        uf.setDescricao("Goias");
        ufs.add(uf);
        
        uf = new Uf();
        uf.setSigla("PR");
        uf.setDescricao("Parana");
        ufs.add(uf);
        
        return ufs;
    }
    

    
    public CidadeBean() {

    }
     
    
    
    public void novo() {
        cidade = new Cidade();
        telaInserir = true;
    }

    public void gravar() {
        FacesContext context = FacesContext.getCurrentInstance();

        boolean resultado = cDao.gravar(cidade);
        if (resultado) {
            cidade = new Cidade();
            telaInserir = false;
            context.addMessage(null,
                    new FacesMessage("Cidade gravada com sucesso!"));
        } else {
            context.addMessage(null,
                    new FacesMessage("Falha ao gravar Cidade!"));
        }
    }
    
    public void posicaoInicial(){
        cidade = new Cidade();
        telaInserir = false;
    }

    public CidadeDao getcDao() {
        return cDao;
    }

    public void setcDao(CidadeDao cDao) {
        this.cDao = cDao;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getCidades() {
        cidades = cDao.listar();
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public boolean isTelaInserir() {
        return telaInserir;
    }

    public void setTelaInserir(boolean telaInserir) {
        this.telaInserir = telaInserir;
    }

    //public void setUfs(List<Uf> ufs) {
     //   this.ufs = ufs;
   // }
    
}
