package teste;

import dao.AlunoDao;
import dao.CursoDao;
import entidades.Aluno;
import entidades.Curso;
import java.util.ArrayList;
import java.util.List;

public class Playground {
    public static void main(String... play){
        
        Aluno aluno = new Aluno();
        AlunoDao alunoDao = new AlunoDao(); 
        Curso curso = new Curso();
        List<Curso> cursos = new ArrayList<>();
        CursoDao cursoDao = new CursoDao();
        
        curso.setNome("ingles");
        curso.setEmenta("alou");

        aluno.setNome("bruno");
        aluno.setSenha("1234");

       
        aluno.setCursos(cursos);
        
        cursos.add(curso);
        cursoDao.salvar(curso);
        alunoDao.salvar(aluno);
        List<Aluno> alunos = alunoDao.listaAluno();
        System.out.println(alunos);
    }

        

}