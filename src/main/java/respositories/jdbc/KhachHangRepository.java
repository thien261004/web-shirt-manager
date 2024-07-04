package respositories.jdbc;

import entities.mapping_entities.KhachHang;
import utils.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhachHangRepository {
    private Connection conn;

    public KhachHangRepository() {
        try {
            this.conn = DBContext.getConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<KhachHang> findAll(){
        List<KhachHang> ds = new ArrayList<>();
        //Select*from MauSac
        String sql="select * from KhachHang";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while(rs.next()){
                int id = rs.getInt("ID");
                String ma = rs.getString("Ma");
                String ten = rs.getString("Ten");
                String sdt =rs.getString("SDT");
                int trangThai = rs.getInt("TrangThai");
                KhachHang kh = new KhachHang(id,ma,ten,sdt,trangThai);
                ds.add(kh);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ds;
    }

    public void insert(KhachHang kh){
        String sql="insert into KhachHang(ID,Ma,Ten,SDT,TrangThai) values(?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,kh.getId());
            ps.setString(2,kh.getMa());
            ps.setString(3,kh.getTen());
            ps.setString(4, kh.getSdt());
            ps.setInt(5,kh.getTrangThai());
            ps.executeQuery();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String sql="DELETE KhachHang WHERE ID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(KhachHang kh)
    {
        String sql = "UPDATE KhachHang SET  Ma = ?, Ten = ?, SDT = ?,TrangThai=? WHERE ID = ?";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, kh.getMa());
            ps.setString(2, kh.getTen());
            ps.setString(3, kh.getSdt());
            ps.setInt(4, kh.getTrangThai());
            ps.setInt(5, kh.getId());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<KhachHang> findAll(int page, int limit)
    {
        List<KhachHang> ds = new ArrayList<>();

        try {
            String sql = "SELECT * FROM KhachHang ORDER BY ID " +
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
                String sdt =rs.getString("SDT");
                int trangThai = rs.getInt("TrangThai");
                KhachHang kh = new KhachHang(id,ma,ten,sdt,trangThai);
                ds.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    public int count()
    {
        try {
            String sql = "SELECT COUNT(ID) Total FROM KhachHang";
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

    public KhachHang findById(int id) {
        String sql = "SELECT * FROM KhachHang WHERE ID = ?";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String ma = rs.getString("Ma");
            String ten = rs.getString("Ten");
            String sdt = rs.getString("SDT");
            int trangThai = rs.getInt("TrangThai");

            KhachHang kh = new KhachHang(id, ma, ten, sdt, trangThai);
            return kh;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
