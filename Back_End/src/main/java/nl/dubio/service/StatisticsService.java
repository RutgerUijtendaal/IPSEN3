package nl.dubio.service;

import nl.dubio.models.Answer;
import nl.dubio.models.Couple;
import nl.dubio.models.Dilemma;
import nl.dubio.models.StatisticModel;
import nl.dubio.persistance.DaoRepository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class StatisticsService {

    private final StatisticModel statisticModel;
    private Timestamp lastUpdate = new Timestamp(System.currentTimeMillis());

    public StatisticsService() {
        statisticModel = new StatisticModel();
        statisticModel.initFilter();
        setData();
    }

    public void setData() {
        statisticModel.setData(DaoRepository.getDilemmaDao().getAll(), DaoRepository.getParentDao().getAll(), DaoRepository.getCoupleDao().getAll(), DaoRepository.getChildDao().getAll(), DaoRepository.getAnswerDao().getAll(), DaoRepository.getResultDao().getAll(), DaoRepository.getRatingDao().getAll());
    }

    public StatisticModel resetModel() {
        statisticModel.resetFilters();

        Timestamp current = new Timestamp(System.currentTimeMillis());
        // If cached data is older than 10 minutes refresh data
        if(lastUpdate.getTime() < current.getTime() - (10 * 60 * 1000)) {
            lastUpdate = current;
            setData();
        }

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