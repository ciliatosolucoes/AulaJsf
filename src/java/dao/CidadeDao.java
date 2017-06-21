/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Cidade;

/**
 *
 * @author adria
 */
@Stateless
public class CidadeDao {
    
    @PersistenceContext(unitName = "AulaJsfPU")
    private EntityManager em;

    public boolean gravar(Cidade cidade) {
        boolean sucesso = false;
        try {
            em.merge(cidade);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sucesso;
    }

    public boolean remover(Cidade cidade) {
        boolean sucesso = false;
        try {
            cidade = em.find(Cidade.class, cidade.getID());
            em.remove(cidade);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sucesso;
    }

    public List<Cidade> listar() {
        List<Cidade> cidades = null;
        try {
            Query query = em.createQuery("Select c from Cidade c");
            cidades = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cidades;
    }
    
}
