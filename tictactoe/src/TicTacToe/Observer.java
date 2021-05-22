package TicTacToe;

public interface Observer<Subject, String> {
    void update(Subject model, String data);
}
