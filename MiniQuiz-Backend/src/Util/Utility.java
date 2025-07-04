package Util;

import Models.Question;

public class Utility {

    public boolean checkAnswer(String answerByUser, Question question) {
        boolean result = false;
        switch (answerByUser.toLowerCase()) {
            case "a":
                if (question.getOptionA().trim().equals(question.getAnswer().trim())) {
                    result = true;
                }
                break;
            case "b":
                if (question.getOptionB().trim().equals(question.getAnswer().trim())) {
                    result = true;
                }
                break;
            case "c":
                if (question.getOptionC().trim().equals(question.getAnswer().trim())) {
                    result = true;
                }
                break;
            case "d":
                if (question.getOptionD().trim().equals(question.getAnswer().trim())) {
                    result = true;
                }
                break;
        }
        return result;
    }

    public String getAnswerLabel(Question question) {
        if (question.getAnswer().trim().equals(question.getOptionA().trim())){
            return "Option A";
        }

        else if (question.getAnswer().trim().equals(question.getOptionB().trim())){
            return "Option B";
        }

        else if (question.getAnswer().trim().equals(question.getOptionC().trim())){
            return "Option C";
        }

        else if (question.getAnswer().trim().equals(question.getOptionD().trim())){
            return "Option D";
        }

        else {
            return "None";
        }
    }

    public String addQuestionMarkIfNot(String question) {
        int length = question.trim().length();
        if (question.charAt(length-1) != '?'){
            question = question +
                    "?";
            return question;
        }
        return question;
    }

}
