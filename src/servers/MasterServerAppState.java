package servers;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import java.util.ArrayList;

/*
 * The Master Server
 *
 * The master server is used to control all the types of servers.
 * In the Main class, this is the only directly attached AppState.
 *
 */

// TODO: This server really must be an AppState?
public class MasterServerAppState extends AbstractAppState{
    
    // Variables
    // Servers
    private LoginServerAppState loginServer;
    private LobbyServerAppState lobbyServer;
    private ArrayList<GameServerAppState> gameServers; // This is a list because there is more than 1 game server, of course xD
    // Misc
    private AppStateManager stateManager; // Used to attach/dettach the servers.
    
@Override
    public void initialize(AppStateManager stateManager, Application app) {
        // Creates the servers
        loginServer = new LoginServerAppState();
        lobbyServer = new LobbyServerAppState();
        // TODO: 10 is the number of maximum servers. Define a constant later for it.
        gameServers = new ArrayList<GameServerAppState>(10);
        
        // Creates the stateManager and attaches the servers
        this.stateManager = stateManager;
        this.stateManager.attach(loginServer);
        this.stateManager.attach(lobbyServer);
        
        // Continues to initialize
        super.initialize(stateManager, app);
    }

    @Override
    public void update(float tpf) {
        
    }

    @Override
    public void cleanup() {
        
        
        // Continues to cleanup
        super.cleanup();
    }
}
