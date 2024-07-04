package respositories.jdbc;

import entities.mapping_entities.SanPham;
import utils.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SanPhamRepository {
    private Connection conn;

    public SanPhamRepository() {
        try {
            this.conn = DBContext.getConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<SanPham> findAll(){
        List<SanPham> ds = new ArrayList<>();
        //Select*from MauSac
        String sql="select * from SanPham";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while(rs.next()){
                int id = rs.getInt("ID");
                String ma = rs.getString("Ma");
                String ten = rs.getString("Ten");
                int trangThai = rs.getInt("TrangThai");
                SanPham ms = new SanPham(id,ma,ten,trangThai);
                ds.add(ms);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ds;
    }

    public void insert(SanPham sp){
        String sql="insert into SanPham(Ma, Ten, TrangThai) values(?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,sp.getMa());
            ps.setString(2,sp.getTen());
            ps.setInt(3,sp.getTrangThai());
            ps.executeQuery();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String sql="DELETE FROM SanPham WHERE ID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(SanPham sp)
    {
        String sql = "UPDATE SanPham SET Ma = ?, Ten = ?, TrangThai = ? WHERE ID = ?";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, sp.getMa());
            ps.setString(2, sp.getTen());
            ps.setInt(3, sp.getTrangThai());
            ps.setInt(4, sp.getId());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<SanPham> findAll(int page, int limit)
    {
        List<SanPham> ds = new ArrayList<>();

        try {
            String sql = "SELECT * FROM SanPham ORDER BY ID " +
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
                SanPham ms = new SanPham(id,ma,ten,trangThai);
                ds.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    public int count()
    {
        try {
            String sql = "SELECT COUNT(ID) Total FROM SanPham";
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

    public SanPham findByID(int id)
    {
        try {
            String sql = "SELECT * FROM SanPham WHERE ID = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            rs.next();
            String ma = rs.getString("Ma");
            String ten = rs.getString("Ten");
            int trangThai = rs.getInt("TrangThai");
            return new SanPham(id, ma, ten, trangThai);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
