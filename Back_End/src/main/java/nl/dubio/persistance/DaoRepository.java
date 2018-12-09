package nl.dubio.persistance;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DaoRepository {

    @JsonProperty
    private static AdminDao adminDao;
    @JsonProperty
    private static AnswerDao answerDao;
    @JsonProperty
    private static DilemmaDao dilemmaDao;
    @JsonProperty
    private static ChildDao childDao;
    @JsonProperty
    private static CoupleDao coupleDao;
    @JsonProperty
    private static ParentDao parentDao;
    @JsonProperty
    private static ResultDao resultDao;
    @JsonProperty
    private static RightDao rightDao;

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
        DaoRepository.adminDao = adminDao;
        DaoRepository.answerDao = answerDao;
        DaoRepository.childDao = childDao;
        DaoRepository.coupleDao = coupleDao;
        DaoRepository.dilemmaDao = dilemmaDao;
        DaoRepository.parentDao = parentDao;
        DaoRepository.resultDao = resultDao;
        DaoRepository.rightDao = rightDao;
    }

    public static AdminDao getAdminDao() { return adminDao; }
    public static AnswerDao getAnswerDao() { return answerDao; }
    public static DilemmaDao getDilemmaDao() { return dilemmaDao; }
    public static ChildDao getChildDao() { return childDao; }
    public static CoupleDao getCoupleDao() { return coupleDao; }
    public static ParentDao getParentDao() { return parentDao; }
    public static ResultDao getResultDao() { return resultDao; }
    public static RightDao getRightDao() { return rightDao; }
}
