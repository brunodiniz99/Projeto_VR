package dao;

import conexao.Conexao;
import entidades.Curso;
import javax.persistence.EntityManager;

public class CursoDao {
    
        public void salvar(Curso curso) {
        EntityManager em = new Conexao().getConnection();
        try {
            em.getTransaction().begin();
            em.persist(curso);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

    }
        
        public void deletar(Integer codigo) {
        EntityManager em = new Conexao().getConnection();
        Curso curso = new Curso();
        try {
            curso = em.find(Curso.class, codigo);
            em.getTransaction().begin();
            em.remove(curso);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        
    }
    
}
