package br.edu.ifpb.jdbcmvc.model.dao;

public class DataAccessException extends Exception {
	public DataAccessException(String message) {
		super(message);
	}
}