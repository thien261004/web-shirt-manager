package respositories.jpa;

import entities.mapping_entities.MauSac;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import java.util.ArrayList;

public class RepositoryMauSac {
    Session session;

    public ArrayList<MauSac> getList() {

        session = HibernateUtils.getFACTORY().openSession();

        ArrayList<MauSac> list = (ArrayList<MauSac>) session.createQuery("FROM MauSac ").list();

        session.close();

        return list;

    }
    public MauSac getDetail(Integer idMS) {

        session = HibernateUtils.getFACTORY().openSession();

        MauSac list = (MauSac) session.createQuery("FROM MauSac WHERE id = :id").setParameter("id", idMS).getSingleResult();

        session.close();

        return list;

    }

    public void add(MauSac mauSac) {

        session = HibernateUtils.getFACTORY().openSession();

        Transaction transaction = session.beginTransaction();

        try {
            session.saveOrUpdate(mauSac);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

        session.close();
    }

    public void delete(MauSac mauSac) {

        session = HibernateUtils.getFACTORY().openSession();

        Transaction transaction = session.beginTransaction();

        try {
            session.delete(mauSac);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

        session.close();
    }
}
