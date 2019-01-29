package nl.dubio.service;

import nl.dubio.models.CoupleListModel;
import nl.dubio.persistance.CoupleListViewDao;
import nl.dubio.persistance.DaoRepository;

import java.util.List;

public class CoupleListService {

    private final CoupleListViewDao coupleListViewDao;

    public CoupleListService() {
        this.coupleListViewDao = DaoRepository.getCoupleListViewDao();
    }

    public List<CoupleListModel> getAll() {
        return coupleListViewDao.getAll();
    }

    public CoupleListModel getByEmail(String email) {
        return coupleListViewDao.getByEmail(email);
    }

}
