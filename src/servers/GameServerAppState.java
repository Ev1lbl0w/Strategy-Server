package servers;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;

/*
 * The Game Server
 *
 * The game server is the most important server. It receives the
 * 2 chosen players from the lobby server, and starts a match.
 * It's the server that receives the most bandwidth, so many
 * improvements should be performed. It will also constantly
 * update and look for hackers. Therefore, this is the most
 * expensive and used server.
 *
 */
public class GameServerAppState extends AbstractAppState{
    
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
