package org.example;

import org.example.dao.AlunoDAO;
import org.example.dao.CursoDAO;
import org.example.model.Alunos;
import org.example.model.Area;
import org.example.model.Cursos;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AlunoForm {
    public JPanel mainPanel;
    private JTextField NomeTx, TelefoneTx, SexoTx, NomeCursoTx, SiglaCursoTx, CursoTx;
    private JCheckBox simMaiorIdade, naoMaiorIdade;
    private JButton Inserir, Atualizar, Excluir, Listar, InserirCurso, AtualizarCurso, ExcluirCurso, ListarCurso;
    private JTable AlunosTable, CursoTable;
    private JComboBox<Area> AreaBox;
    private JLabel Funcao, MaiorIdade, Curso, Nome, Telefone, Sexo, NomeCurso, SiglaCurso, Area;

    private AlunoDAO alunoDAO = new AlunoDAO();
    private CursoDAO cursoDAO = new CursoDAO();
    private Long matriculaSelecionada;
    private Long cursoSelecionado;
    private void limparCampos() {
        NomeTx.setText("");
        SexoTx.setText("");
        TelefoneTx.setText("");
        CursoTx.setText("");
        simMaiorIdade.setSelected(false);
        naoMaiorIdade.setSelected(false);
        matriculaSelecionada = null;
        AlunosTable.clearSelection();
    }

    private void limparCamposCurso() {
        NomeCursoTx.setText("");
        SiglaCursoTx.setText("");
        AreaBox.setSelectedIndex(0);
        cursoSelecionado = null;
        CursoTable.clearSelection();
    }

    public AlunoForm() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Matrícula", "Nome", "Telefone", "Curso", "Maioridade"}, 0);
        AlunosTable.setModel(model);

        DefaultTableModel cursoModel = new DefaultTableModel(new String[]{"Codigo", "Nome do Curso", "Sigla", "Área"}, 0);
        CursoTable.setModel(cursoModel);
        AreaBox.addItem(org.example.model.Area.humanas);
        AreaBox.addItem(org.example.model.Area.biológicas);
        AreaBox.addItem(org.example.model.Area.exatas);
        AreaBox.addItem(org.example.model.Area.artes);
        limparCamposCurso();

        AlunosTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = AlunosTable.getSelectedRow();
                if (selectedRow >= 0) {
                    matriculaSelecionada = (Long) AlunosTable.getValueAt(selectedRow, 0);
                    NomeTx.setText((String) AlunosTable.getValueAt(selectedRow, 1));
                    TelefoneTx.setText((String) AlunosTable.getValueAt(selectedRow, 2));
                    CursoTx.setText((String) AlunosTable.getValueAt(selectedRow, 3));
                    boolean isMaiorIdade = "Sim".equals(AlunosTable.getValueAt(selectedRow, 4));
                    simMaiorIdade.setSelected(isMaiorIdade);
                    naoMaiorIdade.setSelected(!isMaiorIdade);
                }
            }
        });

        Inserir.addActionListener(e -> {
            Alunos aluno = new Alunos();
            aluno.setNome(NomeTx.getText());
            aluno.setSexo(SexoTx.getText());
            aluno.setTelefone(TelefoneTx.getText());
            aluno.setCurso(CursoTx.getText());
            aluno.setMaioridade(simMaiorIdade.isSelected());

            alunoDAO.create(aluno);
            JOptionPane.showMessageDialog(null, "Aluno inserido com sucesso!");
            listarAlunos();
            limparCampos();
        });

        Atualizar.addActionListener(e -> {
            if (matriculaSelecionada != null) {
                Alunos aluno = new Alunos();
                aluno.setMatricula(matriculaSelecionada);
                aluno.setNome(NomeTx.getText());
                aluno.setSexo(SexoTx.getText());
                aluno.setTelefone(TelefoneTx.getText());
                aluno.setCurso(CursoTx.getText());
                aluno.setMaioridade(simMaiorIdade.isSelected());

                alunoDAO.update(aluno);
                JOptionPane.showMessageDialog(null, "Aluno atualizado com sucesso!");
                listarAlunos();
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um aluno para atualizar.");
            }
        });

        Excluir.addActionListener(e -> {
            if (matriculaSelecionada != null) {
                Alunos aluno = new Alunos();
                aluno.setMatricula(matriculaSelecionada);
                alunoDAO.delete(aluno);
                JOptionPane.showMessageDialog(null, "Aluno excluído com sucesso!");
                listarAlunos();
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um aluno para excluir.");
            }
        });

        Listar.addActionListener(e -> listarAlunos());

        // Ações para Cursos
        InserirCurso.addActionListener(e -> {
            Cursos curso = new Cursos();
            curso.setNome(NomeCursoTx.getText());
            curso.setSigla(SiglaCursoTx.getText());
            curso.setArea((Area) AreaBox.getSelectedItem());

            cursoDAO.create(curso);
            JOptionPane.showMessageDialog(null, "Curso inserido com sucesso!");
            listarCursos();
            limparCamposCurso();
        });

        AtualizarCurso.addActionListener(e -> {
            if (cursoSelecionado != null) {
                Cursos curso = new Cursos();
                curso.setCodigo(cursoSelecionado);
                curso.setNome(NomeCursoTx.getText());
                curso.setSigla(SiglaCursoTx.getText());
                curso.setArea((Area) AreaBox.getSelectedItem());

                cursoDAO.update(curso);
                JOptionPane.showMessageDialog(null, "Curso atualizado com sucesso!");
                listarCursos();
                limparCamposCurso();
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um curso para atualizar.");
            }
        });

        ExcluirCurso.addActionListener(e -> {
            if (cursoSelecionado != null) {
                Cursos curso = new Cursos();
                curso.setCodigo(cursoSelecionado);
                cursoDAO.delete(curso);
                JOptionPane.showMessageDialog(null, "Curso excluído com sucesso!");
                listarCursos();
                limparCamposCurso();
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um curso para excluir.");
            }
        });

        ListarCurso.addActionListener(e -> listarCursos());

        CursoTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = CursoTable.getSelectedRow();
                if (selectedRow >= 0) {
                    cursoSelecionado = (Long) CursoTable.getValueAt(selectedRow, 0);
                    NomeCursoTx.setText((String) CursoTable.getValueAt(selectedRow, 1));
                    SiglaCursoTx.setText((String) CursoTable.getValueAt(selectedRow, 2));
                    AreaBox.setSelectedItem(CursoTable.getValueAt(selectedRow, 3));
                }
            }
        });
    }

    private void listarAlunos() {
        DefaultTableModel model = (DefaultTableModel) AlunosTable.getModel();
        model.setRowCount(0);

        List<Alunos> alunosList = alunoDAO.findAll();

        for (Alunos aluno : alunosList) {
            model.addRow(new Object[]{
                    aluno.getMatricula(),
                    aluno.getNome(),
                    aluno.getTelefone(),
                    aluno.getCurso(),
                    aluno.isMaioridade() ? "Sim" : "Não"
            });
        }
    }

    private void listarCursos() {
        DefaultTableModel model = (DefaultTableModel) CursoTable.getModel();
        model.setRowCount(0);

        List<Cursos> cursosList = cursoDAO.findAll();

        for (Cursos curso : cursosList) {
            model.addRow(new Object[]{
                    curso.getCodigo(),
                    curso.getNome(),
                    curso.getSigla(),
                    curso.getArea()
            });
        }
    }
}