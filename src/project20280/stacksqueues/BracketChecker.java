package project20280.stacksqueues;

class BracketChecker {
    private final String input;

    public BracketChecker(String in) {
        input = in;
    }

    public void check() {
        LinkedStack<Character> stack = new LinkedStack<>();
        char closed = '_';
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == '(' || c == '{' || c == '['){
                stack.push(c);

                }
            else if(c == ')' || c == '}' || c == ']'){
                if (stack.isEmpty()){System.out.println("not correct; nothing matches final " + c + "\n"); return;}
                char popped = stack.pop();
                if( popped == '{'){closed = '}';}
                else if (popped == '['){closed = ']';}
                else if (popped == '('){closed = ')';}
                if (closed != c){
                    System.out.println("not correct; " + c +  "doesn't match " + popped + " \n");
                    return;
                }
                }
        }
        if(stack.isEmpty()){System.out.println("correct\n");}
        else{System.out.println("not correct; Nothing matches opening " + stack.pop());}
    }

    public static void main(String[] args) {
        String[] inputs = {
                "[]]()()", // not correct
                "c[d]", // correct\n" +
                "a{b[c]d}e", // correct\n" +
                "a{b(c]d}e", // not correct; ] doesn't match (\n" +
                "a[b{c}d]e}", // not correct; nothing matches final }\n" +
                "a{b(c) ", // // not correct; Nothing matches opening {
        };

        for (String input : inputs) {
            BracketChecker checker = new BracketChecker(input);
            System.out.println("checking: " + input);
            checker.check();
        }
    }
}