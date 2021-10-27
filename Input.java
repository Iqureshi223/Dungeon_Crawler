import ansi_terminal.*;
public class Input {
private Key key = Terminal.getKey();
public Input() {

}
public Key getInput() {
	return key;
}

public void openIventory(Entity entity) {

}
public void help() {
switch(key) {
	case H:
	System.out.println("Welcome to our game, this is the rules of our game: ");
	break;
}

}

}
