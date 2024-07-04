package controllers;

import entities.mapping_entities.SanPham;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import respositories.jdbc.SanPhamRepository;

import java.io.IOException;
import java.util.List;

@WebServlet({
        "/san-pham/index",//get
        "/san-pham/create", //get
        "/san-pham/update", //post
        "/san-pham/delete",//get
        "/san-pham/store", //post
        "/san-pham/edit",//get
})
public class SanPhamServlet extends HttpServlet {
    private SanPhamRepository msRepo = new SanPhamRepository();
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
        List<SanPham> ds = this.msRepo.findAll(page, limit);
        int totalPage = (this.msRepo.count() / limit) + 1;
        req.setAttribute("data", ds);
        req.setAttribute("page", page);
        req.setAttribute("limit", limit);
        req.setAttribute("totalPage", totalPage);
        req.getRequestDispatcher("/views/san-pham/index.jsp").forward(req,res);
    }
    protected void create(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/views/san-pham/create.jsp").forward(req,res);
    }
    protected void store(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String ma = req.getParameter("ma");
        String ten = req.getParameter("ten");
        int trangThai = Integer.parseInt(req.getParameter("trangThai"));
        SanPham sp = new SanPham(null,ma,ten,trangThai);
        this.msRepo.insert(sp);
        res.sendRedirect("/san-pham/index");

    }
    protected void edit(HttpServletRequest req, HttpServletResponse res){

    }
    protected void update(HttpServletRequest req, HttpServletResponse res){

    }
    protected void delete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        this.msRepo.deleteById(id);
        res.sendRedirect("/san-pham/index");
    }

}
