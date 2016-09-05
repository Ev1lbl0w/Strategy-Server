package servers;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;

/*
 * The Lobby Server
 *
 * The lobby server is a server that receives all logged users
 * from the login server. It's main function is to queue
 * players to find matches and allow the creation of groups.
 * Later, it will also allow users to edit their profile.
 *
 */
public class LobbyServerAppState extends AbstractAppState{
    
@Override
    public void initialize(AppStateManager stateManager, Application app) {
        
        
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
