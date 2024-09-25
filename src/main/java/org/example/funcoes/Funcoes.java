package org.example.funcoes;

import org.example.model.Alunos;

import java.util.List;

public class Funcoes {
    public Funcoes(){}
    public static void print(Alunos alunos) {
        System.err.println("Nome: " + alunos.getNome());
        System.err.println("Matricula: " + alunos.getMatricula());
        System.err.println("Curso: " + alunos.getCurso());
        System.err.println(alunos.isMaioridade()?"Maior idade":"Menor idade");
        System.err.println("Sexo: " + alunos.getSexo());
        System.err.println("Telefone: " + alunos.getTelefone());
        System.out.println("=====================================================");
    }

    public static void printList(List<Alunos> lista) {
        for (Alunos aluno : lista){
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Matricula: " + aluno.getMatricula());
            System.out.println("Curso: " + aluno.getCurso());
            System.out.println(aluno.isMaioridade() ? "Maior idade" : "Menor idade");
            System.out.println("Sexo: " + aluno.getSexo());
            System.out.println("Telefone: " + aluno.getTelefone());
            System.out.println("=====================================================");
        }
    }
}
