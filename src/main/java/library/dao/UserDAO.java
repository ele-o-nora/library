package library.dao;

import library.models.User;

public interface UserDAO {
	User getUserByUsername(String username);
}
