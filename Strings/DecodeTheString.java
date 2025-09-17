import java.util.Stack;

class Solution {
    static String decodeString(String s) {
        Stack<Integer> countStack=new Stack<>();
        Stack<StringBuilder> stringStack=new Stack();
        StringBuilder currStr=new StringBuilder();
        int num=0;
        for(char ch:s.toCharArray()){
            if(Character.isDigit(ch)){
                num=num*10+(ch-'0');
            }
            else if(ch=='['){
                countStack.push(num);
                stringStack.push(currStr);
                currStr=new StringBuilder();
                num=0;
            }
            else if(ch==']'){
                int repeatTimes=countStack.pop();
                StringBuilder prevStr=stringStack.pop();
                for(int i=0;i<repeatTimes;i++){
                    prevStr.append(currStr);
                    
                }
                currStr=prevStr;
            }
            else{
                currStr.append(ch);
            }
        }
        return currStr.toString();
    }}
