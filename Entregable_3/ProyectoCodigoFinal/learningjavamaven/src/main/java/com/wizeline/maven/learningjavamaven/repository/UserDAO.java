package com.wizeline.maven.learningjavamaven.repository;

public interface UserDAO {
    public String createUser(String user, String password);
    public String login(String user, String password);
}
