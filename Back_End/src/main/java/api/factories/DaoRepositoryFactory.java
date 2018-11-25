package api.factories;

import com.fasterxml.jackson.annotation.JsonProperty;
import database.DaoRepository;
import database.daos.AdminDao;
import database.daos.AnswerDao;
import database.daos.DilemmaDao;

import javax.validation.constraints.NotNull;

public class DaoRepositoryFactory {

    @NotNull
    @JsonProperty
    private GenericDao adminDao;

    @NotNull
    @JsonProperty
    private GenericDao answerDao;

    @NotNull
    @JsonProperty
    private GenericDao dilemmaDao;



    public DaoRepository build(){
        return new DaoRepository(
                new AdminDao(adminDao.getTableName(), adminDao.getColumnNames()),
                new AnswerDao(answerDao.getTableName(), answerDao.getColumnNames()),
                new DilemmaDao());
    }



    public GenericDao getAdminDao() {
        return adminDao;
    }
    public void setAdminDao(GenericDao adminDao) {
        this.adminDao = adminDao;
    }
}

class GenericDao {

    @NotNull
    @JsonProperty
    private String tableName;

    @NotNull
    @JsonProperty
    private String[] columnNames;


    public String getTableName() { return tableName; }
    public void setTableName(String tableName) { this.tableName = tableName; }
    public String[] getColumnNames() { return columnNames; }
    public void setColumnNames(String[] columnNames) { this.columnNames = columnNames; }

}
