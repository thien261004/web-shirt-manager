package controllers;

import entities.mapping_entities.KhachHang;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import respositories.jdbc.KhachHangRepository;

import java.io.IOException;
import java.util.List;

@WebServlet({
        "/khach-hang/index",//get
        "/khach-hang/create", //get
        "/khach-hang/update", //post
        "/khach-hang/delete",//get
        "/khach-hang/store", //post
        "/khach-hang/edit",//get
})
public class KhachHangServlet extends HttpServlet {
    private KhachHangRepository msRepo = new KhachHangRepository();
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if(uri.contains("create")){
            this.create(req,res);
        }else if(uri.contains("edit")){
            this.edit(req,res);
        }else if(uri.contains("delete")) {
            this.delete(req, res);
        }else{
            this.index(req,res);
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String uri = req.getRequestURI();
        if(uri.contains("store")){
            this.store(req,res);
        }else if(uri.contains("update")){
            this.update(req,res);
        }else{
            //
        }
    }
    protected void index(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String s1 = req.getParameter("page");
        String s2 = req.getParameter("limit");
        int page = (s1 == null || s1.trim().length() == 0) ? 1 : Integer.parseInt(s1.trim());
        int limit = (s2 == null || s2.trim().length() == 0) ? 10 : Integer.parseInt(s2.trim());
        List<KhachHang> ds = this.msRepo.findAll(page, limit);
        int totalPage = (this.msRepo.count() / limit) + 1;
        req.setAttribute("data", ds);
        req.setAttribute("page", page);
        req.setAttribute("limit", limit);
        req.setAttribute("totalPage", totalPage);
        req.getRequestDispatcher("/views/khach-hang/index.jsp").forward(req,res);
    }
    protected void create(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/views/khach-hang/create.jsp").forward(req,res);
    }
    protected void store(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String ten = req.getParameter("ten");
        String sdt = req.getParameter("sdt");
        String ma =req.getParameter("ma");
        int trangThai = Integer.parseInt(req.getParameter("trangThai"));
        KhachHang kh = new KhachHang(id,ten,sdt,ma,trangThai);
        this.msRepo.insert(kh);
        res.sendRedirect("/khach-hang/index");

    }
    protected void edit(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        KhachHang kh = this.msRepo.findById(id);
        req.setAttribute("kh",kh);
        req.getRequestDispatcher("/views/mau-sac/edit.jsp").forward(req,res);
    }
    protected void update(HttpServletRequest req, HttpServletResponse res) throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String ma = req.getParameter("ma");
        String ten = req.getParameter("ten");
        String sdt =req.getParameter("sdt");
        int trangThai = Integer.parseInt(req.getParameter("trangThai"));
        KhachHang kh = new KhachHang(id,ma,ten,sdt,trangThai);
        this.msRepo.update(kh);
        res.sendRedirect("/mau-sac/index");
    }
    protected void delete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        this.msRepo.deleteById(id);
        res.sendRedirect("/khach-hang/index");
    }

}
