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
import model.Pessoa;

/**
 *
 * @author cleberson.ciliato
 */
@Stateless
public class PessoaDao {

    @PersistenceContext(unitName = "AulaJsfPU")
    private EntityManager em;

    public boolean gravar(Pessoa pessoa) {
        boolean sucesso = false;
        try {
            em.merge(pessoa);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sucesso;
    }

    public Pessoa selecionar(Long codigo) {
        Pessoa pessoa = null;
        try {
            pessoa = em.find(Pessoa.class, codigo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pessoa;
    }

    public List<Pessoa> listar() {
        List<Pessoa> clientes = null;
        try {
            Query query = em.createQuery("Select p from Pessoa p");
            clientes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clientes;
    }

    public boolean remover(Pessoa pessoa) {
        boolean sucesso = false;
        try {
            pessoa = em.find(Pessoa.class,
                    pessoa.getId());
            em.remove(pessoa);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sucesso;
    }

}
