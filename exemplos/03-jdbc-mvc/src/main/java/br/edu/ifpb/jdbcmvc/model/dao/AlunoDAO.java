package br.edu.ifpb.jdbcmvc.model.dao;

import java.util.List;
import java.util.Optional;

import br.edu.ifpb.jdbcmvc.model.domain.Aluno;

public interface AlunoDAO {

	public List<Aluno> listarAlunos() throws DataAccessException;
	
	public Optional<Aluno> recuperarAluno(String matricula) throws DataAccessException;
	
	public void salvarAluno(Aluno aluno) throws DataAccessException;
	
	public void removerAluno(String matricula) throws DataAccessException;
	
	public Long contarAlunos() throws DataAccessException;
	
}
