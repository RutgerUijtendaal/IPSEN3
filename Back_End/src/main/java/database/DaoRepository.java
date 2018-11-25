package database;

import database.daos.AdminDao;
import database.daos.AnswerDao;
import database.daos.DilemmaDao;

public class DaoRepository {
    public final AdminDao adminDao;
    public final AnswerDao answerDao;
    public final DilemmaDao dilemmaDao;

    public DaoRepository(AdminDao adminDao, AnswerDao answerDao, DilemmaDao dilemmaDao) {
        this.adminDao = adminDao;
        this.answerDao = answerDao;
        this.dilemmaDao = dilemmaDao;
    }
}
