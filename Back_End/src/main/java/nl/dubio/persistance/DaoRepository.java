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
    private static CoupleListViewDao coupleListViewDao;
    @JsonProperty
    private static ParentDao parentDao;
    @JsonProperty
    private static ResultDao resultDao;
    @JsonProperty
    private static RightDao rightDao;
    @JsonProperty
    private static RatingDao ratingDao;
    @JsonProperty
    private static ConfigDao configDao;

    @JsonCreator
    public DaoRepository(
        @JsonProperty("adminDao") AdminDao adminDao,
        @JsonProperty("answerDao") AnswerDao answerDao,
        @JsonProperty("childDao") ChildDao childDao,
        @JsonProperty("coupleDao") CoupleDao coupleDao,
        @JsonProperty("coupleListViewDao") CoupleListViewDao coupleListViewDao,
        @JsonProperty("dilemmaDao") DilemmaDao dilemmaDao,
        @JsonProperty("parentDao") ParentDao parentDao,
        @JsonProperty("resultDao") ResultDao resultDao,
        @JsonProperty("rightDao") RightDao rightDao,
        @JsonProperty("ratingDao") RatingDao ratingDao,
        @JsonProperty("configDao") ConfigDao configDao
    ) {
        DaoRepository.adminDao = adminDao;
        DaoRepository.answerDao = answerDao;
        DaoRepository.childDao = childDao;
        DaoRepository.coupleDao = coupleDao;
        DaoRepository.coupleListViewDao = coupleListViewDao;
        DaoRepository.dilemmaDao = dilemmaDao;
        DaoRepository.parentDao = parentDao;
        DaoRepository.resultDao = resultDao;
        DaoRepository.rightDao = rightDao;
        DaoRepository.ratingDao = ratingDao;
        DaoRepository.configDao = configDao;
    }

    public static AdminDao getAdminDao() { return adminDao; }
    public static AnswerDao getAnswerDao() { return answerDao; }
    public static DilemmaDao getDilemmaDao() { return dilemmaDao; }
    public static ChildDao getChildDao() { return childDao; }
    public static CoupleDao getCoupleDao() { return coupleDao; }
    public static CoupleListViewDao getCoupleListViewDao() { return coupleListViewDao; }
    public static ParentDao getParentDao() { return parentDao; }
    public static ResultDao getResultDao() { return resultDao; }
    public static RightDao getRightDao() { return rightDao; }
    public static RatingDao getRatingDao() { return ratingDao; }
    public static ConfigDao getConfigDao() { return configDao; }
}
