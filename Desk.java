
/**
 * Desk implementation for Pawffice, will be an interactable object 
 * that gives the user the ability to take notes.
 * 
 * @author Team 10, the Bug Busters
 * @version Spring, 2026
 */

public class Desk extends RoomObject implements Runnable {
    
    private static String savedNotes = "";

    public Desk(){

    }

    public boolean isClicked(int mouseX, int mouseY, int cameraX){
        return false;
    }

    public void run(){
        
    }

    //I was gonna write this code but i think instead I wanna get
    //the clicking on pawwfice main working because this
    //is gonna be really involved possibly with web dev stuff...
}