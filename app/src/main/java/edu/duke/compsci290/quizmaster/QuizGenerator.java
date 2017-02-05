package edu.duke.compsci290.quizmaster;

import java.util.ArrayList;

/**
 * Created by ola on 1/26/17.
 */

public class QuizGenerator {
    public class Question {
        String myQuery;
        String myCorrectAnswer;
        String myWrongAnswer;
        String myThirdAnswer;
        String myFourthAnswer;

        public Question(String q, String correct, String wrong, String third, String fourth) {
            myQuery = q;
            myCorrectAnswer = correct;
            myWrongAnswer = wrong;
            myThirdAnswer = third;
            myFourthAnswer = fourth;
        }

        public String getQuery(){ return myQuery; }
        public String getMyCorrectAnswer(){ return myCorrectAnswer; }
        public String getMyWrongAnswer(){ return myWrongAnswer; }
        public String getMyThirdAnswer(){ return myThirdAnswer; }
        public String getMyFourthAnswer(){ return myFourthAnswer; }
    }

    private ArrayList<Question> myQuestions;
    private int myQuestionIndex;

    public QuizGenerator(){
        myQuestions = new ArrayList<>();
        makeQuiz();
    }

    private void makeQuiz() {
        myQuestions.add(
                new Question("What's the capital of North Carolina?",
                             "Raleigh", "Charlotte", "Greensboro", "Clemson"));
        myQuestions.add(
                new Question("How many feet are in a mile?",
                        "5,280", "7,186", "2,828", "4,302"));
        myQuestions.add(
                new Question("Why do birds fly south?",
                        "It's too far to walk", "Migratory patterns are ingrained",
                        "It's warmer down there", "They like fried chicken"));
        myQuestions.add(
                new Question("What is Stephen Kwok's favorite vegetable?",
                        "Broccoli", "Carrots",
                        "Spinach", "Potato"));
        myQuestions.add(
                new Question("What is this class called?",
                        "Mobile Software Design", "QuizGenerator",
                        "Astrachan the Man", "Work Work Work"));
    }

    public Question getQuestion(){
        if (myQuestionIndex < myQuestions.size()) {
            Question q = myQuestions.get(myQuestionIndex);
            myQuestionIndex++;
            return  q;
        }
        return null;
    }
}
