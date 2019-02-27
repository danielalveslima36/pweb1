package br.edu.ifpb.pweb1.model.dao.generic;

import java.beans.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.edu.ifpb.pweb1.model.jdbc.ConnectionFactory;

public abstract class GenericDAO<T extends Entity> {

	protected Connection connection;

	protected List<String> columns;
	protected List<Object> values;
	protected String tableName;
	protected Class<?> entityClass;
	protected List<Field> persistentFields;

	protected static final List<?> EMPTY_LIST = new ArrayList<>(0);
	protected static final String FORMAT_FIND_ALL = "SELECT * FROM %s";
	protected static final String FORMAT_FIND_BY_ID = "SELECT * FROM %s WHERE id = ?";
	protected static final String FORMAT_FIND_BY = "SELECT * FROM %s WHERE %s = ?";
	protected static final String FORMAT_FIND_LAST_ID = "SELECT id FROM %s ORDER BY id DESC LIMIT 1";
	protected static final String FORMAT_INSERT = "INSERT INTO %s (%s) VALUES (%s)";
	protected static final String FORMAT_UPDATE = "UPDATE %s SET %s WHERE %s";
	protected static final String FORMAT_DELETE = "DELETE FROM %s WHERE %s";

	public GenericDAO() {
		this.connection = ConnectionFactory.getInstance().getConnection();
		Type genericSuperClass = getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) genericSuperClass;
		Type typeArgument = parameterizedType.getActualTypeArguments()[0];
		entityClass = (Class<?>) typeArgument;
		Table table = entityClass.getAnnotation(Table.class);
		if (table != null) {
			tableName = table.name();
		}
		loadColumns();
	}

	protected void loadColumns() {
		columns = new ArrayList<>();
		persistentFields = new ArrayList<>();
		Field[] fields = entityClass.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			if (field.getAnnotation(Transient.class) == null && field.getName().equals("serialVersionUID") == false) {
				columns.add(SqlHelper.getColumnName(field));
				persistentFields.add(field);

			}
		}
	}

	public List<T> findAll() {
		List<T> list = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = String.format(FORMAT_FIND_ALL, tableName);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			Object object = null;
			String columnLabel = "";
			// percorre os registros retornados do banco de dados.
			while (resultSet.next()) {
				object = entityClass.newInstance();
				Field idField = entityClass.getSuperclass().getDeclaredField("id");
				idField.setAccessible(true);
				idField.set(object, resultSet.getObject("id"));
				Field persistedField = entityClass.getSuperclass().getDeclaredField("persisted");
				persistedField.setAccessible(true);
				persistedField.set(object, true);
				// percorre cada atributo do objeto.
				for (Field persistentField : persistentFields) {
					columnLabel = SqlHelper.getColumnName(persistentField);
					persistentField.set(object, resultSet.getObject(columnLabel));
				}
				list.add((T) object);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Optional<T> findByField(String field, Object value) {
		Object object = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = String.format("SELECT * FROM %s WHERE %s = ?" , tableName, field, value);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setObject(1, value);
			resultSet = preparedStatement.executeQuery();
			String columnLabel = "";
			while (resultSet.next()) {
				object = entityClass.newInstance();
				Field idField = entityClass.getSuperclass().getDeclaredField("id");
				idField.setAccessible(true);
				idField.set(object, resultSet.getLong("id"));
				Field persistedField = entityClass.getSuperclass().getDeclaredField("persisted");
				persistedField.setAccessible(true);
				persistedField.set(object, true);
				for (Field persistentField : persistentFields) {
					columnLabel = SqlHelper.getColumnName(persistentField);
					persistentField.set(object, resultSet.getObject(columnLabel));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} finally {
			close(resultSet, preparedStatement, connection);
		}
		return Optional.of((T) object);
	}

	public Optional<T> findById(Long id) {
		Object object = null;
		if (id != null) {
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				String sql = String.format(FORMAT_FIND_BY_ID, tableName, id);
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setLong(1, id);
				resultSet = preparedStatement.executeQuery();
				String columnLabel = "";
				while (resultSet.next()) {
					object = entityClass.newInstance();
					Field idField = entityClass.getSuperclass().getDeclaredField("id");
					idField.setAccessible(true);
					idField.set(object, resultSet.getObject("id"));
					Field persistedField = entityClass.getSuperclass().getDeclaredField("persisted");
					persistedField.setAccessible(true);
					persistedField.set(object, true);
					for (Field persistentField : persistentFields) {
						columnLabel = SqlHelper.getColumnName(persistentField);
						persistentField.set(object, resultSet.getObject(columnLabel));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} finally {
				close(resultSet, preparedStatement, connection);
			}
		}
		return Optional.of((T) object);
	}

	public Long findLastId() {
		Long id = 0L;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = String.format(FORMAT_FIND_LAST_ID, tableName);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getLong("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(resultSet, preparedStatement, connection);
		}
		return id;
	}
	
	public long count() {
		String sql = String.format("SELECT count(*) FROM %s", tableName);
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				return result.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public T insert(T entity) throws IllegalArgumentException {
		List<String> columnsWithoutId = this.columns.stream().filter( f -> !f.equals("id")).collect(Collectors.toList());
		PreparedStatement preparedStatement = null;
		String[] valuesArray = new String[columnsWithoutId.size()];
		Arrays.fill(valuesArray, "?");
		String values = String.join(", ", valuesArray);
		String columns = sql(columnsWithoutId);
		String sql = String.format(FORMAT_INSERT, tableName, columns, values);
		int position = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			List<Field> fieldsWithoutId = persistentFields.stream().filter(f -> !f.getName().equals("id")).collect(Collectors.toList());
			for (Field persistentField : fieldsWithoutId) {
				System.out.println(persistentField.getName());
				if (!persistentField.getName().equals("id")) {
					preparedStatement.setObject(++position, persistentField.get(entity));
				}
			}
			preparedStatement.executeUpdate();
			entity.setId(findLastId());
			entity.setPersisted(true);
			return entity;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement, connection);
		}
		return null;
	}
	
	 public T update(T entity) throws IllegalArgumentException {
	      PreparedStatement preparedStatement = null;
	      String[] columnsAndValues = new String[columns.size()];
	      for (int i = 0; i < columns.size(); i++) {
	         columnsAndValues[i] = String.format("%s = ?", columns.get(i));
	      }
	      String sql = String.format(FORMAT_UPDATE, tableName, String.join(", ", columnsAndValues), "id = ?");
	      int position = 0;
	      try {
	         preparedStatement = connection.prepareStatement(sql);
	         for (Field persistentField : persistentFields) {
	            preparedStatement.setObject(++position, persistentField.get(entity));
	         }
	         preparedStatement.setObject(++position, entity.getId());
	         preparedStatement.executeUpdate();
	         entity.setId(findLastId());
	         entity.setPersisted(true);
	         return entity;
	      } catch (IllegalAccessException e) {
	         e.printStackTrace();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(preparedStatement, connection);
	      }
	      return null;
	   }
	   
	   public T save(T entity) throws IllegalArgumentException {
	      if (entity != null) {
	         if (entity.getPersisted() == false) {
	            return this.insert(entity);
	         } else {
	            return this.update(entity);
	         }
	      } else {
	         throw new IllegalArgumentException("ATENÇÃO: A entidade a ser persistida no banco de dados não pode ser nula!");
	      }
	   }
	   
	   public void delete(T entity) throws IllegalArgumentException {
	      PreparedStatement preparedStatement = null;
	      String sql = String.format(FORMAT_DELETE, tableName, "id = ?");
	      try {
	         preparedStatement = connection.prepareStatement(sql);
	         preparedStatement.setObject(1, entity.getId());
	         preparedStatement.executeUpdate();
	         entity.setPersisted(false);
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(preparedStatement, connection);
	      }
	   }

   protected void close(Object o) {
	      if (o != null && o instanceof AutoCloseable) {
	         try {
	            ((AutoCloseable) o).close();
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	      }
	   }

	protected void close(Object... objects) {
		if (objects != null) {
			for (Object o : objects) {
				close(o);
			}
		}
	}

	protected static String sql(List<?> list) {
		String sqlList = "";
		if (list != null && list.isEmpty() == false) {
			sqlList = list.toString();
			sqlList = sqlList.replace("[", "");
			sqlList = sqlList.replace("]", "");
		}
		return sqlList;
	}

	protected void loadValues(T object) {
		values = getValues(object);
	}

	protected List<Object> getValues(T object) {
		List<Object> values = new ArrayList<>();
		for (Field field : persistentFields) {
			try {
				Object value = field.get(object);
				if (value instanceof String) {
					values.add("'" + field.get(object) + "'");
				} else {
					values.add(field.get(object));
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return values;
	}

}
