public class Main {

    public static int state = 0;
    public static Thread mainThread;
    public static Menu menu;
    public static Window window;

    public static void main(String[] args) {
        menu = new Menu();

        mainThread = new Thread(menu);
        mainThread.start();
    }

    public static void changeState(int newState){
        state = newState;
        if (state == 1){
            menu.stop();
            window = new Window();
            mainThread = new Thread(window);
            mainThread.start();
        } else if (state == 0){
            window.stop();
            menu = new Menu();
            mainThread = new Thread(menu);
            mainThread.start();
        }
    }
}
