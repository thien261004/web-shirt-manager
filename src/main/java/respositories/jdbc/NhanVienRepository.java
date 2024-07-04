package respositories.jdbc;

import entities.mapping_entities.NhanVien;
import utils.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienRepository {
    private Connection conn;

    public NhanVienRepository() {
        try {
            this.conn = DBContext.getConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<NhanVien> findAll(){
        List<NhanVien> ds = new ArrayList<>();
        //Select*from MauSac
        String sql="select * from NhanVien";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while(rs.next()){
                int id = rs.getInt("ID");
                String ten = rs.getString("Ten");
                String maNV = rs.getString("Ma");
                String tenDangNhap =rs.getString("TenDangNhap");
                String matKhau =rs.getString("MatKhau");
                int trangThai = rs.getInt("TrangThai");
                NhanVien nv = new NhanVien(id,ten,maNV,tenDangNhap,matKhau,trangThai);
                ds.add(nv);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ds;
    }

    public void insert(NhanVien nv){
        String sql="insert into NhanVien(ID,Ten,Ma,TenDangNhap,MatKhau,TrangThai) values(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,nv.getID());
            ps.setString(2,nv.getTen());
            ps.setString(3,nv.getMaNV());
            ps.setString(4,nv.getTenDangNhap());
            ps.setString(5,nv.getMatKhau());
            ps.setInt(6,nv.getTrangThai());
            ps.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String sql="DELETE NhanVien WHERE ID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(NhanVien nv)
    {
        String sql = "UPDATE NhanVien SET Ten = ?, Ma = ?, TenDangNhap = ?, MatKhau=? ,TrangThai=? WHERE ID = ?";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, nv.getTen());
            ps.setString(2, nv.getMaNV());
            ps.setString(3, nv.getTenDangNhap());
            ps.setString(4, nv.getMatKhau());
            ps.setInt(5, nv.getTrangThai());
            ps.setInt(6, nv.getID());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<NhanVien> findAll(int page, int limit)
    {
        List<NhanVien> ds = new ArrayList<>();

        try {
            String sql = "SELECT * FROM NhanVien ORDER BY ID " +
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
                String ten = rs.getString("Ten");
                String maNV = rs.getString("Ma");
                String tenDangNhap =rs.getString("TenDangNhap");
                String matKhau =rs.getString("MatKhau");
                int trangThai = rs.getInt("TrangThai");
                NhanVien nv = new NhanVien(id,ten,maNV,tenDangNhap,matKhau,trangThai);
                ds.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    public int count()
    {
        try {
            String sql = "SELECT COUNT(ID) Total FROM NhanVien";
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
}
