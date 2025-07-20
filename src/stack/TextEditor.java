package stack;

import java.util.Stack;

public class TextEditor {
    Stack<String> undoStack = new Stack<>();
    Stack<String> redoStack = new Stack<>();

    public TextEditor() {
        undoStack.push("Selamat");
        undoStack.push("Selamat datang");
        redoStack.clear();
    }

    public void runEditor() {
        System.out.println("Teks saat ini: " + undoStack.peek());
        redoStack.push(undoStack.pop());
        System.out.println("Undo: " + undoStack.peek());
        undoStack.push(redoStack.pop());
        System.out.println("Redo: " + undoStack.peek());
    }
}
