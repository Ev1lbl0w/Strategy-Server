package main;

import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;
import com.jme3.system.JmeContext;
import servers.MasterServerAppState;

public class Main extends SimpleApplication {
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start(JmeContext.Type.Headless);
    }

    @Override
    public void simpleInitApp() {
        MasterServerAppState masterServer = new MasterServerAppState();
        stateManager.attach(masterServer);
    }
    
    @Override
    public void simpleUpdate(float tpf) {
        
    }
    
    @Override
    public void simpleRender(RenderManager rm) {
        
    }
}
