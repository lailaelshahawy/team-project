package use_case.review_trivia_results;

import entity.TriviaQuestion;
import entity.TriviaSession;

import java.util.ArrayList;
import java.util.List;

public class ReviewTriviaResultsInteractor implements ReviewTriviaResultsInputBoundary {
    private final ReviewTriviaResultsDataAccessInterface sessionDataAccess;
    private final ReviewTriviaResultsOutputBoundary presenter;

    public ReviewTriviaResultsInteractor(ReviewTriviaResultsDataAccessInterface sessionDataAccess,
                                         ReviewTriviaResultsOutputBoundary presenter) {
        this.sessionDataAccess = sessionDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        TriviaSession session = sessionDataAccess.getSession();
        if (session == null) {
            presenter.prepareFailView("There is no quiz to review. Start a quiz first.");
            return;
        }
        if (!session.isComplete()) {
            presenter.prepareFailView("Finish all ten questions before reviewing your results.");
            return;
        }

        List<TriviaQuestion> questions = session.getQuestions();
        List<String> answers = session.getSubmittedAnswers();
        List<ReviewTriviaResultsOutputData.QuestionReview> reviews = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            TriviaQuestion question = questions.get(i);
            String answer = answers.get(i);
            reviews.add(new ReviewTriviaResultsOutputData.QuestionReview(
                    question.getQuestionText(), answer, question.getCorrectAnswer(),
                    question.isCorrect(answer)));
        }
        presenter.prepareSuccessView(new ReviewTriviaResultsOutputData(
                session.getScore(), TriviaSession.QUESTION_COUNT, reviews));
    }
}
