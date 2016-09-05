package servers;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.network.Filters;
import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.network.serializing.Serializer;
import encryption.Encryptor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import messages.login.LoginAttemptMessage;
import messages.login.LoginResultMessage;
import messages.login.NewAccountMessage;
import util.Fields;

/*
 * The Login Server
 *
 * The Login Server is a server that takes care of incoming
 * login requests. It accesses a file of encrypted passwords,
 * and it's the main server to work with encryption.
 *
 */
public class LoginServerAppState extends AbstractAppState implements MessageListener<HostedConnection> {
    
    // Variables
    // Server
    private Server loginServer;
    // Accounts
    private LinkedHashMap<String, String> accounts;
    
@Override
    public void initialize(AppStateManager stateManager, Application app) {
        // Checks if any file exists already
        accounts = new LinkedHashMap<String, String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"));
            while(reader.ready()) {
                // If a file exists, it reads the Strings and adds them to the Map
                String username = reader.readLine();
                String password = reader.readLine();
                accounts.put(username, password);
            }
            reader.close();
        } catch(FileNotFoundException flne) {
            File file = new File("accounts.txt");
            try {
                file.createNewFile();
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        
        // Starts the server
        try {
            loginServer = Network.createServer(Fields.LOGIN_SERVER_PORT);
            Serializer.registerClasses(NewAccountMessage.class, LoginAttemptMessage.class, LoginResultMessage.class);
            loginServer.start();
            loginServer.addMessageListener(this);
            System.out.println("Login server operational!");
        } catch(IOException e) {
            System.err.println("Login server could not initialize!");
            e.printStackTrace();
        }
        
        // Continues to initialize
        super.initialize(stateManager, app);
    }
    
    @Override
    public void messageReceived(HostedConnection source, Message message) {
        if(message instanceof NewAccountMessage) {
            NewAccountMessage newAccountMessage = (NewAccountMessage) message;
            boolean wasKicked = false;
            // The client is trying to create a new account.
            // Firstly, check if the username has been taken.
            for(String username : accounts.keySet()) {
                if(username.equals(newAccountMessage.getUsername())) {
                    // If yes, the server sends the result, kicks him and stops the code
                    source.close("Username " + newAccountMessage.getUsername() + " has already been taken!");
                    wasKicked = true;
                    break;
                }
            }
            // If it wasn't kicked, then it registers him
            // TODO: Check if username/password are valid too
            if(!wasKicked) {
                String password = Encryptor.encrypt(newAccountMessage.getPassword());
                accounts.put(newAccountMessage.getUsername(), password);
                System.out.println("A new account by the name of " + newAccountMessage.getUsername() + " was created!");
            }
        } else if(message instanceof LoginAttemptMessage) {
            LoginAttemptMessage loginMessage = (LoginAttemptMessage) message;
            if(accounts.containsKey(loginMessage.getUsername())) {
                LoginResultMessage loginResult = new LoginResultMessage(accounts.get(loginMessage.getUsername()).equals(loginMessage.getPassword()) ? 1 : 3);
                loginResult.setReliable(true);
                loginServer.broadcast(Filters.in(source), loginResult);
                if(loginResult.getResult() == 1) {
                    System.out.println("Accepted connection from " + source.getAddress() + " by the name of " + loginMessage.getUsername());
                } else {
                    System.out.println("Rejected connection from " + source.getAddress() + " by the name of " + loginMessage.getUsername() + ". Passwords didn't match.");
                }
            } else {
                LoginResultMessage loginResult = new LoginResultMessage(2);
                loginResult.setReliable(true);
                loginServer.broadcast(Filters.in(source), loginResult);
                System.out.println("Rejected connection from " + source.getAddress() + " by the name of " + loginMessage.getUsername() + ". Username doesn't exist.");
            }
        }
    }

    @Override
    public void update(float tpf) {
        
    }

    @Override
    public void cleanup() {
        // Writes the new data to the text file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt"));
            for(String name : accounts.keySet()) {
                writer.write(name);
                writer.newLine();
                writer.write(accounts.get(name));
                writer.newLine();
            }
            writer.close();
        } catch(IOException ioe) {
            
        }
        
        // Continues to cleanup
        super.cleanup();
    }
}