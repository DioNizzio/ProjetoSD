package edu.ufp.inf.sd.rmi.project.database;

import edu.ufp.inf.sd.rmi.project.server.lobby.Lobby;
import edu.ufp.inf.sd.rmi.project.server.gamesession.GameSessionRI;
import edu.ufp.inf.sd.rmi.project.variables.User;

import java.util.*;


public class DB {

    private final ArrayList<User> users = new ArrayList<>();
    private final Map<User, GameSessionRI> sessions = new HashMap<>();
    private final List<Lobby> gameLobbies = Collections.synchronizedList(new ArrayList<>());
    
    public DB() {
        users.add(new User("guest", "ufp"));
    }

    public List<Lobby> getGameLobbies() {
        return gameLobbies;
    }

    public void register(String username, String password) {
        if (!userExists(username, password)) {
            User user = new User(username, password);
            users.add(user);
        } else {
            System.out.println("error");
        }
    }

    public boolean userExists(String username, String password) {
        for (User users : this.users) {
            if (username.equals(users.getUsername()) && password.equals(users.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public User getUser(String username,String password){
        for (User users : this.users) {
            if (users.getUsername().equals(username) && users.getPassword().equals(password)) {
                return users;
            }
        }
        return null;
    }

    public void addSession(User user, GameSessionRI session) {
        this.sessions.put(user, session);
    }

    public void removeSession(User user) {
        this.sessions.remove(user);
    }

    public GameSessionRI getSession(User user) {
        return this.sessions.get(user);
    }

    public boolean hasSession(User user) {
        return this.sessions.containsKey(user);
    }

    public ArrayList<User> getUsers(){
        return this.users;
    }

    public Map<User, GameSessionRI> getSessions() {
        return sessions;
    }

    public void addLobby(Lobby lobby){
        gameLobbies.add(lobby);
    }

    public void updateLobby(int index, Lobby lobby){
        gameLobbies.set(index,lobby);
    }



}