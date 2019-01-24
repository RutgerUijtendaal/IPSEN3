package nl.dubio.models.databag;

import nl.dubio.models.Answer;
import nl.dubio.models.Dilemma;

public class AnswerDilemmaDatabag {

    private Dilemma dilemma;
    private Answer[] answers;

    public AnswerDilemmaDatabag(Dilemma dilemma, Answer[] answers) {
        this.dilemma = dilemma;
        this.answers = answers;
    }

    public Dilemma getDilemma() {
        return dilemma;
    }

    public void setDilemma(Dilemma dilemma) {
        this.dilemma = dilemma;
    }

    public Answer[] getAnswers() {
        return answers;
    }

    public void setAnswers(Answer[] answers) {
        this.answers = answers;
    }
}
