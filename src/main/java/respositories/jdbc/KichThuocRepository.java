package respositories.jdbc;

import entities.mapping_entities.KichThuoc;
import utils.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KichThuocRepository {
    private Connection conn;

    public KichThuocRepository() {
        try {
            this.conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<KichThuoc> findAll() {
        List<KichThuoc> ds = new ArrayList<>();
        //Select*from MauSac
        String sql = "select * from KichThuoc";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String ma = rs.getString("Ma");
                String ten = rs.getString("Ten");
                int trangThai = rs.getInt("TrangThai");
                KichThuoc kt = new KichThuoc(id, ma, ten, trangThai);
                ds.add(kt);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ds;
    }

    public void insert(KichThuoc kt) {
        String sql = "insert into KichThuoc(Ma, Ten, TrangThai) values(?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kt.getMa());
            ps.setString(2, kt.getTen());
            ps.setInt(3, kt.getTrangThai());
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM KichThuoc WHERE ID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(KichThuoc kt) {
        String sql = "UPDATE KichThuoc SET Ma = ?, Ten = ?, TrangThai = ? WHERE ID = ?";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, kt.getMa());
            ps.setString(2, kt.getTen());
            ps.setInt(3, kt.getTrangThai());
            ps.setInt(4, kt.getId());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<KichThuoc> findAll(int page, int limit) {
        List<KichThuoc> ds = new ArrayList<>();

        try {
            String sql = "SELECT * FROM KichThuoc ORDER BY ID " +
                    " OFFSET ? ROWS " + // bỏ qua ? bản ghi
                    " FETCH NEXT ? ROWS ONLY"; // lấy ? bản ghi
            PreparedStatement ps = this.conn.prepareStatement(sql);
            int offset = (page - 1) * limit;
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String ma = rs.getString("Ma");
                String ten = rs.getString("Ten");
                int trangThai = rs.getInt("TrangThai");
                KichThuoc kt = new KichThuoc(id, ma, ten, trangThai);
                ds.add(kt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    public int count() {
        try {
            String sql = "SELECT COUNT(ID) Total FROM KichThuoc";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            rs.next();
            return rs.getInt("Total");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    public HashMap<Integer, KichThuoc> findByIdKT(List<Integer> listIdKT) {
        HashMap<Integer, KichThuoc> result = new HashMap<>();

        String sql = "SELECT * FROM KichThuoc WHERE ID IN (";

        for (int i = 0; i < listIdKT.size(); i++) {
            sql += "?";
            if (i != listIdKT.size() - 1) {
                sql += ",";
            }
        }

        sql += ")";
        System.out.println("----------------------");
        System.out.println("SQL: ");
        System.out.println(sql);
        System.out.println("----------------------");
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            for (int i = 0; i < listIdKT.size(); i++) {
                ps.setInt(i + 1, listIdKT.get(i));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String ma = rs.getString("Ma");
                String ten = rs.getString("Ten");
                int trangThai = rs.getInt("TrangThai");
                KichThuoc kt = new KichThuoc(id, ma, ten, trangThai);
                result.put(id, kt);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
