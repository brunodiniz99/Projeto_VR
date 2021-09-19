package dao;

import conexao.Conexao;
import entidades.Aluno;
import java.util.List;
import javax.persistence.EntityManager;

public class AlunoDao {

    private EntityManager em = new Conexao().getConnection();

    public void salvar(Aluno aluno) {

        try {

            em.getTransaction().begin();
            if (aluno.getCodigo() == null) {
                em.persist(aluno);
            } else {
                em.merge(aluno);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

    }

    public boolean deletar(Integer codigo) {
        boolean isDeletar = true;
        Aluno aluno = new Aluno();
        try {
            aluno = em.find(Aluno.class, codigo);
            em.getTransaction().begin();
            em.remove(aluno);
            em.getTransaction().commit();
        } catch (Exception e) {
            isDeletar = false;
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return isDeletar;
    }

    public Aluno buscar(Integer codigo) {

        Aluno aluno = em.find(Aluno.class, codigo);
        return aluno;

    }

    public Aluno buscarAlunoNome(String nome, String senha) {
        
       Aluno aluno = new Aluno();
       List<Aluno> alunos = getAlunoLogin(nome, senha);
       
           aluno.setCodigo(alunos.stream().filter(a -> a.getCodigo() > Integer.parseInt(senha)).findFirst().get().getCodigo());
           aluno.setNome(alunos.stream().filter(a -> a.getNome().equals(nome)).findFirst().get().getNome());
       
       
       
        return (aluno);

    }

    public List<Aluno> listaAluno() {

        String querry = "from Aluno";
        List<Aluno> alunos = em.createQuery(querry).getResultList();
        return alunos;

    }

    public boolean validarAluno(String nome, String senha) {

       List<Aluno> aluno = getAlunoLogin(nome, senha);
        
        return aluno.size() > 0;
    }
    
    public List<Aluno> getAlunoLogin(String nome, String senha){
                String querry = ""
                + " select "
                + "  0 "
                + " from Aluno "
                + " where "
                + "  nome = :nome "
                + "   and "
                + "  senha = :senha ";

        List<Aluno> aluno = em.createQuery(querry)
                .setParameter("nome", nome)
                .setParameter("senha", senha)
                .getResultList();
        
        return aluno;
    }

}
