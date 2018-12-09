package api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import database.daos.*;

public class DaoRepository {

    @JsonProperty
    public final AdminDao adminDao;
    @JsonProperty
    public final AnswerDao answerDao;
    @JsonProperty
    public final DilemmaDao dilemmaDao;
    @JsonProperty
    public final ChildDao childDao;
    @JsonProperty
    public final CoupleDao coupleDao;
    @JsonProperty
    public final ParentDao parentDao;
    @JsonProperty
    public final ResultDao resultDao;
    @JsonProperty
    public final RightDao rightDao;

    @JsonCreator
    public DaoRepository(
        @JsonProperty("adminDao") AdminDao adminDao,
        @JsonProperty("answerDao") AnswerDao answerDao,
        @JsonProperty("childDao") ChildDao childDao,
        @JsonProperty("coupleDao") CoupleDao coupleDao,
        @JsonProperty("dilemmaDao") DilemmaDao dilemmaDao,
        @JsonProperty("parentDao") ParentDao parentDao,
        @JsonProperty("resultDao") ResultDao resultDao,
        @JsonProperty("rightDao") RightDao rightDao
    ) {
        this.adminDao = adminDao;
        this.answerDao = answerDao;
        this.childDao = childDao;
        this.coupleDao = coupleDao;
        this.dilemmaDao = dilemmaDao;
        this.parentDao = parentDao;
        this.resultDao = resultDao;
        this.rightDao = rightDao;
    }
}
