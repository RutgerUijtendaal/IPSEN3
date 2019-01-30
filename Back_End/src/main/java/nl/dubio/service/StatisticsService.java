package nl.dubio.service;

import nl.dubio.models.Answer;
import nl.dubio.models.Couple;
import nl.dubio.models.Dilemma;
import nl.dubio.models.StatisticModel;
import nl.dubio.persistance.DaoRepository;

import java.util.List;

public class StatisticsService {

    private final StatisticModel statisticModel;

    public StatisticsService() {
        statisticModel = new StatisticModel();
        statisticModel.setData(DaoRepository.getDilemmaDao().getAll(), DaoRepository.getParentDao().getAll(), DaoRepository.getCoupleDao().getAll(), DaoRepository.getChildDao().getAll(), DaoRepository.getAnswerDao().getAll(), DaoRepository.getResultDao().getAll(), DaoRepository.getRatingDao().getAll());
        statisticModel.initFilter();
    }

    public StatisticModel resetModel() {
        statisticModel.resetFilters();
        return statisticModel;
    }

    public StatisticModel filterByDilemma(List<Dilemma> dilemmas) {
        statisticModel.filterByDilemma(dilemmas);
        return statisticModel;
    }

    public StatisticModel getStatisticModel() {
        return statisticModel;
    }

    public StatisticModel filterByAnswers(List<Answer> answers) {
        statisticModel.filterByAnswer(answers);
        return statisticModel;
    }

    public StatisticModel filterByBorn(boolean born) {
        statisticModel.filterByBronStatus(born);
        return statisticModel;
    }

    public StatisticModel filterByHour(int hour) {
        statisticModel.filterByHour(hour);
        return statisticModel;
    }

    public void filterByCouple(List<Couple> couples) {
        statisticModel.filterByCouple(couples);
    }
}